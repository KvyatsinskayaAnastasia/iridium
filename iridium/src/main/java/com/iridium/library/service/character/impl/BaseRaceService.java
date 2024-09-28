package com.iridium.library.service.character.impl;

import com.iridium.library.entity.character.RaceAbilityEO;
import com.iridium.library.entity.character.RaceEO;
import com.iridium.library.entity.power.AbilityEO;
import com.iridium.library.mapper.character.RaceMapper;
import com.iridium.library.repository.character.RaceAbilityRepository;
import com.iridium.library.repository.character.RaceRepository;
import com.iridium.library.service.character.RaceService;
import com.iridium.library.service.power.AbilityService;
import com.iridium.library.service.power.MagicService;
import com.iridium.openapi.model.RaceAbilityRequest;
import com.iridium.openapi.model.RaceMagicRequest;
import com.iridium.openapi.model.RaceRequest;
import com.iridium.openapi.model.RaceResponse;
import com.iridium.openapi.model.ShortRaceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * Base service to work with race model.
 * See the {@link com.iridium.library.service.character.RaceService}.
 *
 * @author Kviatsinskaya Anastasia
 */
@Service
@RequiredArgsConstructor
public class BaseRaceService implements RaceService {

    private final RaceRepository raceRepository;
    private final RaceMapper raceMapper;
    private final MagicService magicService;
    private final AbilityService abilityService;
    private final RaceAbilityRepository raceAbilityRepository;

    @Override
    public final UUID saveRace(final RaceRequest race) {
        final var newRace = raceMapper.toRaceEO(race);

        if (!race.getBaseMagic().isEmpty()) {
            final var baseMagicIds = race.getBaseMagic().stream()
                .map(RaceMagicRequest::getId).collect(Collectors.toSet());
            newRace.setBaseMagic(magicService.getAllMagicEntitiesByIds(baseMagicIds));
        }

        if (!race.getUnavailableMagic().isEmpty()) {
            final var unavailableMagicIds = race.getUnavailableMagic().stream()
                .map(RaceMagicRequest::getId).collect(Collectors.toSet());
            newRace.setUnavailableMagic(magicService.getAllMagicEntitiesByIds(unavailableMagicIds));
        }

        final var raceId = raceRepository.save(newRace).getId();

        if (!race.getRaceAbilities().isEmpty()) {
            final var raceAbilityIds = race.getRaceAbilities().stream()
                .map(RaceAbilityRequest::getId).collect(Collectors.toSet());
            final var raceAbilities = abilityService.getAllAbilityEntitiesByIds(raceAbilityIds).stream()
                .collect(toMap(AbilityEO::getId, Function.identity()));
            final var raceAbilityEOS = race.getRaceAbilities().stream()
                .map(raceAbilityRequest -> createRaceAbility(raceAbilities, raceAbilityRequest, newRace))
                .collect(Collectors.toSet());
            raceAbilityRepository.saveAll(raceAbilityEOS);
        }

        return raceId;
    }

    private RaceAbilityEO createRaceAbility(final Map<UUID, ? extends AbilityEO> raceAbilities,
                                            final RaceAbilityRequest raceAbilityRequest,
                                            final RaceEO race) {
        final RaceAbilityEO raceAbilityEO = new RaceAbilityEO();
        raceAbilityEO.setBonus(raceAbilityRequest.getBonus());
        raceAbilityEO.setAbility(raceAbilities.get(raceAbilityRequest.getId()));
        raceAbilityEO.setRace(race);
        return raceAbilityEO;
    }


    @Override
    public final List<ShortRaceResponse> getAllRaces() {
        return raceMapper.toRaceList(raceRepository.findAll());
    }

    @Override
    public final RaceResponse getRaceById(final UUID id) {
        return raceMapper.toRace(raceRepository.findByIdWithMagicInfoAndAbilityBonuses(id).orElse(null));
    }

    @Override
    public final void deleteRace(final UUID uuid) {
        raceRepository.deleteById(uuid);
    }
}

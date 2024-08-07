package com.iridium.library.service.character.impl;

import com.iridium.library.mapper.character.RaceMapper;
import com.iridium.library.repository.character.RaceRepository;
import com.iridium.library.service.character.RaceService;
import com.iridium.openapi.model.Race;
import com.iridium.openapi.model.RaceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Base service to work with race model.
 * See the {@link com.iridium.library.service.character.RaceService}.
 * @author Kviatsinskaya Anastasia
 */
@Service
@RequiredArgsConstructor
public class BaseRaceService implements RaceService {

    private final RaceRepository raceRepository;
    private final RaceMapper raceMapper;

    @Override
    public final UUID saveRace(final RaceRequest race) {
        return raceRepository.save(raceMapper.toRaceEO(race)).getId();
    }

    @Override
    public final List<Race> getAllRaces() {
        return raceMapper.toRaceList(raceRepository.findAll());
    }

    @Override
    public final Race getRaceById(final UUID id) {
        return raceMapper.toRace(raceRepository.findById(id).orElse(null));
    }

    @Override
    public final void deleteRace(final UUID uuid) {
        raceRepository.deleteById(uuid);
    }
}

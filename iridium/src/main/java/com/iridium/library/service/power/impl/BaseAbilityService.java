package com.iridium.library.service.power.impl;

import com.iridium.library.mapper.power.AbilityMapper;
import com.iridium.library.repository.power.AbilityRepository;
import com.iridium.library.service.power.AbilityService;
import com.iridium.openapi.model.Ability;
import com.iridium.openapi.model.AbilityType;
import com.iridium.openapi.model.AddAbility;
import com.iridium.openapi.model.ShortAbilityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Base service to work with ability model.
 * See the {@link com.iridium.library.service.power.AbilityService}.
 *
 * @author Kviatsinskaya Anastasia
 */
@Service
@RequiredArgsConstructor
public class BaseAbilityService implements AbilityService {

    private final AbilityRepository abilityRepository;
    private final AbilityMapper abilityMapper;

    @Override
    public final UUID saveAbility(final AddAbility ability) {
        return abilityRepository.save(abilityMapper.toAbilityEO(ability)).getId();
    }

    @Override
    public final List<ShortAbilityResponse> getAllAbilities() {
        return abilityMapper.toShortAbilityResponseList(abilityRepository.findAll());
    }

    @Override
    public final Ability getAbilityById(final UUID id) {
        return abilityMapper.toAbility(
            abilityRepository.findByIdWithSkills(id).orElseThrow(RuntimeException::new)
        ); //todo: подумать над обработкой ошибок
    }

    @Override
    public final List<ShortAbilityResponse> getAllAbilitiesByType(final AbilityType type) {
        return abilityMapper.toShortAbilityResponseList(
            abilityRepository.findByAbilityTypeIs(abilityMapper.toAbilityTypeEO(type))
        );
    }

    @Override
    public final void deleteAbility(final UUID uuid) {
        abilityRepository.deleteById(uuid);
    }
}

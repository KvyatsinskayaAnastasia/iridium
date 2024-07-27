package com.iridium.library.mapper.power;

import com.iridium.library.entity.power.AbilityEO;
import com.iridium.library.entity.power.AbilityTypeEO;
import com.iridium.openapi.model.Ability;
import com.iridium.openapi.model.AbilityType;
import com.iridium.openapi.model.AddAbilityRequest;
import com.iridium.openapi.model.ShortAbilityResponse;
import com.iridium.openapi.model.Skill;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class AbilityMapper {
    /**
     * Map ability entity to short ability response.
     *
     * @param abilityEO ability entity
     * @return short ability response
     */
    public abstract ShortAbilityResponse toShortAbilityResponse(AbilityEO abilityEO);

    /**
     * Map ability entity to ability model.
     *
     * @param abilityEO ability entity
     * @return ability model
     */
    public abstract Ability toAbility(AbilityEO abilityEO);

    /**
     * Map ability model to ability entity.
     *
     * @param ability ability model
     * @return ability entity
     */
    @Mapping(target = "id", ignore = true)
    public abstract AbilityEO toAbilityEO(AddAbilityRequest ability);

    /**
     * Map list of ability entities to list of short ability responses.
     *
     * @param abilityEOList list of ability entities
     * @return list of short ability responses
     */
    public abstract List<ShortAbilityResponse> toShortAbilityResponseList(List<AbilityEO> abilityEOList);

    /**
     * Map ability type model to ability type entity.
     *
     * @param abilityType ability type model
     * @return ability type entity
     */
    public abstract AbilityTypeEO toAbilityTypeEO(AbilityType abilityType);

    /**
     * Add ability id for skillEO after abilityEO mapping.
     *
     * @param ability ability type model
     */
    @AfterMapping
    protected void addAbilityToSkills(@MappingTarget final AbilityEO ability) {
        ability.getSkills().forEach(skillEO -> skillEO.setAbility(ability));
    }

    /**
     * Sort skills after ability mapping.
     *
     * @param ability ability type model
     */
    @AfterMapping
    protected void sortSkills(@MappingTarget final Ability ability) {
        ability.setSkills(
            ability.getSkills().stream().sorted(Comparator.comparingInt(Skill::getMinLevel))
                .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }
}

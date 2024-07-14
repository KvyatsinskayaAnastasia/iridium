package com.iridium.library.mapper.power;

import com.iridium.library.entity.power.AbilityEO;
import com.iridium.library.entity.power.AbilityTypeEO;
import com.iridium.openapi.model.Ability;
import com.iridium.openapi.model.AbilityType;
import com.iridium.openapi.model.ShortAbilityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = AbilityTypeEO.class)
public interface AbilityMapper {
    /**
     * Map ability entity to short ability response.
     * @param abilityEO ability entity
     * @return short ability response
     */
    @Mapping(target = "abilityType", ignore = true)
    ShortAbilityResponse toShortAbilityResponse(AbilityEO abilityEO);

    /**
     * Map ability entity to ability model.
     * @param abilityEO ability entity
     * @return ability model
     */
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "abilityType", ignore = true)
    Ability toAbility(AbilityEO abilityEO);

    /**
     * Map ability model to ability entity.
     * @param ability ability model
     * @return ability entity
     */
    @Mapping(target = "skillEOS", ignore = true)
    @Mapping(target = "abilityTypeEO", ignore = true)
    AbilityEO toAbilityEO(Ability ability);

    /**
     * Map list of ability entities to list of short ability responses.
     * @param abilityEOList list of ability entities
     * @return list of short ability responses
     */
    List<ShortAbilityResponse> toShortAbilityResponseList(List<AbilityEO> abilityEOList);

    /**
     * Map ability type model to ability type entity.
     * @param abilityType ability type model
     * @return ability type entity
     */
    AbilityTypeEO toAbilityTypeEO(AbilityType abilityType);
}

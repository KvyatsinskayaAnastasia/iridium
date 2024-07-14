package com.iridium.library.mapper.power;

import com.iridium.library.entity.power.SpellEO;
import com.iridium.openapi.model.SpellResponse;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface SpellMapper {
    /**
     * Map spell entity to spell response.
     * @param spellEO spell entity
     * @return spell response
     */
    SpellResponse toSpellResponse(SpellEO spellEO);
    /**
     * Map set of spell entities to set of spell responses.
     * @param spellEOSet set of spell entities
     * @return set of spell responses
     */
    Set<SpellResponse> toSpellResponseSet(Set<SpellEO> spellEOSet);
}

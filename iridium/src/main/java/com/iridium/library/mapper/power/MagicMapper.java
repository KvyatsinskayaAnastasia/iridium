package com.iridium.library.mapper.power;

import com.iridium.library.entity.power.MagicEO;
import com.iridium.library.entity.power.SpellEO;
import com.iridium.openapi.model.AddMagicRequest;
import com.iridium.openapi.model.LeveledSpells;
import com.iridium.openapi.model.MagicResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Mapper(componentModel = "spring")
public abstract class MagicMapper {

    @Autowired
    private SpellMapper spellMapper;

    /**
     * Map list of magic entities to list of magic responses.
     *
     * @param magicEOList list of magic entities
     * @return list of magic responses
     */
    public abstract List<MagicResponse> toMagicResponseList(List<MagicEO> magicEOList);

    /**
     * Map magic model to magic entity.
     *
     * @param addMagicRequest magic model to add
     * @return magic entity
     */
    @Mapping(target = "id", ignore = true)
    public abstract MagicEO toMagicEO(AddMagicRequest addMagicRequest);

    /**
     * Map magic entity to magic response.
     *
     * @param magicEO magic entity
     * @return magic response
     */
    public MagicResponse toMagicResponse(final MagicEO magicEO) {
        if (null == magicEO) {
            return null;
        }

        final MagicResponse magicResponse = new MagicResponse();

        magicResponse.setId(magicEO.getId());
        magicResponse.setName(magicEO.getName());
        magicResponse.setDescription(magicEO.getDescription());

        final Map<Integer, Set<SpellEO>> leveledSpells = magicEO
            .getSpells()
            .stream()
            .collect(Collectors.groupingBy(SpellEO::getLevel,
                Collectors.mapping(Function.identity(), Collectors.toSet())))
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

        magicResponse.setLeveledSpells(leveledSpells.entrySet().stream().map(leveledSpell ->
            new LeveledSpells()
                .level(leveledSpell.getKey())
                .spells(spellMapper.toSpellResponseSet(leveledSpell.getValue())))
            .collect(Collectors.toCollection(LinkedHashSet::new)));

        return magicResponse;
    }

    /**
     * Add magic id for spellEO after magicEO mapping.
     *
     * @param magic magic type model
     */
    @AfterMapping
    protected void addMagicToSpells(@MappingTarget final MagicEO magic) {
        magic.getSpells().forEach(spellEO -> spellEO.setMagic(magic));
    }
}

package com.iridium.library.mapper.power;

import com.iridium.library.entity.power.MagicEO;
import com.iridium.library.entity.power.SpellEO;
import com.iridium.openapi.model.LeveledSpells;
import com.iridium.openapi.model.Magic;
import com.iridium.openapi.model.MagicResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Mapper(componentModel = "spring")
public abstract class MagicMapper {

    /**
     * See the {@link com.iridium.library.mapper.power.SpellMapper}.
     */
    private final SpellMapper spellMapper;

    /**
     * Map list of magic entities to list of magic responses.
     * @param magicEOList list of magic entities
     * @return list of magic responses
     */
    public abstract List<MagicResponse> toMagicResponseList(List<MagicEO> magicEOList);

    /**
     * Map magic model to magic entity.
     * @param magic magic model
     * @return magic entity
     */
    @Mapping(target = "spellEOS", ignore = true)
    public abstract MagicEO toMagicEO(Magic magic);

    /**
     * Map magic entity to magic response.
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
            .getSpellEOS()
            .stream()
            .collect(Collectors.groupingBy(SpellEO::getLevel,
                Collectors.mapping(Function.identity(), Collectors.toSet())));

        magicResponse.setLeveledSpells(leveledSpells.entrySet().stream().map(leveledSpell ->
            new LeveledSpells()
                .level(leveledSpell.getKey())
                .spells(spellMapper.toSpellResponseSet(leveledSpell.getValue())))
            .collect(Collectors.toSet()));

        return magicResponse;
    }
}

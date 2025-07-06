package com.iridium.library.service.power.impl;

import com.iridium.library.entity.power.MagicEO;
import com.iridium.library.entity.power.SpellEO;
import com.iridium.library.mapper.power.MagicMapper;
import com.iridium.library.repository.power.MagicRepository;
import com.iridium.library.repository.power.SpellRepository;
import com.iridium.library.service.power.MagicService;
import com.iridium.openapi.model.AddMagicRequest;
import com.iridium.openapi.model.CharacterSpell;
import com.iridium.openapi.model.MagicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Base service to work with magic model.
 * See the {@link com.iridium.library.service.power.MagicService}.
 *
 * @author Kviatsinskaya Anastasia
 */
@Service
@RequiredArgsConstructor
public class BaseMagicService implements MagicService {

    private final MagicRepository magicRepository;
    private final SpellRepository spellRepository;
    private final MagicMapper magicMapper;

    @Override
    public final UUID saveMagic(final AddMagicRequest addMagicRequest) {
        return magicRepository.save(magicMapper.toMagicEO(addMagicRequest)).getId();
    }

    @Override
    public final List<MagicResponse> getAllMagic() {
        return magicMapper.toMagicResponseList(magicRepository.findAll());
    }

    @Override
    public final MagicResponse getMagicById(final UUID id) {
        final Optional<MagicEO> magicOptional = magicRepository.findByIdWithSpells(id);
        return magicOptional.map(magicMapper::toMagicResponse).orElse(null);
    }

    @Override
    public final void deleteMagic(final UUID uuid) {
        magicRepository.deleteById(uuid);
    }

    @Override
    public final Set<MagicEO> getAllMagicEntitiesByIds(final Set<UUID> magicIds) {
        return magicRepository.findByIdIn(magicIds);
    }

    @Override
    public final Set<SpellEO> getAllSpellsEntitiesByIds(final Set<UUID> spellIds) {
        return spellRepository.findByIdIn(spellIds);
    }

    @Override
    public final Set<CharacterSpell> getAllCharacterSpellsForMagic(final UUID magicId, final int maxLevel) {
        return spellRepository.findByMagicIdAndLevelLessThanEqual(magicId, maxLevel).stream()
            .map(spellEO -> new CharacterSpell().id(spellEO.getId()).level(spellEO.getLevel()))
            .collect(Collectors.toSet());
    }

    @Override
    public final List<UUID> getAllMagicIdsNotIn(final Set<UUID> magicIds) {
        return magicRepository.findByIdIsNotIn(magicIds).stream().map(MagicEO::getId).toList();
    }
}

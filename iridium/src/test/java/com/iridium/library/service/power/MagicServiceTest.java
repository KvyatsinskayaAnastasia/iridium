package com.iridium.library.service.power;

import com.iridium.library.entity.power.SpellEO;
import com.iridium.library.mapper.power.MagicMapper;
import com.iridium.library.repository.power.MagicRepository;
import com.iridium.library.repository.power.SpellRepository;
import com.iridium.openapi.model.AddMagicRequest;
import com.iridium.openapi.model.LeveledSpells;
import org.instancio.Instancio;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MagicServiceTest {

    @Autowired
    MagicService magicService;
    @Autowired
    MagicRepository magicRepository;
    @Autowired
    SpellRepository spellRepository;
    @Autowired
    MagicMapper magicMapper;

    private static final UUID MAGIC_ID = UUID.fromString("3f5f2b5d-859d-4471-9b55-1e4a0a4b6a0b");

    @Test
    @DisplayName("Test success magic save")
    public void testSuccessCreateMagic() {
        final var addMagicRequest = Instancio.of(AddMagicRequest.class).create();
        final var magicId = magicService.saveMagic(addMagicRequest);
        final var magicEO = magicRepository.findByIdWithSpells(magicId).orElse(null);
        assertNotNull(magicEO);
        assertEquals(addMagicRequest.getDescription(), magicEO.getDescription());
        assertEquals(addMagicRequest.getName(), magicEO.getName());
        assertEquals(addMagicRequest.getSpells().size(), magicEO.getSpells().size());
    }

    @Test
    @DisplayName("Test success get magic")
    public void testSuccessGetMagic() {
        final var magic = magicService.getMagicById(MAGIC_ID);
        final var magicEO = magicRepository.findByIdWithSpells(MAGIC_ID).orElse(null);
        assertNotNull(magic);
        assertNotNull(magicEO);
        assertEquals(magic.getDescription(), magicEO.getDescription());
        assertEquals(magic.getName(), magicEO.getName());

        final var spellEOs = magicEO.getSpells();
        magic.getLeveledSpells().stream().map(LeveledSpells::getSpells).flatMap(Collection::stream).forEach(spellResponse -> {
            final var spellEO = spellEOs.stream().filter(sp -> sp.getId().equals(spellResponse.getId())).findFirst().orElse(null);
            assertNotNull(spellEO);
            assertEquals(spellResponse.getDescription(), spellEO.getDescription());
            assertEquals(spellResponse.getName(), spellEO.getName());
        });
    }

    @Test
    @DisplayName("Test success delete magic")
    public void testSuccessDeleteMagic() {
        final var magicEO = magicRepository.save(magicMapper.toMagicEO(Instancio.of(AddMagicRequest.class).create()));
        magicService.deleteMagic(magicEO.getId());
        assertNull(magicRepository.findById(magicEO.getId()).orElse(null));
        assertTrue(spellRepository.findByIdIn(magicEO.getSpells().stream().map(SpellEO::getId).collect(Collectors.toSet())).isEmpty());
    }
}

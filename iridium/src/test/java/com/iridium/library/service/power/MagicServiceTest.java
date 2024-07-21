package com.iridium.library.service.power;

import com.iridium.library.entity.power.SpellEO;
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

    @Test
    @DisplayName("test magic save, get and delete")
    public void testSuccessCreateGetDeleteAbility() {
        final var addMagicRequest = Instancio.of(AddMagicRequest.class).create();
        final var magicId = magicService.saveMagic(addMagicRequest);
        final var magicEO = magicRepository.findByIdWithSpells(magicId).orElse(null);
        assertNotNull(magicEO);
        assertEquals(addMagicRequest.getDescription(), magicEO.getDescription());
        assertEquals(addMagicRequest.getName(), magicEO.getName());
        assertEquals(addMagicRequest.getSpells().size(), magicEO.getSpells().size());

        final var magic = magicService.getMagicById(magicId);
        assertNotNull(magic);
        assertEquals(magic.getDescription(), magicEO.getDescription());
        assertEquals(magic.getName(), magicEO.getName());

        final var spellEOs = magicEO.getSpells();
        magic.getLeveledSpells().stream().map(LeveledSpells::getSpells).flatMap(Collection::stream).forEach(spellResponse -> {
            final var spellEO = spellEOs.stream().filter(sp -> sp.getId().equals(spellResponse.getId())).findFirst().orElse(null);
            assertNotNull(spellEO);
            assertEquals(spellResponse.getDescription(), spellEO.getDescription());
            assertEquals(spellResponse.getName(), spellEO.getName());
        });

        magicService.deleteMagic(magicId);
        assertNull(magicRepository.findById(magicId).orElse(null));
        assertTrue(spellRepository.findByIdIn(magicEO.getSpells().stream().map(SpellEO::getId).toList()).isEmpty());
    }
}

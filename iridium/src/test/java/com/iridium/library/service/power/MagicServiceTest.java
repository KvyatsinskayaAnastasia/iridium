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
    private MagicService magicService;
    @Autowired
    private MagicRepository magicRepository;
    @Autowired
    private SpellRepository spellRepository;
    @Autowired
    private MagicMapper magicMapper;

    @Test
    public void testSaveMagic() {
        final var addMagicRequest = Instancio.of(AddMagicRequest.class).create();
        final var magicId = magicService.saveMagic(addMagicRequest);
        final var magicEO = magicRepository.findByIdWithSpells(magicId).orElse(null);
        assertNotNull(magicEO);
        assertEquals(addMagicRequest.getDescription(), magicEO.getDescription());
        assertEquals(addMagicRequest.getName(), magicEO.getName());
        assertEquals(addMagicRequest.getSpells().size(), magicEO.getSpells().size());
    }

    @Test
    public void testGetMagicById() {
        final var savedMagicEO = magicRepository.save(magicMapper.toMagicEO(Instancio.of(AddMagicRequest.class).create()));
        final var magicEO = magicRepository.findByIdWithSpells(savedMagicEO.getId()).orElse(null);
        assertNotNull(savedMagicEO);
        assertNotNull(magicEO);
        assertEquals(savedMagicEO.getDescription(), magicEO.getDescription());
        assertEquals(savedMagicEO.getName(), magicEO.getName());

        final var spellEOs = magicEO.getSpells();
        savedMagicEO.getSpells().forEach(spellResponse -> {
            final var spellEO = spellEOs.stream().filter(sp -> sp.getId().equals(spellResponse.getId())).findFirst().orElse(null);
            assertNotNull(spellEO);
            assertEquals(spellResponse.getDescription(), spellEO.getDescription());
            assertEquals(spellResponse.getName(), spellEO.getName());
        });
    }

    @Test
    public void testDeleteMagic() {
        final var magicEO = magicRepository.save(magicMapper.toMagicEO(Instancio.of(AddMagicRequest.class).create()));
        magicService.deleteMagic(magicEO.getId());
        assertNull(magicRepository.findById(magicEO.getId()).orElse(null));
        assertTrue(spellRepository.findByIdIn(magicEO.getSpells().stream().map(SpellEO::getId).collect(Collectors.toSet())).isEmpty());
    }
}

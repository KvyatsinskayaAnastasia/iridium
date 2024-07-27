package com.iridium.library.service.character;

import com.iridium.library.entity.power.SpellEO;
import com.iridium.library.repository.character.RaceRepository;
import com.iridium.library.repository.power.MagicRepository;
import com.iridium.library.repository.power.SpellRepository;
import com.iridium.library.service.power.MagicService;
import com.iridium.openapi.model.AddMagicRequest;
import com.iridium.openapi.model.LeveledSpells;
import com.iridium.openapi.model.RaceRequest;
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
public class RaceServiceTest {

    @Autowired
    RaceService raceService;
    @Autowired
    RaceRepository raceRepository;

    @Test
    @DisplayName("test race save, get and delete")
    public void testSuccessCreateGetDeleteAbility() {
        final var addRaceRequest = Instancio.of(RaceRequest.class).create();
        final var raceId = raceService.saveRace(addRaceRequest);
        final var raceEO = raceRepository.findById(raceId).orElse(null);
        assertNotNull(raceEO);
        assertEquals(addRaceRequest.getDescription(), raceEO.getDescription());
        assertEquals(addRaceRequest.getName(), raceEO.getName());

        final var race = raceService.getRaceById(raceId);
        assertNotNull(race);
        assertEquals(race.getDescription(), raceEO.getDescription());
        assertEquals(race.getName(), raceEO.getName());

        raceService.deleteRace(raceId);
        assertNull(raceRepository.findById(raceId).orElse(null));
    }
}

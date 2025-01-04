package com.iridium.library.service.character;

import com.iridium.library.repository.character.RaceRepository;
import com.iridium.library.repository.power.AbilityRepository;
import com.iridium.library.repository.power.MagicRepository;
import com.iridium.library.service.power.AbilityService;
import com.iridium.library.service.power.MagicService;
import com.iridium.openapi.model.RaceAbility;
import com.iridium.openapi.model.RaceAbilityRequest;
import com.iridium.openapi.model.RaceMagic;
import com.iridium.openapi.model.RaceMagicRequest;
import com.iridium.openapi.model.RaceRequest;
import com.iridium.openapi.model.RaceResponse;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RaceServiceTest {
    @Autowired
    MagicRepository magicRepository;
    @Autowired
    AbilityRepository abilityRepository;

    @Autowired
    RaceService raceService;
    @Autowired
    RaceRepository raceRepository;

    @Test
    @DisplayName("test race save, get and delete")
    public void testSuccessCreateGetDeleteRace() {
        final var addRaceRequest = createRaceRequest();
        final var raceId = raceService.saveRace(addRaceRequest);
        final var raceEO = raceRepository.findById(raceId).orElse(null);
        assertNotNull(raceEO);
        assertEquals(addRaceRequest.getDescription(), raceEO.getDescription());
        assertEquals(addRaceRequest.getName(), raceEO.getName());
        assertEquals(addRaceRequest.getAppearance(), raceEO.getAppearance());

        final var race = raceService.getRaceById(raceId, RaceResponse.class);
        assertNotNull(race);
        assertEquals(race.getDescription(), addRaceRequest.getDescription());
        assertEquals(race.getName(), addRaceRequest.getName());
        assertEquals(race.getAppearance(), addRaceRequest.getAppearance());
        assertNotNull(race.getBaseMagic());
        assertTrue(race.getBaseMagic().stream().map(RaceMagic::getId).toList()
                .containsAll(addRaceRequest.getBaseMagic().stream().map(RaceMagicRequest::getId).toList()));
        assertNotNull(race.getUnavailableMagic());
        assertTrue(race.getUnavailableMagic().stream().map(RaceMagic::getId).toList()
            .containsAll(addRaceRequest.getUnavailableMagic().stream().map(RaceMagicRequest::getId).toList()));
        assertNotNull(race.getRaceAbilities());
        assertTrue(race.getRaceAbilities().stream().map(RaceAbility::getId).toList()
            .containsAll(addRaceRequest.getRaceAbilities().stream().map(RaceAbilityRequest::getId).toList()));

        raceService.deleteRace(raceId);
        assertNull(raceRepository.findById(raceId).orElse(null));
        addRaceRequest.getBaseMagic().forEach(magic ->
            assertNotNull(magicRepository.findById(magic.getId()).orElse(null)));
        addRaceRequest.getUnavailableMagic().forEach(magic ->
            assertNotNull(magicRepository.findById(magic.getId()).orElse(null)));
        addRaceRequest.getRaceAbilities().forEach(ability ->
            assertNotNull(abilityRepository.findById(ability.getId()).orElse(null)));
    }

    private RaceRequest createRaceRequest() {
        final var raceRequest = new RaceRequest();
        raceRequest.setName("test name");
        raceRequest.setAppearance("test appearance");
        raceRequest.setDescription("test description");
        raceRequest.setBaseMagic(Set.of(new RaceMagicRequest().id(UUID.fromString("d834b8a8-c7a3-4387-a7a6-1c59a4f0603b"))));
        raceRequest.setUnavailableMagic(Set.of(new RaceMagicRequest().id(UUID.fromString("b4a59f7f-2dbe-4a14-b1cb-efa19d7022c0"))));
        raceRequest.setRaceAbilities(Set.of(new RaceAbilityRequest()
            .id(UUID.fromString("45d6b331-2679-41e0-9410-6bbbe498fb29"))
            .bonus(1)));
        return raceRequest;
    }
}

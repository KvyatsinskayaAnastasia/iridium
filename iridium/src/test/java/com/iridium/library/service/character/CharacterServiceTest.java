package com.iridium.library.service.character;

import com.iridium.library.entity.power.SpellEO;
import com.iridium.library.repository.character.CharacterRepository;
import com.iridium.library.repository.character.RaceRepository;
import com.iridium.library.repository.power.AbilityRepository;
import com.iridium.library.repository.power.SpellRepository;
import com.iridium.openapi.model.CharacterAbility;
import com.iridium.openapi.model.CharacterMagic;
import com.iridium.openapi.model.CharacterSpell;
import com.iridium.openapi.model.CharacterType;
import com.iridium.openapi.model.CreateCharacterRequest;
import com.iridium.openapi.model.Gender;
import com.iridium.openapi.model.User;
import com.iridium.security.service.user.AuthorizationService;
import org.instancio.Instancio;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacterServiceTest {
    @Autowired
    private SpellRepository spellRepository;
    @Autowired
    private AbilityRepository abilityRepository;
    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterRepository characterRepository;

    @MockBean
    AuthorizationService authorizationService;

    @Test
    public void testSaveGetDeleteCharacter() throws IOException {
        final var user = Instancio.of(User.class).create();
        when(authorizationService.getCurrentUser()).thenReturn(user);
        final var addCharacterRequest = createCharacterRequest();
        final var characterId = characterService.saveCharacter(addCharacterRequest, null);
        final var characterEO = characterRepository.findById(characterId).orElse(null);
        assertNotNull(characterEO);
        assertEquals(addCharacterRequest.getName(), characterEO.getName());
        assertEquals(addCharacterRequest.getAge().intValue(), characterEO.getAge());
        assertEquals(addCharacterRequest.getRaceId(), characterEO.getRace().getId());
        assertEquals(addCharacterRequest.getGender().toString(), characterEO.getGender().toString());
        assertEquals(addCharacterRequest.getAppearance(), characterEO.getAppearance());
        assertEquals(addCharacterRequest.getTemper(), characterEO.getTemper());
        assertEquals(addCharacterRequest.getBiography(), characterEO.getBiography());
        assertEquals(addCharacterRequest.getAim(), characterEO.getAim());
        assertEquals(addCharacterRequest.getNationality(), characterEO.getNationality());

        final var character = characterService.getCharacterById(characterId);
        assertNotNull(character);
        assertEquals(addCharacterRequest.getName(), character.getName());
        assertEquals(addCharacterRequest.getAge(), character.getAge());
        assertEquals(addCharacterRequest.getRaceId(), character.getRace().getId());
        assertEquals(addCharacterRequest.getGender().toString(), character.getGender().toString());
        assertEquals(addCharacterRequest.getAppearance(), character.getAppearance());
        assertEquals(addCharacterRequest.getTemper(), character.getTemper());
        assertEquals(addCharacterRequest.getBiography(), character.getBiography());
        assertEquals(addCharacterRequest.getAim(), character.getAim());
        assertEquals(addCharacterRequest.getNationality(), character.getNationality());
        assertNotNull(character.getMagic());
        assertTrue(character.getMagic().stream()
                .map(CharacterMagic::getSpells)
                .flatMap(Collection::stream)
                .map(CharacterSpell::getId).toList()
            .containsAll(addCharacterRequest.getSpells().stream().toList()));
        assertNotNull(character.getAbilities());
        assertTrue(character.getAbilities().stream().map(CharacterAbility::getAbilityId).toList()
            .containsAll(addCharacterRequest.getAbilities().stream().map(CharacterAbility::getAbilityId).toList()));

        characterService.deleteCharacter(characterId);
        assertNull(characterRepository.findById(characterId).orElse(null));
        addCharacterRequest.getSpells().forEach(spellId ->
            assertNotNull(spellRepository.findById(spellId).orElse(null)));
        addCharacterRequest.getAbilities().forEach(ability ->
            assertNotNull(abilityRepository.findById(ability.getAbilityId()).orElse(null)));
        assertNotNull(raceRepository.findById(addCharacterRequest.getRaceId()).orElse(null));
    }

    @Test
    public void testSuccessGenerateCharacter() {
        var preGeneratedCharacter = characterService.generateCharacter();
        assertNotNull(preGeneratedCharacter);
        assertNotNull(preGeneratedCharacter.getName());
        assertNotNull(preGeneratedCharacter.getAge());
        assertNotNull(preGeneratedCharacter.getRaceId());
        assertNotNull(preGeneratedCharacter.getGender());
        assertNotNull(preGeneratedCharacter.getAbilities());
        assertNotNull(preGeneratedCharacter.getTemper());
    }

    private CreateCharacterRequest createCharacterRequest() {
        final var characterRequest = new CreateCharacterRequest();
        characterRequest.setCharacterType(CharacterType.PLAYER);
        characterRequest.setName("test name");
        characterRequest.setAge(23);
        characterRequest.setRaceId(UUID.fromString("3eeb7e9e-6bcd-4f72-bb62-5d00c8cb2b2d"));
        characterRequest.setGender(Gender.MALE);
        characterRequest.setAppearance("test appearance");
        characterRequest.setSpells(Set.of(UUID.fromString("a1b2c3d4-e5f6-7890-1234-56789abcdef0"),
            UUID.fromString("b1c2d3e4-f5a6-7890-1234-56789abcdef1")));
        characterRequest.setAbilities(Set.of(
            new CharacterAbility().abilityId(UUID.fromString("45d6b331-2679-41e0-9410-6bbbe498fb29")).level(3),
            new CharacterAbility().abilityId(UUID.fromString("a9c78db7-cc95-4c2e-8e46-13be3115f008")).level(3)));
        characterRequest.setTemper("test temper");
        characterRequest.setBiography("test biography");
        characterRequest.setAim("test aim");
        characterRequest.setNationality("test nationality");
        return characterRequest;
    }
}

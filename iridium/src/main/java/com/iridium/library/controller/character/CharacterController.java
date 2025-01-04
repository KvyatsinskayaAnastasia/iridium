package com.iridium.library.controller.character;

import com.iridium.library.service.character.impl.BaseCharacterService;
import com.iridium.openapi.api.CharacterApi;
import com.iridium.openapi.model.Character;
import com.iridium.openapi.model.CreateCharacterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CharacterController implements CharacterApi {

    private final BaseCharacterService characterService;

    @Override
    public final ResponseEntity<UUID> addCharacter(final CreateCharacterRequest createCharacterRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.saveCharacter(createCharacterRequest));
    }

    @Override
    public final ResponseEntity<List<Character>> getAllCharacters() {
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    @Override
    public final ResponseEntity<Character> getCharacter(final UUID id) {
        return ResponseEntity.ok(characterService.getCharacterById(id));
    }
}

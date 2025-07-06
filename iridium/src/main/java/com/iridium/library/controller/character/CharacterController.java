package com.iridium.library.controller.character;

import com.iridium.library.service.character.impl.BaseCharacterService;
import com.iridium.openapi.api.CharacterApi;
import com.iridium.openapi.model.Character;
import com.iridium.openapi.model.CreateCharacterRequest;
import com.iridium.openapi.model.GeneratorSettings;
import com.iridium.openapi.model.PreGeneratedCharacter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CharacterController implements CharacterApi {

    private final BaseCharacterService characterService;

    @Override
    public final ResponseEntity<UUID> saveCharacter(final CreateCharacterRequest character,
                                                    final MultipartFile image) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(characterService.saveCharacter(character, image));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public final ResponseEntity<List<Character>> getAllCharacters() {
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    @Override
    public final ResponseEntity<Character> getCharacter(final UUID id) {
        return ResponseEntity.ok(characterService.getCharacterById(id));
    }

    @Override
    public final ResponseEntity<PreGeneratedCharacter> generateCharacter(final GeneratorSettings generatorSettings) {
        return ResponseEntity.ok(characterService.generateCharacter());
    }
}

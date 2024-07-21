package com.iridium.library.service.character.impl;

import com.iridium.library.mapper.character.CharacterMapper;
import com.iridium.library.repository.character.CharacterRepository;
import com.iridium.library.service.character.CharacterService;
import com.iridium.openapi.model.Character;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Base service to work with character model.
 * See the {@link com.iridium.library.service.character.CharacterService}.
 * @author Kviatsinskaya Anastasia
 */
@Service
@RequiredArgsConstructor
public class BaseCharacterService implements CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    @Override
    public final UUID saveCharacter(final Character character) {
        return characterRepository.save(characterMapper.toCharacterEO(character)).getId();
    }

    @Override
    public final List<Character> getAllCharacters() {
        return characterMapper.toCharacterList(characterRepository.findAll());
    }

    @Override
    public final Character getCharacterById(final UUID id) {
        return characterMapper.toCharacter(characterRepository.findById(id).orElse(null));
    }
}

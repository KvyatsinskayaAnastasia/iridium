package com.iridium.library.repository.character;

import com.iridium.library.entity.character.CharacterAbilityEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CharacterAbilityRepository extends JpaRepository<CharacterAbilityEO, UUID> {
}

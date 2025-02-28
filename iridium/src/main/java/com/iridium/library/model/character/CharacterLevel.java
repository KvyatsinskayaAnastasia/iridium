package com.iridium.library.model.character;

import lombok.Getter;

@Getter
public enum CharacterLevel {
    SMALL(0, 5, 2, 2),
    MEDIUM(1, 10, 3, 3),
    BIG(5, 15, 5, 5),
    GREAT(5, 20, 5, 6);

    private final int minAbilityLevel;
    private final int maxAbilityLevel;
    private final int maxMagicLevel;
    private final int maxFightSkillsCount;

    CharacterLevel(final int minAbilityLevel, final int maxAbilityLevel,
                   final int maxMagicLevel, final int maxFightSkillsCount) {
        this.minAbilityLevel = minAbilityLevel;
        this.maxAbilityLevel = maxAbilityLevel;
        this.maxMagicLevel = maxMagicLevel;
        this.maxFightSkillsCount = maxFightSkillsCount;
    }
}

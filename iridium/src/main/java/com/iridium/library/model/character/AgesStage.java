package com.iridium.library.model.character;

import lombok.Getter;

@Getter
public enum AgesStage {
    CHILD(8, 17),
    YANG(18, 50),
    ADULT(51, 150),
    OLD(151, 300);

    private final int minAges;
    private final int maxAges;

    AgesStage(final int minAges, final int maxAges) {
        this.minAges = minAges;
        this.maxAges = maxAges;
    }
}

package com.iridium.library.entity.power;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

import static com.iridium.library.entity.ValidationConstants.DESCRIPTION_LENGTH;

@Entity
@Getter
@Setter
public class SpellEO {

    /**
     * Max spell level.
     */
    public static final int MAX_SPELL_LEVEL = 5;
    /**
        * Min spell level.
     */
    public static final int MIN_SPELL_LEVEL = 5;

    /**
     * Id.
     */
    @Id
    @GeneratedValue
    private UUID id;

    /**
     * Name.
     */
    private String name;

    /**
     * Description.
     */
    @Column(length = DESCRIPTION_LENGTH)
    private String description;

    /**
     * Spell level.
     */
    @Max(MAX_SPELL_LEVEL)
    @Min(MIN_SPELL_LEVEL)
    private int level;
}


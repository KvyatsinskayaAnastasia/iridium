package com.iridium.library.entity.power;

import com.iridium.common.entity.AbstractEntityEO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static com.iridium.library.entity.ValidationConstants.DESCRIPTION_LENGTH;

@Entity
@Table(name = "spell")
@Getter
@Setter
public class SpellEO extends AbstractEntityEO {

    public static final int MAX_SPELL_LEVEL = 5;
    public static final int MIN_SPELL_LEVEL = 5;

    private String name;

    @Column(length = DESCRIPTION_LENGTH)
    private String description;

    /**
     * Spell level.
     */
    @Max(MAX_SPELL_LEVEL)
    @Min(MIN_SPELL_LEVEL)
    private int level;

    @ManyToOne
    @JoinColumn(name = "magic_id")
    private MagicEO magic;
}


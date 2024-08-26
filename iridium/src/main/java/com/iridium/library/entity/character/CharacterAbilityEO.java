package com.iridium.library.entity.character;

import com.iridium.common.entity.AbstractEntityEO;
import com.iridium.library.entity.power.AbilityEO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "character_ability")
@Getter
@Setter
public class CharacterAbilityEO extends AbstractEntityEO {

    @OneToOne
    @JoinColumn(name = "ability_id", nullable = false)
    private AbilityEO ability;

    @ManyToOne
    @JoinColumn(name = "character_id", nullable = false)
    private CharacterEO character;

    /**
     * Current level of ability.
     */
    private int level;
}

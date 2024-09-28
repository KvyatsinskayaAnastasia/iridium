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
@Table(name = "race_ability")
@Getter
@Setter
public class RaceAbilityEO extends AbstractEntityEO {

    @ManyToOne
    @JoinColumn(name = "ability_id", nullable = false)
    private AbilityEO ability;

    @OneToOne
    @JoinColumn(name = "race_id", nullable = false)
    private RaceEO race;

    /**
     * Race bonus for ability level.
     */
    private int bonus;
}

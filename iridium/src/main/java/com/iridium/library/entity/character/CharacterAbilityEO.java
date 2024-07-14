package com.iridium.library.entity.character;

import com.iridium.library.entity.power.AbilityEO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CharacterAbilityEO {

    /**
     * Ability, see {@link com.iridium.library.entity.power.AbilityEO}.
     */
    @OneToOne
    private AbilityEO abilityEO;

    /**
     * Current level of ability.
     */
    private int level;
}

package com.iridium.library.entity.character;

import com.iridium.common.entity.AbstractEntityEO;
import com.iridium.library.entity.power.MagicEO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "race")
@Getter
@Setter
public class RaceEO extends AbstractEntityEO {

    private String name;

    private String description;

    private String appearance;

    @ManyToMany
    @JoinTable(
        name = "base_race_magic",
        joinColumns = @JoinColumn(name = "race_id"),
        inverseJoinColumns = @JoinColumn(name = "magic_id")
    )
    private Set<MagicEO> baseMagic;

    @ManyToMany
    @JoinTable(
        name = "unavailable_race_magic",
        joinColumns = @JoinColumn(name = "race_id"),
        inverseJoinColumns = @JoinColumn(name = "magic_id")
    )
    private Set<MagicEO> unavailableMagic;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private Set<RaceAbilityEO> raceAbilities;
}

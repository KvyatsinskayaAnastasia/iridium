package com.iridium.library.entity.character;

import com.iridium.common.entity.AbstractEntityEO;
import com.iridium.library.entity.power.SpellEO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "character")
@Getter
@Setter
public class CharacterEO extends AbstractEntityEO {

    private String name;

    private int age;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    private RaceEO race;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private GenderEO gender;

    private String appearance;

    @ManyToMany
    @JoinTable(
        name = "character_spell",
        joinColumns = @JoinColumn(name = "character_id"),
        inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private Set<SpellEO> spells;

    @OneToMany(mappedBy = "character")
    private Set<CharacterAbilityEO> characterAbilities;

    private String temper;

    private String biography;

    private String aim;

    private String nationality;

    private byte[] image;
}

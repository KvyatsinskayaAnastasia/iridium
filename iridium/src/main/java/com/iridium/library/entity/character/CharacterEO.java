package com.iridium.library.entity.character;

import com.iridium.library.entity.power.SpellEO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
public class CharacterEO {
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
     * Age.
     */
    private int age;

    /**
     * Race, see {@link com.iridium.library.entity.character.RaceEO}.
     */
    @ManyToOne
    private RaceEO raceEO;

    /**
     * Gender, see {@link com.iridium.library.entity.character.Gender}.
     */
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * Appearance.
     */
    private String appearance;

    /**
     * Spells that character can use, see {@link com.iridium.library.entity.power.SpellEO}.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "character_spell",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "spell_id")
    )
    private Set<SpellEO> spells;

    /**
     * Abilities, that character can use, and theirs level.
     * See {@link com.iridium.library.entity.character.CharacterAbilityEO}.
     */
    @OneToMany
    private Set<CharacterAbilityEO> abilities;

    /**
     * Temper.
     */
    private String temper;

    /**
     * Biography.
     */
    private String biography;

    /**
     * Aim.
     */
    private String aim;

    /**
     * Nationally.
     */
    private String nationality;

    /**
     * Image of character.
     */
    private byte[] image;
}

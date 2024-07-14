package com.iridium.library.entity.power;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

import static com.iridium.library.entity.ValidationConstants.DESCRIPTION_LENGTH;

@Entity
@Getter
@Setter
public class MagicEO {
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
     * Spells for magic.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SpellEO> spellEOS;
}

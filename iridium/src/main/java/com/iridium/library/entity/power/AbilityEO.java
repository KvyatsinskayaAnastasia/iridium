package com.iridium.library.entity.power;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

import static com.iridium.library.entity.ValidationConstants.DESCRIPTION_LENGTH;

@Entity
@Getter
@Setter
public class AbilityEO {
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
     * Ability type see the {@link com.iridium.library.entity.power.AbilityTypeEO}.
     *
     */
    @Enumerated(EnumType.STRING)
    private AbilityTypeEO abilityTypeEO;

    /**
     * Skills, that ability provide ordered by min level to learn them.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("MIN_LEVEL ASC")
    private Set<SkillEO> skillEOS;
}

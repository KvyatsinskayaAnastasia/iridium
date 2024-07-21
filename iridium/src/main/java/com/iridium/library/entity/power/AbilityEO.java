package com.iridium.library.entity.power;

import com.iridium.library.entity.AbstractEntityEO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static com.iridium.library.entity.ValidationConstants.DESCRIPTION_LENGTH;

@Entity
@Table(name = "ability")
@Getter
@Setter
public class AbilityEO extends AbstractEntityEO {

    private String name;

    @Column(length = DESCRIPTION_LENGTH)
    private String description;

    @Column(name = "ability_type")
    @Enumerated(EnumType.STRING)
    private AbilityTypeEO abilityType;

    @OneToMany(mappedBy = "ability", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SkillEO> skills;
}

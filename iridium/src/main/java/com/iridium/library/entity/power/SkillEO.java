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

import static com.iridium.library.entity.ValidationConstants.DESCRIPTION_LENGTH;

@Entity
@Table(name = "skill")
@Getter
@Setter
public class SkillEO extends AbstractEntityEO {

    @Column(length = DESCRIPTION_LENGTH)
    private String description;

    /**
     * Min level of ability to get skill.
     */
    private Integer minLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ability_id")
    private AbilityEO ability;
}

package com.iridium.library.entity.power;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static com.iridium.library.entity.ValidationConstants.DESCRIPTION_LENGTH;

@Entity
@Getter
@Setter
public class SkillEO {
    /**
     * Id.
     */
    @Id
    @GeneratedValue
    private UUID id;

    /**
     * Description.
     */
    @Column(length = DESCRIPTION_LENGTH)
    private String description;

    /**
     * Min level of ability to get skill.
     */
    private int minLevel;
}

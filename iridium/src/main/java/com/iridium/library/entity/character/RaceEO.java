package com.iridium.library.entity.character;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class RaceEO {
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
    private String description;
}

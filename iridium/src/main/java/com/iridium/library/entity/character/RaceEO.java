package com.iridium.library.entity.character;

import com.iridium.library.entity.AbstractEntityEO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "race")
@Getter
@Setter
public class RaceEO extends AbstractEntityEO {

    private String name;

    private String description;
}

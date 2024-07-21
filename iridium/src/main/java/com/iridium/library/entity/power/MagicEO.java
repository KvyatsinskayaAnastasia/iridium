package com.iridium.library.entity.power;

import com.iridium.library.entity.AbstractEntityEO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static com.iridium.library.entity.ValidationConstants.DESCRIPTION_LENGTH;

@Entity
@Table(name = "magic")
@Getter
@Setter
public class MagicEO extends AbstractEntityEO {

    private String name;

    @Column(length = DESCRIPTION_LENGTH)
    private String description;

    @OneToMany(mappedBy = "magic", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SpellEO> spells;
}

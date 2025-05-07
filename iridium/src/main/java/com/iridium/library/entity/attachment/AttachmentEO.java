package com.iridium.library.entity.attachment;

import com.iridium.common.entity.AbstractEntityEO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "attachment")
public class AttachmentEO extends AbstractEntityEO {

    private String name;

    private String path;
}

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

    /**
     * Base constructor for attachment.
     * @param name file name
     * @param path file path
     */
    public AttachmentEO(final String name, final String path) {
        this.name = name;
        this.path = path;
    }

    /**
     * Default constructor.
     */
    public AttachmentEO() {

    }
}

package com.iridium.library.service.attachment;

import com.iridium.library.entity.attachment.AttachmentEO;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * Service for work with attached data.
 */
public interface AttachmentService {

    /**
     * Save attachment.
     * @param file sent file
     * @return new attachment id
     */
    AttachmentEO saveAttachment(MultipartFile file);

    /**
     * Get attachment entity by id.
     * @param attachmentId attachment id
     * @return attachment entity
     */
    AttachmentEO getAttachmentById(UUID attachmentId);
}

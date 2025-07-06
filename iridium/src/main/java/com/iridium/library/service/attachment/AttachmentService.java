package com.iridium.library.service.attachment;

import com.iridium.library.entity.attachment.AttachmentEO;
import com.iridium.library.model.attachment.AttachmentType;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

/**
 * Service for work with attached data.
 */
public interface AttachmentService {

    /**
     * Save attachment.
     * @param file sent file
     * @param attachmentType type of attachment
     * @return new attachment id
     */
    AttachmentEO saveAttachment(MultipartFile file, AttachmentType attachmentType) throws IOException;
}

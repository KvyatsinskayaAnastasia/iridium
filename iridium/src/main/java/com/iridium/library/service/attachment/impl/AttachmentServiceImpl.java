package com.iridium.library.service.attachment.impl;

import com.iridium.library.entity.attachment.AttachmentEO;
import com.iridium.library.repository.attachment.AttachmentRepository;
import com.iridium.library.service.attachment.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    @Value("${attachments.directory}")
    private String directory;

    private final AttachmentRepository attachmentRepository;

    @Override
    public final AttachmentEO saveAttachment(final MultipartFile file) {
        File uploadDirectory = new File(directory);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }
        String curDate = LocalDateTime.now().toString();
        String fileName = "character_" + curDate;
        try {
            file.transferTo(new File(uploadDirectory + "\\" + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AttachmentEO attachment = new AttachmentEO();
        attachment.setName(fileName);
        attachment.setPath(directory + "\\characters");
        return attachmentRepository.save(attachment);
    }

    @Override
    public final AttachmentEO getAttachmentById(final UUID attachmentId) {
        return attachmentRepository.findById(attachmentId).orElseThrow();
    }
}

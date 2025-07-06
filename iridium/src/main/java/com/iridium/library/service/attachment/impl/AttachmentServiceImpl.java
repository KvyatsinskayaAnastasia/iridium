package com.iridium.library.service.attachment.impl;

import com.iridium.library.entity.attachment.AttachmentEO;
import com.iridium.library.model.attachment.AttachmentType;
import com.iridium.library.repository.attachment.AttachmentRepository;
import com.iridium.library.service.attachment.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    @Value("${attachments.directory}")
    private String directory;

    private final AttachmentRepository attachmentRepository;

    private static final String FILE_NAME = "%s_%d";
    private static final String MAIN_DIRECTORY = "attaches";
    private static final String ABSOLUTE_PATH = "%s\\" + MAIN_DIRECTORY + "\\%s";


    @Override
    public final AttachmentEO saveAttachment(final MultipartFile file, final AttachmentType attachmentType)
            throws IOException {
        String absolutePath = String.format(ABSOLUTE_PATH, directory, attachmentType.getValue());
        File uploadDirectory = new File(absolutePath);
        String fileName = String.format(FILE_NAME, attachmentType.getValue(), System.currentTimeMillis());
        file.transferTo(new File(uploadDirectory, fileName));
        return attachmentRepository.save(new AttachmentEO(fileName, attachmentType.getValue()));
    }
}

package com.iridium.library.repository.attachment;

import com.iridium.library.entity.attachment.AttachmentEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<AttachmentEO, UUID> {
}

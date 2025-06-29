package org.example.prj1.service;

import org.example.prj1.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;
}

package org.example.prj1.service;

import org.example.prj1.entity.Attachment;
import org.example.prj1.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    private AttachmentRepository attachmentRepository;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }
    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence" + fileName);
            }

            Attachment attachment = new Attachment(
                    fileName,
                    file.getContentType(),
                    file.getBytes());
            return attachmentRepository.save(attachment);
        } catch (Exception e)
        {
            throw new Exception("Could not save file:" + fileName,e);
        }
    }
}

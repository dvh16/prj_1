package org.example.prj1.controller;

import org.example.prj1.dto.response.ResponseData;
import org.example.prj1.entity.Attachment;
import org.example.prj1.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AttachmentController {

    private AttachmentService attachmentService;
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }
    @PostMapping("/upload")
    public ResponseData uploadAttachment(@RequestParam("file") MultipartFile file) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.saveAttachment(file);

        return new ResponseData(attachment.getFilename(), file.getContentType(), file.getSize());

    }
}

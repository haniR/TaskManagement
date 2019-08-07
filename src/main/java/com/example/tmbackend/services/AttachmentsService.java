/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.services;

import com.example.tmbackend.daos.AttachmentsDAO;
import com.example.tmbackend.models.Attachment;
import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class AttachmentsService {

    @Autowired
    private AttachmentsDAO attachmentsDAO;

    public Attachment getAttachmentById(int id) {
        Attachment obj = attachmentsDAO.getAttachmentById(id);
        return obj;
    }

    public List<Attachment> getAllAttachments() {
        return attachmentsDAO.getAttachments();
    }
    public List<Attachment> getAttachmentforTask(int tasksId) {
        return attachmentsDAO.getAttachmentforTask(tasksId);
    }

    public synchronized boolean addAttachment(String path,Attachment attachment) {
      
            attachmentsDAO.addAttachment(path,attachment);
            return true;
        
    }

    public boolean updateAttachment(Attachment attachments) {
        return attachmentsDAO.updateAttachment(attachments);
    }

    public boolean deleteAttachment(int id) {
        return attachmentsDAO.deleteAttachment(id);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.controllers;

import com.example.tmbackend.general.Response;
import com.example.tmbackend.models.Attachment;
import com.example.tmbackend.services.AttachmentsService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author ASUS
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AttachmentsController {

    @Autowired
    private AttachmentsService attachmentsService;

    @GetMapping("/Attachment/{id}")
    public Response<Attachment> getAttachmentById(@PathVariable("id") Integer id) {
        Attachment attachments = attachmentsService.getAttachmentById(id);
        return new Response<Attachment>(true, "", false, attachments);
    }

    @GetMapping(value = "/Attachments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<Attachment>> getAllAttachments() {
        Response<List<Attachment>> resp;
        List<Attachment> list = attachmentsService.getAllAttachments();
        if (list.isEmpty()) {
            resp = new Response<>(false, "THere's an errror show list doesnt complete", true, list);
        } else {
            resp = new Response<>(true, "show completed", true, list);
        }
        return resp;
    }

    @GetMapping(value = "/AttachmentsTask/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<Attachment>> getAttachmentsforTask(@PathVariable("id") Integer id) {
        Response<List<Attachment>> resp;
        List<Attachment> list = attachmentsService.getAttachmentforTask(id);
        if (list.isEmpty()) {
            resp = new Response<>(false, "There's no Attachement for this project", false, list);
        } else {
            resp = new Response<>(true, "show completed", true, list);
        }
        return resp;
    }

    @PostMapping(value = "/Attachement", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<Attachment> addAttachment(@RequestBody Attachment attachment) throws IOException {
        Response<Attachment> resp = new Response<>();
        File file = new File("");
//        System.err.println(file.getAbsolutePath());
        String fullPath = file.getAbsolutePath();
//        System.err.println(fullPath.substring(0, fullPath.lastIndexOf("\\")));
        String path = fullPath.substring(0, fullPath.lastIndexOf("\\")) + "\\webapps\\attachments\\";
        File f = new File(path + attachment.getName());
        byte[] result = Base64.getDecoder().decode(attachment.getSrc().split(",")[1]);

        FileOutputStream os = new FileOutputStream(f);
        os.write(result);
        os.close();

        boolean flag = attachmentsService.addAttachment(path, attachment);
        if (flag == false) {
            resp = new Response(false, "THere's an errror add doesnt complete", true, f);
        } else {
            resp = new Response(true, "add completed", true, f);
            attachment.setSrc(attachment.getName());
            resp.setData(attachment);

        }
        return resp;
    }

    @PutMapping("/Attachment")
    public Response<Attachment> updateAttachment(@RequestBody Attachment attachments) {
        Response<Attachment> resp = new Response<>();
        try {
            boolean flag = attachmentsService.updateAttachment(attachments);
            if (flag == false) {
                resp = new Response(false, "There's an errror update doesnt complete", true, attachments);
            } else {
                resp = new Response(true, "update completed", true, attachments);
            }
        } catch (Exception ex) {
            resp = new Response(false, ex.getMessage(), true, attachments);
        }
        return resp;
    }

    @DeleteMapping("Attachment/{id}")
    public Response<Attachment> deleteAttachment(@PathVariable("id") Integer id) {
        Response<Attachment> resp = new Response<>();
        boolean flag = attachmentsService.deleteAttachment(id);
        if (flag == false) {
            resp = new Response(false, "There's an errror delete doesnt complete", true, null);
        } else {
            resp = new Response(true, "delete completed", true, null);
        }
        return resp;
    }

    public Response<List<Attachment>> responsePAttachmentData() {
        return null;

    }

}

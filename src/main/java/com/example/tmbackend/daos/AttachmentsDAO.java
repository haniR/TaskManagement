/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.daos;

import com.example.tmbackend.mappers.AttachmentsRowMaper;
import com.example.tmbackend.models.Attachment;
import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Transactional
@Repository
public class AttachmentsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Attachment> getAttachments() {
        String sql = "SELECT * FROM taskmanagement.attachments";
        RowMapper<Attachment> rowMapper = new AttachmentsRowMaper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }
    

    public Attachment getAttachmentById(int id) {
        String sql = "SELECT * from taskmanagement.attachments WHERE id = ?";
        RowMapper<Attachment> rowMapper = new BeanPropertyRowMapper<Attachment>(Attachment.class);
        Attachment attachments = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return attachments;
    }
    public List<Attachment> getAttachmentforTask(int tasksId) {
        String sql = "SELECT * from taskmanagement.attachments WHERE tasksId = ?";
        RowMapper<Attachment> rowMapper = new BeanPropertyRowMapper<Attachment>(Attachment.class);
       return jdbcTemplate.query(sql, rowMapper, tasksId);
         
    }

    public void addAttachment(String path,Attachment attachment) {
        String sql = "INSERT INTO taskmanagement.attachments ( src, tasksId,createdBy,createdAt,updatedBy,updatedAt) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, path, attachment.getTasksId(),attachment.getCreatedBy(),attachment.getCreatedAt(),attachment.getUpdatedBy(),attachment.getUpdatedAt());
//        sql = "SELECT id FROM attachments WHERE taskId =?";
//        int id = jdbcTemplate.queryForObject(sql, Integer.class,1);
//
//        attachment.setId(id);
    }

    public boolean updateAttachment(Attachment attachment) {
        String sql = "UPDATE taskmanagement.attachments SET src =?, tasksId =? WHERE id =?";
        return jdbcTemplate.update(sql, attachment.getSrc(), attachment.getTasksId(), attachment.getId()) != 0;
    }

    public boolean deleteAttachment(int id) {
        String sql = "DELETE FROM taskmanagement.attachments WHERE id=?";
        return jdbcTemplate.update(sql, id) != 0;
    }

    public boolean AttachmentExists(String src, int tasksId) {
        String sql = "SELECT count(*) FROM taskmanagement.attachments WHERE src = ? and tasksId =?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, src, tasksId);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }
}

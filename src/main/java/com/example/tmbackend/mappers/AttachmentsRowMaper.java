/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.mappers;

import com.example.tmbackend.models.Attachment;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ASUS
 */
public class AttachmentsRowMaper implements RowMapper<Attachment> {

    @Override
    public Attachment mapRow(ResultSet row, int rowNum) throws SQLException {
        Attachment attachments = new Attachment();
        attachments.setId(row.getInt("id"));
        attachments.setSrc(row.getString("src"));
        attachments.setTasksId(row.getInt("tasksId"));
        attachments.setCreatedBy(row.getInt("createdBy"));
        attachments.setCreatedAt(row.getDate("createdAt"));
        attachments.setUpdatedBy(row.getInt("updatedBy"));
        attachments.setUpdatedAt(row.getDate("updatedAt"));
        return attachments;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.mappers;

import com.example.tmbackend.models.Task;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author yshah
 */
public class TasksRowMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet row, int rowNum) throws SQLException {
        Task tasks = new Task();
        tasks.setId(row.getInt("id"));
        tasks.setName(row.getString("name"));
        tasks.setType(row.getString("type"));

        tasks.setDescription(row.getString("description"));
//        tasks.setDue(row.getDate("due"));
        tasks.setPriority(row.getString("priority"));
        tasks.setProjectId(row.getInt("projectsId"));
        tasks.setStatus(row.getString("status"));
        tasks.setAssigne(row.getInt("assigne"));
        tasks.setCreatedBy(row.getInt("createdBy"));
        tasks.setCreatedAt(row.getDate("createdAt"));
        tasks.setUpdatedBy(row.getInt("updatedBy"));
        tasks.setUpdatedAt(row.getDate("updatedAt"));
        tasks.setUser_name(row.getString("user_name"));
        tasks.setProject_name(row.getString("project_name"));

        return tasks;

    }
}

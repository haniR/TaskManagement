package com.example.tmbackend.mappers;

import com.example.tmbackend.models.Project;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet row, int rowNum) throws SQLException {
        Project project = new Project();
        project.setId(row.getInt("id"));
        project.setName(row.getString("name"));
        project.setCode(row.getString("code"));
        project.setAdminId(row.getInt("adminId"));
        project.setCreatedBy(row.getInt("createdBy"));
        project.setCreatedAt(row.getDate("createdAt"));
        project.setUpdatedBy(row.getInt("updatedBy"));
        project.setUpdatedAt(row.getDate("updatedAt"));
        project.setAdminName(row.getString("AdminName"));
        return project;
    }

}

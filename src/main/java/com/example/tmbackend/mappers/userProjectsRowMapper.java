
package com.example.tmbackend.mappers;

import com.example.tmbackend.models.User_projects;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class userProjectsRowMapper implements RowMapper<User_projects>{

    @Override
    public User_projects mapRow(ResultSet row, int rowNum) throws SQLException {
        
        User_projects users = new User_projects();
        users.setUsersId(row.getInt("usersId"));
        users.setProjectsId(row.getInt("projectsId"));        
        users.setCreatedBy(row.getInt("createdBy"));
        users.setCreatedAt(row.getDate("createdAt"));
        users.setUpdatedBy(row.getInt("updatedBy"));
        users.setUpdatedAt(row.getDate("updatedAt"));
        return users;

    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.mappers;

import com.example.tmbackend.models.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author yshah
 */
public class UsersRowMapper implements RowMapper<User>{
      @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        User users = new User();
        users.setId(row.getInt("id"));
        users.setSrc(row.getString("src"));
        users.setDateOfBirth(row.getDate("dateOfBirth"));
        users.setName(row.getString("name"));
        users.setType(row.getString("type"));
        users.setPassword(row.getString("password"));
        users.setUserName(row.getString("userName"));
        
        users.setCreatedBy(row.getInt("createdBy"));
        users.setCreatedAt(row.getDate("createdAt"));
        users.setUpdatedBy(row.getInt("updatedBy"));
        users.setUpdatedAt(row.getDate("updatedAt"));
        return users;

    }
}

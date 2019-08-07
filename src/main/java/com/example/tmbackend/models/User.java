/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.models;

//import java.sql.Date;
import static java.time.temporal.WeekFields.ISO;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import sun.security.util.Password;

/**
 *
 * @author yshah
 */
public class User {
    int id;
    String name;
    String password;
    String userName;
    Date dateOfBirth;
    String type;
    String src;
    int createdBy;
    int updatedBy;
    Date createdAt = new Date();
    Date updatedAt = new Date();
   

    public User() {
    }

    public User(int id, String name, String password, String userName, Date dateOfBirth, String type, String src, int createdBy, int updatedBy, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
        this.src = src;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

   
   

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

          
    
    
}

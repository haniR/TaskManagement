/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

/**
 *
 * @author yshah
 */
public class Task {

    private int id;
    private String type;
    private String priority;
    private String status;
    private int assigne;
    private String name;
    private String description;
    private int projectsId;
//    private Date due;
    private int createdBy;
    private int updatedBy;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String user_name;
    private String project_name;

   
    

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Task() {

    }

    public Task(int id, String type, String priority, String status, int assignee, String name, String description, int projectId/*, Date due*/, int createdBy, int updatedBy, Date createdAt, Date updatedAt) {
        this.id = id;

//        this.type.add(type);
        this.priority = priority;
        this.status = status;
        this.assigne = assignee;
        this.name = name;
        this.description = description;
        this.projectsId = projectId;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAssigne() {
        return assigne;
    }

    public void setAssigne(int assigne) {
        this.assigne = assigne;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProjectId() {
        return projectsId;
    }

    public void setProjectId(int projectId) {
        this.projectsId = projectId;
    }

//    public Date getDue() {
//        return due;
//    }
//
//    public void setDue(Date due) {
//        this.due = due;
//    }
}

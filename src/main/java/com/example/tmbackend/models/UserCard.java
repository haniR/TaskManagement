/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author yshah
 */
public class UserCard {
    User user = new User();
    ArrayList< Project> projects =   new ArrayList<Project>();
    ArrayList< Task> tasks =   new ArrayList<Task>();

    public UserCard() {
          
    }

    public User  getUser() {
        return  user;
    }

    public void setUser(User user) {
        this.user =  user;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    

}

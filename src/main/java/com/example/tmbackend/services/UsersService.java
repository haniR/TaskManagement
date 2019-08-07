/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.services;

import com.example.tmbackend.daos.UsersDAO;
import com.example.tmbackend.models.Project;
import com.example.tmbackend.models.Task;
import com.example.tmbackend.models.User;
import com.example.tmbackend.models.UserCard;
import com.example.tmbackend.models.User_projects;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yshah
 */
@Service
public class UsersService {

    @Autowired
    private UsersDAO usersDAO;

    public List<Project> getProjectsByUserId(int id) {
        return usersDAO.getProjectsByUserId(id);
    }
    public List<Task> getTasksByUserId(int id){
        
    return usersDAO.getTasksByUserId(id);
    }
    public List<User> getUsersByProjectId(int projectsId) {
        return usersDAO.getUsersByProjectId(projectsId);
    }

    public List<User> getAllAdmins() {
        return usersDAO.getAllAdmins();
    }

    public User getUserLoginId(User users) {
        return usersDAO.getUserLoginId(users);
    }

    public List<User> getAllUsers() {
        return usersDAO.getUsers();
    }

    public List<User> getAllUsersExist(int ProjectId) {
        return usersDAO.getAllUsersExist(ProjectId);
    }

    public User getUserById(int id) {
        return usersDAO.getUserById(id);
    }

    public synchronized boolean addUser(User users) throws IOException {
        if (usersDAO.UserExists(users.getId())) {
            return false;
        } else {
            usersDAO.addUser(users);
            return true;
        }
    }

    public synchronized boolean addUserToProject(User_projects user) {

        usersDAO.addUserToProject(user);
        return true;

    }

    public boolean updateUser(User users) throws IOException {
        return usersDAO.updateUser(users);
    }

    public boolean updateUserForName(User users) throws IOException {
        return usersDAO.updateUserForName(users);
    }

    public boolean deleteUser(int id) {
        return usersDAO.deleteUser(id);
    }

    public boolean deleteUserFromProject(int id, int id2, int projectId) {
        return usersDAO.deleteUserFromProject(id, id2, projectId);
    }

    public UserCard getUserCardById(int id) {
        UserCard card = new UserCard();
        card.setProjects((ArrayList<Project>) this.getProjectsByUserId(id));
        card.setTasks((ArrayList<Task>) this.getTasksByUserId(id));

        card.setUser((User) this.getUserById(id));
//        System.out.println(s.toString());
        return card;
    }

    public User getLogin(String username, String password) {
        User obj = usersDAO.login(username, password);
        return obj;
    }

}

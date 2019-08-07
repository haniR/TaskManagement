/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.controllers;

import com.example.tmbackend.daos.UsersDAO;
import com.example.tmbackend.general.Response;
import com.example.tmbackend.models.Project;
import com.example.tmbackend.models.Task;
import com.example.tmbackend.models.User;
import com.example.tmbackend.models.UserCard;
import com.example.tmbackend.models.User_projects;
import com.example.tmbackend.services.UsersService;
import java.io.IOException;
import java.util.Date;
import java.security.SecureRandom;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author yshah
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class UsersController {

    @Autowired
    private UsersService usersService;
    protected static SecureRandom random = new SecureRandom();
    private HashMap<String, User> hashMap = new HashMap<>();

    @GetMapping("/User/{id}")
    public Response<User> getUserById(@PathVariable("id") Integer id) {
        User users = usersService.getUserById(id);
        return new Response<User>(true, "", false, users);
    }

    @PostMapping(value = "/User", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<User> getUserLogInById(@RequestBody User user) {
        User userr = usersService.getUserLoginId(user);
        Response<User> resp = new Response<>();
        if (userr.getId() == 1) {
            resp = new Response<>(false, "There's an errror show list doesnt complete", true, userr);
        } else {
            resp = new Response<>(true, "Show completed", true, userr);
        }
        return new Response<User>(true, "", false, userr);
    }

    @GetMapping(value = "/Users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<User>> getAllUsers() {
        Response<List<User>> resp;
        List<User> list = usersService.getAllUsers();
        if (list.isEmpty()) {
            resp = new Response<>(false, "There's an errror show list doesnt complete", true, list);
        } else {
            resp = new Response<>(true, "Show completed", true, list);
        }
        return resp;
    }

    @GetMapping(value = "/UsersProject/{projectsId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<User>> getUsersByProjectId(@PathVariable("projectsId") Integer projectsId) {
        Response<List<User>> resp;
        List<User> list = usersService.getUsersByProjectId(projectsId);
        if (list.isEmpty()) {
            resp = new Response<>(false, "There's no users dor this project", true, list);
        } else {
            resp = new Response<>(true, "Show completed", true, list);
        }
        return resp;
    }

    @GetMapping(value = "/admins", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<User>> getAllAdmins() {
        Response<List<User>> resp;
        List<User> list = usersService.getAllAdmins();
        if (list.isEmpty()) {
            resp = new Response<>(false, "There's no users dor this project", false, list);
        } else {
            resp = new Response<>(true, "Show completed", true, list);
        }
        return resp;
    }

    @GetMapping(value = "/UserCard/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<UserCard> getUserCardById(@PathVariable("id") int id) {
        Response<UserCard> resp;
        UserCard user = usersService.getUserCardById(id);
        //if (user.) {
        //    resp = new Response<>(false, "There's an errror show list doesnt complete", true, user);
        // } else {
        resp = new Response<>(true, "Show completed", true, user);
        // }
        return resp;
    }

    @PostMapping("/Users")
    public Response<User> addUser(@RequestBody User users, UriComponentsBuilder builder) throws IOException {
        Response<User> resp = new Response<>();
        Date a = new Date(System.currentTimeMillis());
        users.setCreatedAt(a);
        users.setUpdatedAt(a);
        users.setCreatedBy(0);
        users.setUpdatedBy(0);
        boolean flag = usersService.addUser(users);
        if (flag == false) {
            resp = new Response(false, "There's an errror add doesnt complete", true, users);
        } else {
            resp = new Response(true, "Add completed", true, users);

        }

        return resp;
    }

    @PostMapping(value = "/userToProject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<User_projects> addUserToProject(@RequestBody User_projects user) {
        Response<User_projects> resp = new Response<>();
        boolean flag = usersService.addUserToProject(user);
        if (flag == false) {
            resp = new Response(false, "There's an errror add doesnt complete", true, user);
        } else {
            resp = new Response(true, "Add completed", true, user);
        }

        return resp;
    }

    @GetMapping(value = "/UsersExist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<User>> getAllUsersExist(@PathVariable("id") int id) {
        Response<List<User>> resp;
        List<User> list = usersService.getAllUsersExist(id);
        if (list.isEmpty()) {
            resp = new Response<>(false, "There's an errror show list doesnt complete", true, list);
        } else {
            resp = new Response<>(true, "Show completed", true, list);
        }
        return resp;
    }

    @PostMapping("/users/update")
    public Response<User> updateUser(@RequestBody User users) {
        Response<User> resp = new Response<>();
        try {
            boolean flag = usersService.updateUser(users);
            if (flag == false) {
                resp = new Response(false, "There's an errror update doesnt complete", true, users);
            } else {
                resp = new Response(true, "Update completed", true, users);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resp = new Response(false, ex.getMessage(), true, users);
        }
        return resp;
    }
    @PostMapping("/users/updateName")
    public Response<User> updateUserForName(@RequestBody User users) {
        Response<User> resp = new Response<>();
        try {
            boolean flag = usersService.updateUserForName(users);
            if (flag == false) {
                resp = new Response(false, "There's an errror update doesnt complete", true, users);
            } else {
                resp = new Response(true, "Update completed", true, users);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            resp = new Response(false, ex.getMessage(), true, users);
        }
        return resp;
    }

    @DeleteMapping("/Users/{id}")
    public Response<User> deleteUser(@PathVariable("id") Integer id) {
        Response<User> resp = new Response<>();
        boolean flag = usersService.deleteUser(id);
        if (flag == false) {
            resp = new Response(false, "There's an errror delete doesnt complete", true, null);
        } else {
            resp = new Response(true, "Delete completed", true, null);
        }
        return resp;
    }

    @DeleteMapping("/User/{assigneFrom}/{assigneTo}/{projectId}")
    public Response<Task> deleteUserFromProject(@PathVariable("assigneFrom") Integer assigneFrom, @PathVariable("assigneTo") Integer assigneTo, @PathVariable("projectId") Integer projectId) {
        System.err.println("From => " + assigneFrom);
        Response<Task> resp = new Response<>();
        boolean flag = usersService.deleteUserFromProject(assigneFrom, assigneTo, projectId);
        if (flag == false) {
            resp = new Response(false, "There's an errror delete doesnt complete", true, null);
        } else {
            resp = new Response(true, "Delete completed", true, null);
        }
        return resp;
    }
    Response<User> resp = new Response<>();

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<User> getLogin(@RequestBody User users) {
        System.err.println(users.getUserName());
        System.err.println(users.getPassword());

        try {

            User user = usersService.getLogin(users.getUserName(), users.getPassword());
            
            getLoginId(user.getId());
            String token = generateToken(user.getUserName());
            hashMap.put(token, user);
            hashMap.forEach((key, value) -> {
                if (value == user) {
                    resp = new Response<>(true, key, true, value);
                } else {
                    resp = new Response<>(false, "Wrong Username Or Password", true, null);

                }
            });
            System.out.print("the token is " + token);
        } catch (Exception e) {
            resp = new Response<>(false, "Wrong Username Or Password", true, null);

            System.out.println(e);
        }
        return resp;
    }

    public synchronized String generateToken(String username) {
        long longToken = Math.abs(random.nextLong());
        String token = Long.toString(longToken, 16);
        return token;
    }

    public int getLoginId(int id) {
        return id;
    }

    @GetMapping("Users/{id}")
    public Response<List<Project>> getUserProjectById(@PathVariable("id") Integer id) {
        Response<List<Project>> resp;
        List<Project> user = usersService.getProjectsByUserId(id);
        if (user.isEmpty()) {
            resp = new Response<>(false, "THere's an errror show list doesn't complete", true, user);
        } else {
            resp = new Response<>(true, "show completed", true, user);
        }
        return resp;
    }

    public Response<List<User>> responsePUserData() {
        return null;

    }
}

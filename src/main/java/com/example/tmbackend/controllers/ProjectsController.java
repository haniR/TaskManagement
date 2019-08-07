/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.controllers;

import com.example.tmbackend.general.Response;
import com.example.tmbackend.models.Project;
import com.example.tmbackend.models.User;
import com.example.tmbackend.services.ProjectsService;
import com.example.tmbackend.services.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author ASUS
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/projects")
public class ProjectsController {

    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/test")
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/{id}")
    public Response<List<Project>> getUserProjectById(@PathVariable("id") Integer id) {
        Response<List<Project>> resp = null;
        User user = usersService.getUserById(id);
        
        if (user.getType().equals("admin")) {
            List<Project> list = projectsService.getAllProjects();
            if (list.isEmpty()) {
                resp = new Response<>(false, "There's n project for this Id", true, list);
            } else {
                resp = new Response<>(true, "show Admin projects completed", true, list);
            }
        }else  {
            List<Project> list = projectsService.getProjectById(id);
            
            if (list.isEmpty()) {
                resp = new Response<>(false, "There's no project for you ", true, list);
            } else {
                resp = new Response<>(true, "show User projects completed", true, list);
            }

        }

        return resp;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<Project>> getAllProjects() {
        Response<List<Project>> resp;
        List<Project> list = projectsService.getAllProjects();
        if (list.isEmpty()) {
            resp = new Response<>(false, "There's an errror show list doesnt complete", true, list);
        } else {
            resp = new Response<>(true, "show completed", true, list);
        }
        return resp;
    }

    @PostMapping("")
    public Response<List<Project>> addProject(@RequestBody Project project, UriComponentsBuilder builder) {
        Response<List<Project>> resp = new Response<>();
        boolean flag = projectsService.addProject(project);
        if (flag == false) {
            resp = new Response(false, "There's an errror add doesnt complete", true, project);
        } else {
            resp = new Response(true, "add completed", true, project);
        }
        resp.setData(projectsService.getAllProjects());
        return resp;
    }

    @PutMapping("")
    public Response<Project> updateProject(@RequestBody Project project) {
        Response<Project> resp = new Response<>();
        try {
            boolean flag = projectsService.updateProject(project);
            if (flag == false) {
                resp = new Response(false, "There's an errror update doesnt complete", true, project);
            } else {
                resp = new Response(true, "update completed", true, project);
            }
        } catch (Exception ex) {
            resp = new Response(false, ex.getMessage(), true, project);
        }
        return resp;
    }

    @DeleteMapping("/{id}")
    public Response<Project> deleteProject(@PathVariable("id") Integer id) {
        Response<Project> resp = new Response<>();
        boolean flag = projectsService.deleteProject(id);
        if (flag == false) {
            resp = new Response(false, "There's an errror delete doesnt complete", true, null);
        } else {
            resp = new Response(true, "delete completed", true, null);
        }
        return resp;
    }

    public Response<List<Project>> responseProjectData() {
        return null;

    }

}

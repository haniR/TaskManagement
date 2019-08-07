/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.controllers;

import com.example.tmbackend.general.ChartResponse;
import com.example.tmbackend.general.Response;
import com.example.tmbackend.models.Chart;
import com.example.tmbackend.models.ChartWrapper;
import com.example.tmbackend.models.PriorityChart;
import com.example.tmbackend.models.Task;
import com.example.tmbackend.models.TypeChart;
import com.example.tmbackend.services.TasksService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/tasks")

public class TasksController {

    @Autowired
    private TasksService tasksService;

    @GetMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Task> getTaskById(@PathVariable("id") Integer id) {
        Task tasks = tasksService.getTaskById(id);
        return new Response<Task>(true, "", false, tasks);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<Task>> getAllTasks(@PathVariable("id") Integer id) {

        Response<List<Task>> resp;
        List<Task> list = tasksService.getAllTasks(id);
        if (list.isEmpty()) {
            resp = new Response<>(false, "There's No Task for this Project", true, list);
        } else {
            resp = new Response<>(true, "Show completed", true, list);
        }
        return resp;
    }

    @GetMapping(value = "/a/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ChartResponse<List<Chart>> getChartByProjectId(@PathVariable("projectId") Integer id) {

        ChartResponse<List<Chart>> resp;

        ChartWrapper<List<Chart>> data = new ChartWrapper<>();
        List<Chart> list = tasksService.getStatusByProjectId(id);

        List<Chart> list2 = tasksService.getTypeByProjectId(id);

        List<Chart> list3 = tasksService.getPriorityByProjectId(id);

        data.setData(list);
        data.setData2(list2);
        data.setData3(list3);
        resp = new ChartResponse<>(true, "Show Completed", true, data);
//    }
        return resp;
    }
// 

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ChartResponse<List<Chart>> getChartForstatus(@PathVariable("userId") Integer id) {

        ChartResponse<List<Chart>> resp;
        ChartWrapper<List<Chart>> data = new ChartWrapper<>();
        List<Chart> list = tasksService.getChartForStatus(id);
        List<Chart> list2 = tasksService.getChartForType(id);
        List<Chart> list3 = tasksService.getChartForPriority(id);

        data.setData(list);
        data.setData2(list2);
        data.setData3(list3);
        resp = new ChartResponse<>(true, "Show Completed", true, data);
        return resp;
    }

    @PostMapping(value = "/filter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<Task>> getAllTasksFilterization(@RequestBody Task task) {
        Response<List<Task>> resp;
        List<Task> list = tasksService.getAllTasksFilterization(task);
        if (list.isEmpty()) {
            resp = new Response<>(false, "There's No Task like this filter", true, list);
        } else {
            resp = new Response<>(true, "Show completed", true, list);
        }
        return resp;
    }

    @PostMapping("")
    public Response<Task> addTask(@RequestBody Task tasks, UriComponentsBuilder builder) {
        Response<Task> resp = new Response<>();
        boolean flag = tasksService.addTask(tasks);
        if (flag == false) {
            resp = new Response(false, "There's an error add doesnt complete", true, tasks);
        } else {
            resp = new Response(true, "Add completed", true, tasks);

        }

        return resp;
    }

    
    
    
    @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Task> updateTask(@RequestBody Task tasks) {
        ObjectMapper mapper = new ObjectMapper();
        Response<Task> resp = new Response<>();
        try {
            System.err.println(mapper.writeValueAsString(tasks));
            boolean flag = tasksService.updateTask(tasks);
            if (flag == false) {
                resp = new Response(false, "There's an errror update doesnt complete", true, tasks);
            } else {
                resp = new Response(true, "Update completed", true, tasks);
            }
        } catch (Exception ex) {

            resp = new Response(false, ex.getMessage(), true, tasks);
        }
        return resp;
    }

    
    
    
    @DeleteMapping("/{id}")
    public Response<Task> deleteTask(@PathVariable("id") Integer id) {
        Response<Task> resp = new Response<>();
        boolean flag = tasksService.deleteTask(id);
        if (flag == false) {
            resp = new Response(false, "There's an errror delete doesnt complete", true, null);
        } else {
            resp = new Response(true, "Delete completed", true, null);
        }
        return resp;
    }

    public Response<List<Task>> responsePTaskData() {
        return null;

    }

}

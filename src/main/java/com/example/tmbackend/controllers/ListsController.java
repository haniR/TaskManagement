package com.example.tmbackend.controllers;

import com.example.tmbackend.general.Response;
import com.example.tmbackend.models.Lists;
import com.example.tmbackend.models.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ListsController {

    Lists lists = new Lists();
    List<String> types = lists.getTypes();
    List<String> priorities = lists.getPriorities();
    List<String> statuses = lists.getStatuses();

    @GetMapping(value = "/types")
    public Response<Lists> ListsOfTypes() {
        Response<Lists> resp = new Response<>();
        try {
            if (types.isEmpty()) {
                resp = new Response(false, "There's an error to take your lists of values ", true, types);

            } else {
                resp = new Response(true, "Update completed", false, types);

            }

        } catch (Exception ex) {

            resp = new Response(false, ex.getMessage(), true, types);
        }
        return resp;
    }
     @GetMapping(value = "/priorities")
    public Response<Lists> ListsOfPriorities() {
        Response<Lists> resp = new Response<>();
        try {
            if (priorities.isEmpty()) {
                resp = new Response(false, "There's an error to take your lists of priorities ", true, priorities);

            } else {
                resp = new Response(true, "Update completed", false, priorities);

            }

        } catch (Exception ex) {

            resp = new Response(false, ex.getMessage(), true, priorities);
        }
        return resp;
    } @GetMapping(value = "/statuses")
    public Response<Lists> ListsOfStatuses() {
        Response<Lists> resp = new Response<>();
        try {
            if (statuses.isEmpty()) {
                resp = new Response(false, "There's an error to take your lists of statuses ", true, statuses);

            } else {
                resp = new Response(true, "Update completed", false, statuses);

            }

        } catch (Exception ex) {

            resp = new Response(false, ex.getMessage(), true, statuses);
        }
        return resp;
    }

}

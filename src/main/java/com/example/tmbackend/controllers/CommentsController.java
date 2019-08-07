
package com.example.tmbackend.controllers;

import com.example.tmbackend.general.Response;
import com.example.tmbackend.models.Comment;
import com.example.tmbackend.services.CommentsService;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class CommentsController {
    
    @Autowired
    private CommentsService commentsService;

    @GetMapping("/Comment/{id}")
    public Response<Comment> getCommentById(@PathVariable("id") Integer id) {
        Comment comments = commentsService.getCommentById(id);
        return new Response<Comment>(true, "", false, comments);
    }

    @GetMapping(value = "/Comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<Comment>> getAllComments() {
        Response<List<Comment>> resp;
        List<Comment> list = commentsService.getAllComments();
        if (list.isEmpty()) {
            resp = new Response<>(false, "There's an errror show list doesnt complete", true, list);
        } else {
            resp = new Response<>(true, "Show completed", true, list);
        }
        return resp;
    }

    @PostMapping("/Comments/add")
    public Response<Comment> addComment(@RequestBody Comment comments, UriComponentsBuilder builder) {
        Response<Comment> resp = new Response<>();
        boolean flag = commentsService.addComment(comments);
        if (flag == false) {
            resp = new Response(false, "There's an errror add doesnt complete", true, comments);
        } else {
            resp = new Response(true, "Add completed", true, comments);

        }

        return resp;
    }

    @PostMapping("/Comments/update")
    public Response<Comment> updateComment(@RequestBody Comment comments) {
        Response<Comment> resp = new Response<>();
        try {
            boolean flag = commentsService.updateComment(comments);
            if (flag == false) {
                resp = new Response(false, "There's an errror update doesnt complete", true, comments);
            } else {
                resp = new Response(true, "update completed", true, comments);
            }
        } catch (Exception ex) {
            resp = new Response(false, ex.getMessage(), true, comments);
        }
        return resp;
    }

    @DeleteMapping("Comments/{id}")
    public Response<Comment> deleteComment(@PathVariable("id") Integer id) {
        Response<Comment> resp = new Response<>();
        boolean flag = commentsService.deleteComment(id);
        if (flag == false) {
            resp = new Response(false, "There's an errror delete doesnt complete", true, null);
        } else {
            resp = new Response(true, "delete completed", true, null);
        }
        return resp;
    }

    public Response<List<Comment>> responseCommentData() {
        return null;

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.services;

import com.example.tmbackend.daos.CommentsDAO;
import com.example.tmbackend.models.Comment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yshah
 */
@Service
public class CommentsService {
    @Autowired
    private CommentsDAO commentsDAO;

    public Comment getCommentById(int id) {
        Comment obj = commentsDAO.getCommentById(id);
        return obj;
    }

    public List<Comment> getAllComments() {
        return commentsDAO.getComments();
    }

    public synchronized boolean addComment(Comment comments) {
        if (commentsDAO.CommentExists(comments.getId())) {
            return false;
        } else {
            commentsDAO.addComment(comments);
            return true;
        }
    }

    public boolean updateComment(Comment comments) {
        return commentsDAO.updateComment(comments);
    }

    public boolean deleteComment(int id) {
        return commentsDAO.deleteComment(id);
    }

}

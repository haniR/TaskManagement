/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.general;

import java.util.List;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Sohay
 */
public class Response<T> {

    private boolean success = true;
    private String msg = "Operation completed Successfully!";
    private boolean msg_show = true;
    private T data;

    

    public Response(boolean success, String msg, boolean msg_show, T data) {
        this.success = success;
        this.msg = msg;
        this.msg_show = msg_show;
        this.data = data;
    }

   

    public Response() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isMsg_show() {
        return msg_show;
    }

    public void setMsg_show(boolean msg_show) {
        this.msg_show = msg_show;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

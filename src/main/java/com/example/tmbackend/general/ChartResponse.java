/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.general;

import com.example.tmbackend.models.Chart;
import com.example.tmbackend.models.ChartWrapper;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ChartResponse <T>{

    private boolean success = true;
    private String msg = "Operation completed Successfully!";
    private boolean msg_show = true;
    private ChartWrapper<List<Chart>> data;

    public ChartResponse(ChartWrapper<List<Chart>> data) {
        this.data = data;
    }
     public ChartResponse(boolean success, String msg, boolean msg_show, ChartWrapper<List<Chart>> data) {
        this.success = success;
        this.msg = msg;
        this.msg_show = msg_show;
        this.data = data;

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

    public ChartWrapper<List<Chart>>  getData() {
        return data;
    }

    public void setData(ChartWrapper<List<Chart>> datadata) {
        this.data = data;
    }

    
}

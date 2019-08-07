/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.tmbackend.models;
import com.example.tmbackend.models.Chart;

/**
 *
 * @author ASUS
 */
public class ChartWrapper<T>{
    private T data;
    private T data2;
    private T data3;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData2() {
        return data2;
    }

    public void setData2(T data2) {
        this.data2 = data2;
    }

    public T getData3() {
        return data3;
    }

    public void setData3(T data3) {
        this.data3 = data3;
    }

    
}

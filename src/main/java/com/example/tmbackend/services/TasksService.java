/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.services;

import com.example.tmbackend.daos.TasksDAO;
import com.example.tmbackend.models.Chart;
import com.example.tmbackend.models.PriorityChart;
import com.example.tmbackend.models.Task;
import com.example.tmbackend.models.TypeChart;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yshah
 */
@Service
public class TasksService {

    @Autowired
    private TasksDAO tasksDAO;

    public Task getTaskById(int id) {
        return tasksDAO.getTaskById(id);
    }

    public List<Task> getAllTasks(int id) {
        return tasksDAO.getAllTasks(id);
    }

    public List<Task> getAllTasksFilterization(Task task) {
        return tasksDAO.getAllTasksFilterization(task);
    }

    public List<Chart> getChartForStatus(int id) {
        return tasksDAO.getChartsForStatus(id);
    }

    public List<Chart> getChartForType(int id) {
        return tasksDAO.getChartsForTypes(id);
    }

    public List<Chart> getChartForPriority(int id) {
        return tasksDAO.getChartsForPriority(id);
    }

    public synchronized boolean addTask(Task tasks) {
        if (tasksDAO.TaskExists(tasks.getId())) {
            return false;
        } else {
            tasksDAO.addTask(tasks);
            return true;
        }
    }

    public boolean updateTask(Task tasks) {
        return tasksDAO.updateTask(tasks);
    }

    public boolean deleteTask(int id) {
        return tasksDAO.deleteTask(id);
    }

    public List<Chart> getStatusByProjectId(Integer id) {
         return tasksDAO.getStatusByProjectId(id);
    }

    public List<Chart> getTypeByProjectId(Integer id) {
         return tasksDAO.getTypeByProjectId(id);
    }

    public List<Chart> getPriorityByProjectId(Integer id) {
        return tasksDAO.getPriorityByProjectId(id);
    }

}

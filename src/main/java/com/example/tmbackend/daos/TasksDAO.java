/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.daos;

import com.example.tmbackend.mappers.PriorityChartMapper;
import com.example.tmbackend.mappers.StatusChartMapper;
import com.example.tmbackend.mappers.TasksRowMapper;
import com.example.tmbackend.mappers.TypeChartMapper;
import com.example.tmbackend.models.Chart;
import com.example.tmbackend.models.PriorityChart;
import com.example.tmbackend.models.Task;
import com.example.tmbackend.models.TypeChart;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author yshah
 */
@Transactional
@Repository
public class TasksDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Task getTaskById(int id) {
        String sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t "
                + ",taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and"
                + " p.id=projectsId and t.id=?";
        RowMapper<Task> rowMapper = new TasksRowMapper();
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<Task> getAllTasks(int projectsId) {
        String sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t "
                + ",taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and "
                + "p.id=projectsId and projectsId=?";
        RowMapper<Task> rowMapper = new TasksRowMapper();
        return jdbcTemplate.query(sql, rowMapper, projectsId);
    }

    public List<Task> getAllTasksFilterization(Task task) {
        String sql = "";
        RowMapper<Task> rowMapper = new TasksRowMapper();
        if (task.getType() == "" && task.getPriority() == "" && task.getStatus() == "" && task.getAssigne() == 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId());

        } else if (task.getType() != "" && task.getPriority() == "" && task.getStatus() == "" && task.getAssigne() == 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and t.type =?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getType());

        } else if (task.getType() == "" && task.getPriority() != "" && task.getStatus() == "" && task.getAssigne() == 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and t.priority =?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getPriority());

        } else if (task.getType() == "" && task.getPriority() == "" && task.getStatus() != "" && task.getAssigne() == 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and t.status =?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getStatus());

        } else if (task.getType() == "" && task.getPriority() == "" && task.getStatus() == "" && task.getAssigne() != 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and t.assigne =?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getAssigne());

        } else if (task.getType() != "" && task.getPriority() != "" && task.getStatus() != "" && task.getAssigne() != 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and  t.type =?and t.priority =? and t.status =? and t.assigne=?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getType(), task.getPriority(), task.getStatus(), task.getAssigne());

        } else if (task.getType() != "" && task.getPriority() != "" && task.getStatus() == "" && task.getAssigne() == 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and  t.type =?and t.priority =? ";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getType(), task.getPriority());

        } else if (task.getType() != "" && task.getPriority() == "" && task.getStatus() != "" && task.getAssigne() == 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and  t.type =? and t.status =?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getType(), task.getStatus());

        } else if (task.getType() == "" && task.getPriority() != "" && task.getStatus() != "" && task.getAssigne() == 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and t.priority =? and t.status =?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getPriority(), task.getStatus());

        } else if (task.getType() != "" && task.getPriority() != "" && task.getStatus() == "" && task.getAssigne() != 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and  t.type =?and t.priority =? and t.assigne =?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getType(), task.getPriority(), task.getAssigne());

        } else if (task.getType() == "" && task.getPriority() != "" && task.getStatus() == "" && task.getAssigne() != 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and t.priority =? and t.assigne =?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getPriority(), task.getAssigne());

        } else if (task.getType() != "" && task.getPriority() == "" && task.getStatus() != "" && task.getAssigne() != 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and  t.type =? and t.status =? and t.assigne =?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getType(), task.getStatus(), task.getAssigne());

        } else if (task.getType() == "" && task.getPriority() == "" && task.getStatus() != "" && task.getAssigne() != 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=?  and t.status =? and t.assigne =?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getStatus(), task.getAssigne());

        } else if (task.getType() != "" && task.getPriority() == "" && task.getStatus() == "" && task.getAssigne() != 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and  t.type =?  and t.assigne =?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getType(), task.getAssigne());

        } else if (task.getType() == "" && task.getPriority() != "" && task.getStatus() != "" && task.getAssigne() != 0) {
            sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=? and t.priority =? and t.status =? and t.assigne =?";
            return jdbcTemplate.query(sql, rowMapper, task.getProjectId(), task.getPriority(), task.getStatus(), task.getAssigne());

        }
        sql = "SELECT t.*,u.name As user_name ,p.name As project_name FROM taskmanagement.tasks as t ,taskmanagement.projects as p,taskmanagement.users as u where u.id = t.assigne and p.id=projectsId and projectsId=?";
        return jdbcTemplate.query(sql, rowMapper, task.getProjectId());
    }

    public List<Chart> getChartsForStatus(int id) {
        String sql = "SELECT t.status,count(t.id) As counter "
                + " FROM   taskmanagement.tasks as t "
                + " join users as u on u.id = t.assigne where t.assigne = ? "
                + " group by t.status ";

        RowMapper<Chart> rowMapper = new StatusChartMapper();
        return jdbcTemplate.query(sql, rowMapper, id);
    }

    public List<Chart> getChartsForTypes(int id) {
        String sql = "SELECT t.type,count(t.id) As counter "
                + " FROM   taskmanagement.tasks as t "
                + " join users as u on u.id = t.assigne where t.assigne = ? "
                + " group by t.type ";
        RowMapper<Chart> rowMapper = new TypeChartMapper();
        return jdbcTemplate.query(sql, rowMapper, id);
    }

    public List<Chart> getChartsForPriority(int id) {
        String sql = "SELECT t.priority,count(t.id) As counter "
                + " FROM   taskmanagement.tasks as t "
                + " join users as u on u.id = t.assigne where t.assigne = ? "
                + " group by t.priority ";
        RowMapper<Chart> rowMapper = new PriorityChartMapper();
        return jdbcTemplate.query(sql, rowMapper, id);
    }

    public List<Chart> getStatusByProjectId(int id) {
        String sql = "SELECT t.status,count(*) as counter "
                + "FROM tasks as t , users as u "
                + "where u.id = t.assigne  and projectsId = ? "
                + "GROUP BY status";

        RowMapper<Chart> rowMapper = new StatusChartMapper();
        return jdbcTemplate.query(sql, rowMapper, id);
    }

    public List<Chart> getTypeByProjectId(int id) {
        String sql = "SELECT t.type,count(*) as counter "
                + "FROM tasks as t , users as u "
                + "where u.id = t.assigne  and projectsId = ? "
                + "GROUP BY type";
//        String sql ="SELECT type,count(*) as counter FROM tasks where projectsId = 2 GROUP BY type";
        RowMapper<Chart> rowMapper = new TypeChartMapper();
        return jdbcTemplate.query(sql, rowMapper, id);
    }

    public List<Chart> getPriorityByProjectId(int id) {
        String sql = "SELECT t.priority,count(*) as counter "
                + "FROM tasks as t , users as u "
                + "where u.id = t.assigne  and projectsId = ? "
                + "GROUP BY priority";
        RowMapper<Chart> rowMapper = new PriorityChartMapper();
        return jdbcTemplate.query(sql, rowMapper, id);
    }

    public void addTask(Task task) {
        String sql = "INSERT INTO taskmanagement.tasks ( type,  priority,  status,  "
                + "  name,  description,  projectsId,  assigne,createdBy,createdAt,updatedBy,updatedAt) "
                + "values (?,?,?,?,?,?,?,?,?,?,?)";

        java.util.Date date = new java.util.Date(System.currentTimeMillis());
        task.setCreatedAt(date);
        task.setUpdatedAt(date);
        task.setUpdatedBy(task.getId());
        task.setCreatedBy(task.getId());
        jdbcTemplate.setExceptionTranslator(new MySqlTrans());
        jdbcTemplate.update(sql, task.getType(), task.getPriority(), task.getStatus(),
                task.getName(), task.getDescription(), task.getProjectId(), task.getAssigne(),
                 task.getCreatedBy(), task.getCreatedAt(), task.getUpdatedBy(), task.getUpdatedAt());

//        sql = "SELECT id FROM tasks WHERE id =?";
//        int id = jdbcTemplate.queryForObject(sql, Integer.class, task.getId());
//
//        task.setId(id);
    }

    public boolean updateTask(Task task) {
        String sql = "UPDATE taskmanagement.tasks "
                + "SET type =?, priority =?, status =?"
                + ", name =?, description =?,  assigne =?,updatedAt=?,UpdatedBy=?"
                + " WHERE id =?";
        return jdbcTemplate.update(sql, task.getType(), task.getPriority(), task.getStatus(),
                task.getName(), task.getDescription(), task.getAssigne(), task.getUpdatedAt(), task.getUpdatedBy(),
                task.getId()) != 0;
    }

    public boolean deleteTask(int id) {
        String sql = "DELETE FROM taskmanagement.tasks WHERE id=?";
        return jdbcTemplate.update(sql, id) != 0;
    }

    public boolean TaskExists(int id) {
        String sql = "SELECT count(*) FROM taskmanagement.tasks WHERE  id =?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }
}

class MySqlTrans implements SQLExceptionTranslator {

    @Override
    public DataAccessException translate(String string, String string1, SQLException sqle) {
        System.err.println(string);
        System.err.println(string1);
        sqle.printStackTrace();
        return null;
    }
}

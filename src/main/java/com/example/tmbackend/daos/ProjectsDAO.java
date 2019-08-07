/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tmbackend.daos;

import com.example.tmbackend.mappers.ProjectRowMapper;
import com.example.tmbackend.mappers.UsersRowMapper;
import com.example.tmbackend.models.Project;
import com.example.tmbackend.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Transactional
@Repository
public class ProjectsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private UsersDAO usersDAO;

//    public void getProjectsByType(int id) {
//        User user = usersDAO.getUserById(id);
//        if (user.getType() == "admin") {
//            getAllProjects();
//        } else {
//            getProjectByUserId(id);
//        }
//
//    }

    public List<Project> getProjectByUserId(int id) {
        RowMapper<Project> rowMapper = new ProjectRowMapper();
        String sql2 = "select p.*,U.name as AdminName from users u ,projects p where u.id=p.adminId and p.id IN (SELECT projectsId from taskmanagement.user_projects WHERE usersId =?);";
        return this.jdbcTemplate.query(sql2, rowMapper, id);
    }

    public List<Project> getAllProjects() {
        RowMapper<Project> rowMapper = new ProjectRowMapper();
        String sql2 = "select p.*,U.name as AdminName from users u ,projects p where u.id=p.adminId";
        return this.jdbcTemplate.query(sql2, rowMapper);
    }

    public void addProject(Project project) {
        String sql = "INSERT INTO taskmanagement.projects ( name, code,adminId,createdBy,createdAt,updatedAt,UpdatedBy) values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, project.getName(), project.getCode(), project.getAdminId(), project.getId(), project.getCreatedAt(), project.getUpdatedAt(), project.getId());

        sql = "SELECT id FROM taskmanagement.projects WHERE name = ? and code =? and adminId=? ";
        int id = jdbcTemplate.queryForObject(sql, Integer.class, project.getName(), project.getCode(), project.getAdminId());

        project.setId(id);
    }

    public boolean updateProject(Project project) {
        String sql = "UPDATE taskmanagement.projects SET name =?, code =?,adminId=? ,updatedAt=?,UpdatedBy=? WHERE id =?";
        return jdbcTemplate.update(sql, project.getName(), project.getCode(), project.getAdminId(),project.getUpdatedAt(), project.getUpdatedBy(), project.getId()) != 0;
    }

    public boolean deleteProject(int id) {
        String sql = "DELETE FROM taskmanagement.projects WHERE id=?";
        return jdbcTemplate.update(sql, id) != 0;
    }

    public boolean ProjectExists(String name, String code) {
        String sql = "SELECT count(*) FROM taskmanagement.projects WHERE name = ? and code =?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, name, code);
        if (count == 0) {
            return false;
        } else {
            return true;
        }
    }

}

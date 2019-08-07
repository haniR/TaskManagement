package com.example.tmbackend.services;

import com.example.tmbackend.daos.ProjectsDAO;
import com.example.tmbackend.models.Project;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectsService {

    @Autowired
    private ProjectsDAO projectsDAO;
    
    public List<Project> getProjectById(int id) {
        return projectsDAO.getProjectByUserId(id);
    }
//    public void getProjectsByType(int id){
//        projectsDAO.getProjectsByType(id);
//    }

    public List<Project> getAllProjects() {
        return projectsDAO.getAllProjects();
    }
    

    public synchronized boolean addProject(Project project) {
        if (projectsDAO.ProjectExists(project.getName(), project.getCode())) {
            return false;
        } else {
            projectsDAO.addProject(project);
            return true;
        }
    }

    public boolean updateProject(Project project) {
        return projectsDAO.updateProject(project);
    }

    public boolean deleteProject(int id) {
        return projectsDAO.deleteProject(id);
    }

}

package com.example.employeemanagement.project.services;

import com.example.employeemanagement.exception.EntityNotFound;
import com.example.employeemanagement.project.entity.Project;
import com.example.employeemanagement.project.repository.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepo projectRepo;
    public Project addProject(Project project){
        return projectRepo.save(project);
    }
    public List<Project> findAllProject(){
        return projectRepo.findAll();
    }
    public void deleteById(Long id){
        projectRepo.deleteById(id);
    }
    public Project findProjectById(Long id){
        return projectRepo.findById(id).orElseThrow(() -> new EntityNotFound("project not found " + id));
    }
}

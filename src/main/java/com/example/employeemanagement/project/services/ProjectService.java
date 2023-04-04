package com.example.employeemanagement.project.services;

import com.example.employeemanagement.designation.entity.Designation;
import com.example.employeemanagement.designation.repository.DesignationRepo;
import com.example.employeemanagement.exception.EntityNotFound;
import com.example.employeemanagement.project.entity.Project;
import com.example.employeemanagement.project.repository.ProjectRepo;
import com.example.employeemanagement.teams.entity.Teams;
import com.example.employeemanagement.teams.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepo projectRepo;
    @Autowired
    TeamRepo teamRepo;
    @Autowired
    DesignationRepo designationRepo;
    public Project addProject(Project project){
        Project project1=projectRepo.save(project);
        List<Designation> designationList=designationRepo.findAll();
        designationList.forEach(designation -> {
            Teams teams = new Teams();
            teams.setTeamName(designation.getDesignationName());
            teams.setProjectId(project1.getId());
            teamRepo.save(teams);
        });
        return project1;
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

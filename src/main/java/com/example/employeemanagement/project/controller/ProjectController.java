package com.example.employeemanagement.project.controller;

import com.example.employeemanagement.project.entity.Project;
import com.example.employeemanagement.project.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@CrossOrigin(exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Project>> findAllProject() {
        return new ResponseEntity<>(projectService.findAllProject(), HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Project> findProjectById(@PathVariable("id") Long id){
        return new ResponseEntity<>(projectService.findProjectById(id),HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody Project project){
        return new ResponseEntity<>(projectService.addProject(project),HttpStatus.OK);
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        projectService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }



}

package com.example.employeemanagement.teams.controller;

import com.example.employeemanagement.teams.entity.Teams;
import com.example.employeemanagement.teams.service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin
public class TeamsController {
    @Autowired
    TeamsService teamsService;
    @GetMapping("/findAll/{id}")
    public ResponseEntity<List<Teams>> findAllTeam(@PathVariable("id") Long id) {
        return new ResponseEntity<>(teamsService.findAllTeam(id), HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Teams> findTeamById(@PathVariable("id") Long id){
        return new ResponseEntity<>(teamsService.findById(id),HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Teams> addTeams(@RequestBody Teams teams){
        return new ResponseEntity<>(teamsService.addTeam(teams),HttpStatus.OK);
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        teamsService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

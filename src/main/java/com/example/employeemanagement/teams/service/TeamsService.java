package com.example.employeemanagement.teams.service;


import com.example.employeemanagement.exception.EntityNotFound;
import com.example.employeemanagement.teams.entity.Teams;
import com.example.employeemanagement.teams.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsService {
    @Autowired
    TeamRepo teamRepo;
    public Teams addTeam(Teams teams){
        return teamRepo.save(teams);
    }
    public List<Teams> findAllTeam(){
        return teamRepo.findAll();
    }
    public Teams findById(Long id){
        return teamRepo.findById(id).orElseThrow(() -> new EntityNotFound("skill not found " + id));
    }
    public Teams updateTeams(Teams teams){
        return teamRepo.save(teams);
    }
    public void deleteById(Long id){
        teamRepo.deleteById(id);
    }
}

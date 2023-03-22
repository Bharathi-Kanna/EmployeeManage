package com.example.employeemanagement.relations.employeeTeam.service;

import com.example.employeemanagement.relations.employeeTeam.repository.EmployeeTeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeTeamService {
    @Autowired
    EmployeeTeamRepo employeeTeamRepo;
}

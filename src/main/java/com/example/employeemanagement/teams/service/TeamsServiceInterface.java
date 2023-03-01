package com.example.employeemanagement.teams.service;

import com.example.employeemanagement.employee.response.EmployeeResponse;
import com.example.employeemanagement.employee.response.EmployeeResponseSimple;
import com.example.employeemanagement.generics.ServiceInterface;
import com.example.employeemanagement.teams.entity.Teams;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamsServiceInterface extends ServiceInterface<Teams> {

    public List<Teams> findByProject(Long Id);

    List<EmployeeResponseSimple> findALlEmployeeTeam(Long id);
}

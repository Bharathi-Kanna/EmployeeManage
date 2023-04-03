package com.example.employeemanagement.relations.employeeTeam.service;


import com.example.employeemanagement.employee.response.EmployeeResponse;
import com.example.employeemanagement.employee.services.entityservice.EmployeeService;
import com.example.employeemanagement.exception.EntityNotFound;
import com.example.employeemanagement.relations.employeeTeam.entity.EmployeeTeam;
import com.example.employeemanagement.relations.employeeTeam.repository.EmployeeTeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeTeamService {
    @Autowired
    EmployeeTeamRepo employeeTeamRepo;
    @Autowired
    EmployeeService employeeService;

    public EmployeeTeam addEmployeeTeam(EmployeeTeam employeeTeam){
        return employeeTeamRepo.save(employeeTeam);
    }
    public void removeEmployee(Long id){
        EmployeeTeam employeeTeam= employeeTeamRepo.findById(id).orElseThrow(() -> new EntityNotFound("employeeTeam not found " + id));
        employeeTeam.setEndDate(LocalDate.now());
        employeeTeam.setEmployeeStatus("INACTIVE");
        employeeTeamRepo.save(employeeTeam);
    }
    public List<EmployeeResponse> findAllEmployeeInTeam(Long id){
        List<EmployeeTeam> employeeTeamList=employeeTeamRepo.findByTeamId(id);
        List<EmployeeResponse> employeeResponseList=new ArrayList<>();
        employeeTeamList.forEach(employeeTeam -> {
            EmployeeResponse employeeResponse=employeeService.findEntityByIdWithNames(employeeTeam.getEmployeeId());
            employeeResponse.setEmployeeTeamId(employeeTeam.getId());
            if(employeeTeam.getEmployeeStatus()=="ACTIVE")
            employeeResponseList.add(employeeResponse);
        });
        return employeeResponseList;
    }

}

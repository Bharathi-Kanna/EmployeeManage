package com.example.employeemanagement.relations.employeeTeam.repository;

import com.example.employeemanagement.relations.employeeTeam.entity.EmployeeTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeTeamRepo extends JpaRepository<EmployeeTeam,Long> {

    List<EmployeeTeam> findByTeamId(Long id);
}

package com.example.employeemanagement.relations.employeeskills.repository;

import com.example.employeemanagement.relations.employeeskills.entity.EmployeeSkills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeSkillsRepo extends JpaRepository<EmployeeSkills,Long> {
    List<EmployeeSkills> findByEmployeeId(Long id);
    List<EmployeeSkills> findBySkillId(Long id);
}
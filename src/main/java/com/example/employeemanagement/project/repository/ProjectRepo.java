package com.example.employeemanagement.project.repository;

import com.example.employeemanagement.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Long> {
}

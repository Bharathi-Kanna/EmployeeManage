package com.example.employeemanagement.teams.repository;

import com.example.employeemanagement.teams.entity.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Teams,Long> {
}

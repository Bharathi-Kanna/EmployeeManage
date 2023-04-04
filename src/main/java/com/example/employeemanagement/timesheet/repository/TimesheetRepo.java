package com.example.employeemanagement.timesheet.repository;

import com.example.employeemanagement.timesheet.entity.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimesheetRepo extends JpaRepository<Timesheet,Long> {
    List<Timesheet> findByProjectId(Long id);

}

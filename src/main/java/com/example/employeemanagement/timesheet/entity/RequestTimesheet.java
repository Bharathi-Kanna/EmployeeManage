package com.example.employeemanagement.timesheet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestTimesheet {
    private Long projectId;
    private LocalDate startDate;
    private LocalDate endDate;
}

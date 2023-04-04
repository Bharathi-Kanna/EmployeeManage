package com.example.employeemanagement.timesheet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String employeeName;
    private String projectName;
    @JsonIgnore
    private Long projectId;
    private LocalDate date;
    private Integer duration;
    private Long ratePerHour;
    private Long total;

}

package com.example.employeemanagement.employee.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FilterResponse {

    private List<Long> skillIds;
    private List<Long> certificateIds;
    private Float startExp;
    private Float endExp;
    private Long startSalary;
    private Long endSalary;
}

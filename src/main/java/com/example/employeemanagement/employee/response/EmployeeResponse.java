package com.example.employeemanagement.employee.response;


import com.example.employeemanagement.certificate.entity.Certificate;
import com.example.employeemanagement.payroll.entity.Payroll;
import com.example.employeemanagement.skills.entity.Skills;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse  {
    private Long id;
    private String name;
    private String departmentName;
    private String designationName;
    private String phoneNumber;
    private String email;
    private LocalDate dateOfBirth;
    private String address;
    private Float experience;
    private String employmentType;
    private List<Skills> employeeSkills;
    private List<Certificate> employeeCertificate;
    private Payroll payroll;
    private String status;
    private LocalDate joinDate;
    private LocalDate leftDate;
    private Long EmployeeTeamId;


}

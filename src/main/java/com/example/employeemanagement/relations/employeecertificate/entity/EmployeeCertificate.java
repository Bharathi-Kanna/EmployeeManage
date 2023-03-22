package com.example.employeemanagement.relations.employeecertificate.entity;
import jakarta.persistence.*;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="emp_certificate_id")
    private Long  employeeCertificateId;
    @NotNull
    @Column(name="emp_id")
    private Long employeeId;
    @NotNull
    @Column(name="certificate_id")
    private Long certificateId;
}

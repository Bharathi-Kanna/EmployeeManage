package com.example.employeemanagement.relations.employeeskills.entity;
import jakarta.persistence.*;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emp_skill_id")
    private Long  employeeSkillId;
    @NotNull
    @Column(name="emp_id")
    private Long employeeId;
    @NotNull
    @Column(name="skill_id")
    private Long skillId;
}

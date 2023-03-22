package com.example.employeemanagement.skills.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="skill_id")
    private Long  skillId;
    @Column(name="skill_name")
    private String skillName;

}

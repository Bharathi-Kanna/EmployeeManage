package com.example.employeemanagement.relations.employeecertificate.repositoy;

import com.example.employeemanagement.relations.employeecertificate.entity.EmployeeCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeCertificateRepo extends JpaRepository<EmployeeCertificate,Long> {
    List<EmployeeCertificate> findByEmployeeId(Long id);
    List<EmployeeCertificate> findByCertificateId(Long id);
}

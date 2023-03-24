package com.example.employeemanagement.employee.services.serviceinterface;

import com.example.employeemanagement.employee.entity.Employee;
import com.example.employeemanagement.employee.response.FilterResponse;
import com.example.employeemanagement.relations.employeecertificate.entity.EmployeeCertificate;
import com.example.employeemanagement.relations.employeeskills.entity.EmployeeSkills;
import com.example.employeemanagement.employee.response.EmployeeResponse;
import com.example.employeemanagement.generics.ServiceInterface;

import java.util.List;

public interface EmployeeServiceInterface extends ServiceInterface<Employee> {



    EmployeeResponse findEntityByIdWithNames(Long id);
    List<EmployeeResponse> findAllEmployeeByEntityId(Long id,String entity);
    EmployeeSkills  addSkills(EmployeeSkills employeeSkills);
    EmployeeCertificate addCertificate(EmployeeCertificate employeeCertificate);
    void deleteEmployeeSkills(Long empId,Long skillId);
    void deleteEmployeeCertificate(Long empId,Long certificateId);
    List<EmployeeResponse> findWithFilter(FilterResponse filterResponse);
    List<EmployeeResponse> skillFilter(FilterResponse filterResponse,List<EmployeeResponse> employeeResponseList);
    List<EmployeeResponse> certificateFilter(FilterResponse filterResponse,List<EmployeeResponse> employeeResponseList);
    List<EmployeeResponse> salaryFilter(FilterResponse filterResponse,List<EmployeeResponse> employeeResponseList);
    List<EmployeeResponse> experienceFilter(FilterResponse filterResponse,List<EmployeeResponse> employeeResponseList);
    List<String> sortBySalary();




}

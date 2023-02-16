package com.example.EmployeeManagement.services.serviceclass.entityservices;

import com.example.EmployeeManagement.Exception.EntityNotFound;
import com.example.EmployeeManagement.entity.entities.Department;
import com.example.EmployeeManagement.entity.entities.Designation;
import com.example.EmployeeManagement.entity.entities.Employee;
import com.example.EmployeeManagement.repository.entityrepo.DepartmentRepo;
import com.example.EmployeeManagement.repository.entityrepo.DesignationRepo;
import com.example.EmployeeManagement.repository.entityrepo.EmployeeRepo;
import com.example.EmployeeManagement.response.EmployeeResponse;
import com.example.EmployeeManagement.services.serviceinterface.entityinterface.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceInterface  {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    DesignationRepo designationRepo;
    @Override
    public List<Employee> findAllEntity() {
        return employeeRepo.findAll();
    }
    @Override
    public Employee updateEntity(Long id,Employee newEmployee) {
        Employee employee = findEntityById(id);
        employee.setName(newEmployee.getName());
        employee.setAddress(newEmployee.getAddress());
        employee.setDateOfBirth(newEmployee.getDateOfBirth());
        employee.setEmail(newEmployee.getEmail());
        employee.setDepartmentId(newEmployee.getDepartmentId());
        employee.setDesignationId(newEmployee.getDesignationId());
        employee.setPhoneNumber(newEmployee.getPhoneNumber());
        employee.setStatus(newEmployee.getStatus());
        employee.setJoinDate(newEmployee.getJoinDate());
        employee.setLeftDate(newEmployee.getLeftDate());
        return employeeRepo.save(employee);
    }
    @Override
    public EmployeeResponse findEntityByIdWithNames(Long id) {
        Employee employee = findEntityById(id);
        return findEmployeeResponse(employee);
    }

    @Override
    public Employee addEntity(Employee employee){
        return employeeRepo.save(employee);
    }

    @Override
    public Employee findEntityById(Long id){
        return employeeRepo.findById(id).orElseThrow(() -> new EntityNotFound("employee not found " + id));
    }

    @Override
    public void deleteById(Long id) {
        employeeRepo.deleteById(id);
    }

    public List<EmployeeResponse> findAllEmployeeByEntityId(Long id,String entity) {

        List<EmployeeResponse> employeeResponseList =new ArrayList<>();
        List<Employee> employees = new ArrayList<>();

        if(entity.equals("DEP"))
            employees = employeeRepo.findByDepartmentId(id);
        else if(entity.equals("DES"))
            employees = employeeRepo.findByDesignationId(id);
        else
            employees = employeeRepo.findAll();

        employees.forEach((employee)->{
            employeeResponseList.add(findEmployeeResponse(employee));
        });
        return employeeResponseList ;
    }

    private EmployeeResponse findEmployeeResponse(Employee employee){
        EmployeeResponse employeeResponse=new EmployeeResponse();
        String departmentName;
        String designationName;
        employeeResponse.setEmployeeId(employee.getEmployeeId());
        employeeResponse.setName(employee.getName());
        Department department  = departmentRepo.findById(employee.getDepartmentId()).orElseThrow(() -> new EntityNotFound("department not found "));
        departmentName=department.getDepartmentName();
        employeeResponse.setDepartmentName(departmentName);
        Designation designation = designationRepo.findById(employee.getDesignationId()).orElseThrow(() -> new EntityNotFound("designation not found "));
        designationName=designation.getDesignationName();
        employeeResponse.setDesignationName(designationName);
        employeeResponse.setAddress(employee.getAddress());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setDateOfBirth(employee.getDateOfBirth());
        employeeResponse.setStatus(employee.getStatus());
        employeeResponse.setJoinDate(employee.getJoinDate());
        employeeResponse.setLeftDate(employee.getLeftDate());
        employeeResponse.setPhoneNumber(employee.getPhoneNumber());
        return employeeResponse;
    }


}


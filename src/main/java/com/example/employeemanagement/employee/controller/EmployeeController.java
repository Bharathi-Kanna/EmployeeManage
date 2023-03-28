package com.example.employeemanagement.employee.controller;
import com.example.employeemanagement.employee.response.FilterResponse;
import com.example.employeemanagement.generics.ControllerInterface;
import com.example.employeemanagement.employee.entity.Employee;
import com.example.employeemanagement.relations.employeecertificate.entity.EmployeeCertificate;
import com.example.employeemanagement.relations.employeeskills.entity.EmployeeSkills;
import com.example.employeemanagement.employee.response.EmployeeResponse;
import com.example.employeemanagement.employee.services.serviceinterface.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController implements ControllerInterface<Employee>{

    @Autowired
    private EmployeeServiceInterface employeeServices;

    @GetMapping("/findAll")
    public ResponseEntity<List<EmployeeResponse>> findAllEntityWithNames(@RequestParam(defaultValue = "1") Long id,@RequestParam(defaultValue = "EMP") String entity){
        return new ResponseEntity<>(employeeServices.findAllEmployeeByEntityId(id,entity),HttpStatus.OK);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<EmployeeResponse> findEntityByIdWithId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeServices.findEntityByIdWithNames(id),HttpStatus.OK);
    }

    @GetMapping("/findAllWithId")
    public ResponseEntity<List<Employee>> findAllEntity() {
        return new ResponseEntity<>(employeeServices.findAllEntity(), HttpStatus.OK) ;
    }

    @Override
    @GetMapping("/findByIdWithId/{id}")
    public ResponseEntity<Employee> findEntityById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeServices.findEntityById(id),HttpStatus.OK);
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<Employee> addEntity(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeServices.addEntity(employee), HttpStatus.OK);
    }
    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEntity(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeServices.updateEntity(id,employee),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        employeeServices.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping("/addSkills")
    public ResponseEntity<EmployeeSkills> addSkills(@RequestBody EmployeeSkills employeeSkills){
        return new ResponseEntity<>(employeeServices.addSkills(employeeSkills),HttpStatus.OK);
    }
    @PostMapping("/addCertificate")
    public ResponseEntity<EmployeeCertificate> addCertificate(@RequestBody EmployeeCertificate employeeCertificate){

        return new ResponseEntity<>(employeeServices.addCertificate(employeeCertificate),HttpStatus.OK);
    }
    @DeleteMapping("/deleteEmployeeSkill/{empId}/{skillId}")
    public ResponseEntity<HttpStatus> deleteEmployeeSkill(@PathVariable("empId") Long empId,@PathVariable("skillId")Long skillId){
        employeeServices.deleteEmployeeSkills(empId,skillId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/deleteEmployeeCertificate/{empId}/{certificateId}")
    public ResponseEntity<HttpStatus> deleteEmployeeCertificate(@PathVariable("empId") Long empId,@PathVariable("certificateId")Long certificateId){
        employeeServices.deleteEmployeeCertificate(empId,certificateId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/bySalary")
    public ResponseEntity<List<String>> sortBySalary(){
        return new ResponseEntity<>(employeeServices.sortBySalary(),HttpStatus.OK);
    }
    @PostMapping("/findWithFilter")
    public ResponseEntity<List<EmployeeResponse>> findWithFilter(@RequestBody FilterResponse filterResponse){
        return new ResponseEntity<>(employeeServices.findWithFilter(filterResponse),HttpStatus.OK);
    }
}

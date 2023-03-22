package com.example.employeemanagement.department.controller;
import com.example.employeemanagement.department.response.DepartmentResponse;
import com.example.employeemanagement.generics.ControllerInterface;
import com.example.employeemanagement.department.entity.Department;
import com.example.employeemanagement.department.services.serviceinterface.DepartmentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/department")
public class DepartmentController implements ControllerInterface<Department> {
    @Autowired
    private DepartmentServiceInterface departmentService;
//    @GetMapping("/findAll")
    @Override
    public ResponseEntity<List<Department>> findAllEntity() {
//
        return null;
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<DepartmentResponse>> findAllDepartment(){
        List<Department> list = departmentService.findAllEntity();
        List<DepartmentResponse> responseList = new ArrayList<>();
        list.forEach(e -> {
            DepartmentResponse eResponse = new DepartmentResponse(e);
            responseList.add(eResponse);
        });
        return new ResponseEntity<List<DepartmentResponse>>(responseList, HttpStatus.OK) ;
    }


    @GetMapping("/findById/{id}")
    @Override
    public ResponseEntity<Department> findEntityById(@PathVariable Long id) {
        return new ResponseEntity(departmentService.findEntityById(id),HttpStatus.OK);
    }
    @PostMapping("/add")
    @Override
    public ResponseEntity<Department> addEntity(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.addEntity(department),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<Department> updateEntity(@PathVariable Long id,@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.updateEntity(id,department),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        departmentService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

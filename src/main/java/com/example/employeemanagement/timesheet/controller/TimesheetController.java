package com.example.employeemanagement.timesheet.controller;

import com.example.employeemanagement.timesheet.entity.RequestTimesheet;
import com.example.employeemanagement.timesheet.entity.Timesheet;
import com.example.employeemanagement.timesheet.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timesheet")

public class TimesheetController {
    @Autowired
    TimesheetService timesheetService;
    @PostMapping("/generateTimesheet")

    public ResponseEntity<HttpStatus> generateTimesheet(){
        timesheetService.generateTimesheet();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/updateTimesheet")
    public ResponseEntity<HttpStatus> updateTimesheet(@RequestBody Timesheet timesheet){
        timesheetService.updateTimesheet(timesheet);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/findAllTimesheet/{id}")
    public ResponseEntity<List<Timesheet>> findAllTimesheet(@PathVariable Long id){
        return new ResponseEntity<>(timesheetService.findAllTimesheet(id),HttpStatus.OK);
    }
    @GetMapping("/findByGivenDate")
    public ResponseEntity<List<Timesheet>> findByGivenDates(@RequestBody RequestTimesheet requestTimesheet){
        return new ResponseEntity<>(timesheetService.findByGivenDate(requestTimesheet),HttpStatus.OK);
    }

}

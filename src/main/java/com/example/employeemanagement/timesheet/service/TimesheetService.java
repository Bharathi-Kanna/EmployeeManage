package com.example.employeemanagement.timesheet.service;

import com.example.employeemanagement.employee.entity.Employee;
import com.example.employeemanagement.employee.repository.EmployeeRepo;
import com.example.employeemanagement.project.entity.Project;
import com.example.employeemanagement.project.repository.ProjectRepo;
import com.example.employeemanagement.relations.employeeteam.entity.EmployeeTeam;
import com.example.employeemanagement.relations.employeeteam.repository.EmployeeTeamRepo;
import com.example.employeemanagement.teams.entity.Teams;
import com.example.employeemanagement.teams.repository.TeamRepo;
import com.example.employeemanagement.timesheet.entity.RequestTimesheet;
import com.example.employeemanagement.timesheet.entity.Timesheet;
import com.example.employeemanagement.timesheet.repository.TimesheetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimesheetService {
    @Autowired
    TimesheetRepo timesheetRepo;
    @Autowired
    EmployeeTeamRepo employeeTeamRepo;
    @Autowired
    TeamRepo teamRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ProjectRepo projectRepo;


    public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();

        while (!startDate.isAfter(endDate)) {
            dates.add(startDate);
            startDate = startDate.plusDays(1);
        }

        return dates;
    }
    public void generateTimesheet(){
        List<EmployeeTeam> employeeTeamList=employeeTeamRepo.findAll();
        employeeTeamList.forEach(employeeTeam -> {
            if(employeeTeam.getLatestTimesheetGeneratedDate()!=LocalDate.now()){
                List<LocalDate> dates=getDatesBetween(employeeTeam.getLatestTimesheetGeneratedDate(),LocalDate.now());
                dates.forEach(date->{
                    if((employeeTeam.getEndDate()==null || employeeTeam.getEndDate().compareTo(date)>=0)&&LocalDate.now()!=date){
                        Timesheet timesheet=new Timesheet();
                        Optional<Employee> employee= employeeRepo.findById(employeeTeam.getEmployeeId());
                        timesheet.setEmployeeName(employee.get().getName());
                        Optional<Teams> teams=teamRepo.findById(employeeTeam.getTeamId());
                        Optional<Project> project=projectRepo.findById(teams.get().getProjectId());
                        timesheet.setProjectId(teams.get().getProjectId());
                        timesheet.setProjectName(project.get().getProjectName());
                        timesheet.setDate(date);
                        timesheet.setRatePerHour(employeeTeam.getRatePerHour());
                        timesheetRepo.save(timesheet);
                    }
                });
                employeeTeam.setLatestTimesheetGeneratedDate(LocalDate.now());
                employeeTeamRepo.save(employeeTeam);
            }
        });
    }
    public List<Timesheet> findAllTimesheet(Long id){
        return timesheetRepo.findByProjectId(id);
    }
    public void updateTimesheet(Timesheet timesheet){
        if(timesheet.getRatePerHour()!=null && timesheet.getDuration()!=null) {
            timesheet.setTotal(timesheet.getRatePerHour()* timesheet.getDuration());
        }
        timesheetRepo.save(timesheet);
    }
    public List<Timesheet> findByGivenDate(RequestTimesheet requestTimesheet){
        List<Timesheet> timesheetList = findAllTimesheet(requestTimesheet.getProjectId());
        List<Timesheet> timesheetList1 = new ArrayList<>();
        timesheetList.forEach(timesheet -> {
            if(timesheet.getDate().compareTo(requestTimesheet.getStartDate())>=0 && timesheet.getDate().compareTo(requestTimesheet.getEndDate())<=0){
                timesheetList1.add(timesheet);
            }
        });
        return timesheetList1;
    }

}

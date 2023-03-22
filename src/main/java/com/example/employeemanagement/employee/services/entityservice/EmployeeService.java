package com.example.employeemanagement.employee.services.entityservice;
import com.example.employeemanagement.exception.EntityNotFound;
import com.example.employeemanagement.certificate.entity.Certificate;
import com.example.employeemanagement.department.entity.Department;
import com.example.employeemanagement.department.repository.DepartmentRepo;
import com.example.employeemanagement.designation.entity.Designation;
import com.example.employeemanagement.designation.repository.DesignationRepo;
import com.example.employeemanagement.employee.entity.Employee;
import com.example.employeemanagement.employee.repository.EmployeeRepo;
import com.example.employeemanagement.relations.employeecertificate.entity.EmployeeCertificate;
import com.example.employeemanagement.relations.employeeskills.entity.EmployeeSkills;
import com.example.employeemanagement.relations.employeecertificate.repositoy.EmployeeCertificateRepo;
import com.example.employeemanagement.relations.employeeskills.repository.EmployeeSkillsRepo;
import com.example.employeemanagement.employee.response.EmployeeResponse;
import com.example.employeemanagement.certificate.services.serviceinterface.CertificateServiceInterface;
import com.example.employeemanagement.employee.services.serviceinterface.EmployeeServiceInterface;
import com.example.employeemanagement.skills.services.serviceinterface.SkillsServiceInterface;
import com.example.employeemanagement.skills.entity.Skills;
import com.example.employeemanagement.skills.repository.SkillsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeService implements EmployeeServiceInterface  {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    DesignationRepo designationRepo;
    @Autowired
    EmployeeSkillsRepo employeeSkillsRepo;
    @Autowired
    EmployeeCertificateRepo employeeCertificateRepo;
    @Autowired
    SkillsRepo skillsRepo;
    @Autowired
    SkillsServiceInterface skillsService;
    @Autowired
    CertificateServiceInterface certificateService;
    @Override
    public List<Employee> findAllEntity() {
        return employeeRepo.findAll();
    }
    @Override
    public Employee updateEntity(Long id, Employee newEmployee) {

        Employee employee = findEntityById(id);
        if(newEmployee.getEmployeeId()==null)
            newEmployee.setEmployeeId(employee.getEmployeeId());
        if(newEmployee.getName()==null)
            newEmployee.setName(employee.getName());
        if(newEmployee.getAddress()==null)
            newEmployee.setAddress(employee.getAddress());
        if(newEmployee.getDateOfBirth()==null)
            newEmployee.setDateOfBirth(employee.getDateOfBirth());
        if(newEmployee.getEmail()==null)
            newEmployee.setEmail(employee.getEmail());
        if(newEmployee.getDepartmentId()==null)
            newEmployee.setDepartmentId(employee.getDepartmentId());
        if(newEmployee.getDesignationId()==null)
            newEmployee.setDesignationId(employee.getDesignationId());
        if(newEmployee.getPhoneNumber()==null)
            newEmployee.setPhoneNumber(employee.getPhoneNumber());
        if(newEmployee.getStatus()==null)
            newEmployee.setStatus(employee.getStatus());
        if(newEmployee.getJoinDate()==null)
            newEmployee.setJoinDate(employee.getJoinDate());
        if(newEmployee.getLeftDate()==null)
            newEmployee.setLeftDate(employee.getLeftDate());
        if(newEmployee.getEmploymentType()==null)
            newEmployee.setEmploymentType(employee.getEmploymentType());
        if(newEmployee.getExperience()==null)
            newEmployee.setExperience(employee.getExperience());
        if(newEmployee.getPayroll()==null) {
            newEmployee.setPayroll(employee.getPayroll());
        }
        return employeeRepo.save(newEmployee);
    }
    @Override
    public EmployeeResponse findEntityByIdWithNames(Long id) {
        Employee employee = findEntityById(id);
        return findEmployeeResponse(employee);
    }
    @Override
    public List<String> sortBySalary(){
        return employeeRepo.sortBySalary();
    }

    @Override
    public Employee addEntity(Employee employee){
       if(!(employee.getPayroll()==null))
           employee.getPayroll().setTotalSalary(employee.getPayroll().getSalary()+employee.getPayroll().getBonus());
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
        else if (entity.equals("EXP")){
            employees=employeeRepo.findByOrderByExperienceDesc();
        }
        else if(entity.equals("ACTIVE")||entity.equals("INACTIVE")){
            List<Employee> employeeList = employeeRepo.findAll();
            List<Employee> employeeList1 = new ArrayList<>();
            employeeList.forEach(employee -> {
                String string = employee.getStatus();
                if((string).equals((String)entity)){
                    employeeList1.add(employee);
                }
            });
            employees=employeeList1;
        }
        else if(entity.equals("SKILL")){
            List<EmployeeSkills> employeeSkillsList=employeeSkillsRepo.findBySkillId(id);
            List<Employee> employees1 = new ArrayList<>();
            employeeSkillsList.forEach(employeeSkills -> {
                Employee employee = findEntityById(employeeSkills.getEmployeeId());
                employees1.add(employee);
            });
            employees=employees1;
        }
        else if(entity.equals("CER")){
            List<EmployeeCertificate> employeeCertificateList = employeeCertificateRepo.findByCertificateId(id);
            List<Employee> employees1 = new ArrayList<>();
            employeeCertificateList.forEach(employeeCertificate -> {
                Employee employee = findEntityById(employeeCertificate.getEmployeeId());
                employees1.add(employee);
            });
            employees=employees1;
        }
        else
            employees = employeeRepo.findAll();

        employees.forEach((employee)->{
            employeeResponseList.add(findEmployeeResponse(employee));
        });
        return employeeResponseList ;
    }

    @Override
    public EmployeeSkills addSkills(EmployeeSkills employeeSkills) {
       return employeeSkillsRepo.save(employeeSkills);
    }

    @Override
    public EmployeeCertificate addCertificate(EmployeeCertificate employeeCertificate) {
        return employeeCertificateRepo.save(employeeCertificate);
    }
    @Override
    public void deleteEmployeeSkills(Long empId, Long skillId) {
        List<EmployeeSkills> employeeSkillsList = employeeSkillsRepo.findByEmployeeId(empId);
        EmployeeSkills employeeSkill=new EmployeeSkills();
        employeeSkillsList.forEach(employeeSkills -> {
            if(employeeSkills.getSkillId()==skillId){
                employeeSkillsRepo.deleteById(employeeSkills.getEmployeeSkillId());
            }
        });
    }
    @Override
    public void deleteEmployeeCertificate(Long empId, Long certificateId) {
        List<EmployeeCertificate> employeeCertificateList = employeeCertificateRepo.findByEmployeeId(empId);
        EmployeeCertificate employeeCertificate  =new EmployeeCertificate();
        employeeCertificateList.forEach(employeeCertificates ->{
            if(employeeCertificates.getCertificateId()==certificateId){
                employeeCertificateRepo.deleteById(employeeCertificates.getEmployeeCertificateId());
            }
        } );
    }

    private EmployeeResponse findEmployeeResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        String departmentName;
        String designationName;
        employeeResponse.setId(employee.getEmployeeId());
        employeeResponse.setName(employee.getName());
        Department department = departmentRepo.findById(employee.getDepartmentId()).orElseThrow(() -> new EntityNotFound("department not found "));
        departmentName = department.getDepartmentName();
        employeeResponse.setDepartmentName(departmentName);
        Designation designation = designationRepo.findById(employee.getDesignationId()).orElseThrow(() -> new EntityNotFound("designation not found "));
        designationName = designation.getDesignationName();
        employeeResponse.setDesignationName(designationName);
        employeeResponse.setAddress(employee.getAddress());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setDateOfBirth(employee.getDateOfBirth());
        employeeResponse.setStatus(employee.getStatus());
        employeeResponse.setJoinDate(employee.getJoinDate());
        employeeResponse.setLeftDate(employee.getLeftDate());
        employeeResponse.setPhoneNumber(employee.getPhoneNumber());
        employeeResponse.setExperience(employee.getExperience());
        employeeResponse.setEmploymentType(employee.getEmploymentType());
        employeeResponse.setPayroll(employee.getPayroll());
        List<EmployeeSkills> employeeSkillsList = employeeSkillsRepo.findByEmployeeId(employee.getEmployeeId());
        List<EmployeeCertificate> employeeCertificateList = employeeCertificateRepo.findByEmployeeId(employee.getEmployeeId());
        List<Skills> skillsList = new ArrayList<>();
        List<Certificate> certificateList = new ArrayList<>();
        employeeSkillsList.forEach(employeeSkills -> {
            Skills skills = skillsService.findEntityById(employeeSkills.getSkillId());
            skillsList.add(skills);
        });
        employeeCertificateList.forEach(employeeCertificate -> {
            Certificate certificate = certificateService.findEntityById(employeeCertificate.getCertificateId());
            certificateList.add(certificate);
        });
        employeeResponse.setEmployeeSkills(skillsList);
        employeeResponse.setEmployeeCertificate(certificateList);
        return employeeResponse;
    }
}


package com.example.employeemanagement.payroll.services.entityservice;
import com.example.employeemanagement.payroll.entity.Payroll;
import com.example.employeemanagement.payroll.repository.PayrollRepo;
import com.example.employeemanagement.payroll.services.serviceinterface.PayrollServiceInterface;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PayrollService implements PayrollServiceInterface {
    @Autowired
    private PayrollRepo payrollRepo;
    @Override
    public Payroll addEntity(Payroll entity) {
        return null;
    }
    @Override
    public Payroll updateEntity(Long id, Payroll newEntity) {
        return  payrollRepo.save(newEntity);
    }
    @Override
    public List<Payroll> findAllEntity() {
        return Collections.emptyList();
    }
    @Override
    public Payroll findEntityById(Long id) {
        return null;
    }
    @Override
    public void deleteById(Long id) {
//override
    }
}

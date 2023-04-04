package com.example.employeemanagement.certificate.services.entityservice;

import com.example.employeemanagement.exception.EntityNotFound;
import com.example.employeemanagement.certificate.entity.Certificate;
import com.example.employeemanagement.certificate.repository.CertificateRepo;
import com.example.employeemanagement.certificate.services.serviceinterface.CertificateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CertificateService implements CertificateServiceInterface {
    @Autowired
    CertificateRepo certificateRepo;
    @Override
    public Certificate addEntity(Certificate entity) {
        return certificateRepo.save(entity);
    }
    @Override
    public Certificate updateEntity(Long id, Certificate newEntity) {
        Certificate certificate = findEntityById(id);
        if(newEntity.getCertificateId()==null)
            newEntity.setCertificateId(certificate.getCertificateId());
        if(newEntity.getCertificateName()==null)
            newEntity.setCertificateName(certificate.getCertificateName());
        return certificateRepo.save(newEntity);
    }
    @Override
    public List<Certificate> findAllEntity() {
        return certificateRepo.findAll();
    }
    @Override
    public Certificate findEntityById(Long id) {
        return certificateRepo.findById(id).orElseThrow(() -> new EntityNotFound("certificate not found " + id));
    }
    @Override
    public void deleteById(Long id) {
//        override
    }
}

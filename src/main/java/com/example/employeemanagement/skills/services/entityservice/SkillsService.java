package com.example.employeemanagement.skills.services.entityservice;
import com.example.employeemanagement.exception.EntityNotFound;
import com.example.employeemanagement.skills.entity.Skills;
import com.example.employeemanagement.skills.repository.SkillsRepo;
import com.example.employeemanagement.skills.services.serviceinterface.SkillsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SkillsService implements SkillsServiceInterface {
    @Autowired
    SkillsRepo skillsRepo;
    @Override
    public Skills addEntity(Skills entity) {
        return skillsRepo.save(entity);
    }
    @Override
    public Skills updateEntity(Long id, Skills newEntity) {
        Skills skills = findEntityById(id);
        if(newEntity.getSkillId()==null)
            newEntity.setSkillId(skills.getSkillId());
        if(newEntity.getSkillName()==null)
            newEntity.setSkillName(skills.getSkillName());
        return skillsRepo.save(newEntity);
    }
    @Override
    public List<Skills> findAllEntity() {
        return skillsRepo.findAll();
    }

    @Override
    public Skills findEntityById(Long id) {
        return skillsRepo.findById(id).orElseThrow(() -> new EntityNotFound("skill not found " + id));
    }
    @Override
    public void deleteById(Long id) {
//override
    }
}

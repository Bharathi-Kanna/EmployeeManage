package com.example.employeemanagement.skills.entityservice;


import com.example.employeemanagement.skills.entity.Skills;
import com.example.employeemanagement.skills.repository.SkillsRepo;
import com.example.employeemanagement.skills.services.entityservice.SkillsService;
import org.aspectj.lang.annotation.Before;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.Assert.*;


import java.util.Optional;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SkillsServiceTest {


    @Autowired
    private SkillsService skillsService;
    @MockBean
    private SkillsRepo skillsRepo;
    @BeforeEach
    void setup(){
        Optional<Skills> skills=Optional.of(new Skills(1L,"React"));
        Mockito.when(skillsRepo.findById(1L)).thenReturn(skills);
    }
    @Test
    public void testGetSkillById(){
        String skillName="React";
        Skills skillById=skillsService.findEntityById(1L);
       Assert.assertEquals(skillName,skillById.getSkillName());
    }
}

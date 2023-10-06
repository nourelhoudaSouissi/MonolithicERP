package com.csidigital.management.controller;

import com.csidigital.management.service.impl.SkillsImpl;
import com.csidigital.shared.dto.request.SkillsRequest;
import com.csidigital.shared.dto.response.SkillsResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@Transactional
@RequestMapping("/rh/skills")
public class SkillsController {
    @Autowired
    private SkillsImpl skills;

    @GetMapping("/getAll")
    public List<SkillsResponse> getAllSkills(){
        return skills.getAllSkills();
    }

    @GetMapping("/getSkills/{id}")
    public SkillsResponse getSkillsById(@PathVariable Long id){
        return skills.getSkillsById(id);
    }

    @PostMapping("/addSkills")
    public SkillsResponse createSkills(@Valid @RequestBody SkillsRequest skillsRequest){
        return skills.createSkills(skillsRequest);
    }

    @PostMapping("/addAllSkills")
    public List<SkillsResponse> createSkills(@Valid @RequestBody List<SkillsRequest> skillsRequests) {
        List<SkillsResponse> skillsResponses = new ArrayList<>();

        for (SkillsRequest skillsRequest : skillsRequests) {
            SkillsResponse skillsResponse = skills.createSkills(skillsRequest);
            skillsResponses.add(skillsResponse);
        }

        return skillsResponses;
    }

    @PutMapping("/updateSkills/{id}")
    public SkillsResponse updateSkills(@Valid @RequestBody SkillsRequest skillsRequest,
                                                       @PathVariable Long id){
        return skills.updateSkills(skillsRequest, id);
    }

    @DeleteMapping("/deleteSkills/{id}")
    public void deleteSkills(@PathVariable Long id){
        skills.deleteSkills(id);
    }
}

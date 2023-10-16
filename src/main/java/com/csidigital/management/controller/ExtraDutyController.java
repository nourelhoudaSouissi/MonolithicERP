package com.csidigital.management.controller;

import com.csidigital.management.service.impl.ExtraDutyServiceImpl;
import com.csidigital.shared.dto.request.ExtraDutyRequest;
import com.csidigital.shared.dto.response.ExtraDutyResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/extraDuties")
//@CrossOrigin(origins = "${cross.origin.url}")
public class ExtraDutyController {
    @Autowired
    private ExtraDutyServiceImpl extraDutyService ;

    @GetMapping()
    public List<ExtraDutyResponse> getAllExtraDuties() {
        return extraDutyService.getAllExtraDuties();
    }

    @GetMapping("/{id}")
    public ExtraDutyResponse getExtraDutyById(@PathVariable Long id){
        return extraDutyService.getExtraDutyById(id);
    }

    @PostMapping()
    public ExtraDutyResponse createExtraDuty(@Valid @RequestBody ExtraDutyRequest extraDutyRequest){
        return extraDutyService.createExtraDuty(extraDutyRequest);
    }

    @PutMapping("/{id}")
    public ExtraDutyResponse updateExtraDuty(@Valid @RequestBody ExtraDutyRequest extraDutyRequest,
                                                 @PathVariable Long id){
        return extraDutyService.updateExtraDuty(extraDutyRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteExtraDuty(@PathVariable Long id){
        extraDutyService.deleteExtraDuty(id);
    }
}

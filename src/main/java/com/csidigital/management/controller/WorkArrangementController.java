package com.csidigital.management.controller;

import com.csidigital.management.service.impl.WorkArrangementServiceImpl;
import com.csidigital.shared.dto.request.WorkArrangementRequest;
import com.csidigital.shared.dto.response.WorkArrangementResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/workArrangements")
//@CrossOrigin(origins = "${cross.origin.url}")
public class WorkArrangementController {
    @Autowired
    private WorkArrangementServiceImpl workArrangementService ;

    @GetMapping()
    public List<WorkArrangementResponse> getAllWorkArrangements() {
        return workArrangementService.getAllWorkArrangements();
    }

    @GetMapping("/{id}")
    public WorkArrangementResponse getWorkArrangementById(@PathVariable Long id){
        return workArrangementService.getWorkArrangementById(id);
    }

    @PostMapping()
    public WorkArrangementResponse createWorkArrangement(@Valid @RequestBody WorkArrangementRequest workArrangementRequest){
        return workArrangementService.createWorkArrangement(workArrangementRequest);
    }

    @PutMapping("/{id}")
    public WorkArrangementResponse updateWorkArrangement(@Valid @RequestBody WorkArrangementRequest workArrangementRequest,
                                                 @PathVariable Long id){
        return workArrangementService.updateWorkArrangement(workArrangementRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkArrangement(@PathVariable Long id){
        workArrangementService.deleteWorkArrangement(id);
    }

}

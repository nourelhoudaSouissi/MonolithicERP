package com.csidigital.management.controller;

import com.csidigital.management.service.DisbursementService;
import com.csidigital.shared.dto.request.DisbursementDtoRequest;
import com.csidigital.shared.dto.response.DisbursementDtoResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/fm/disbursements")
@CrossOrigin("*")
public class DisbursementController {
    private final DisbursementService disbursementService;

    public DisbursementController(DisbursementService disbursementService) {
        this.disbursementService = disbursementService;

    }
    @GetMapping()
    public List<DisbursementDtoResponse> getAllDisbursement() {
        return disbursementService.getAllDisbursement();
    }

    @GetMapping("/{id}")
    public DisbursementDtoResponse getDisbursementById(@PathVariable Long id){
        return disbursementService.getByIdDisbursement(id);
    }

    @PostMapping()
    public DisbursementDtoResponse createDisbursement(@Valid @RequestBody DisbursementDtoRequest disbursementDtoRequest){
        return disbursementService.CreateDisbursement(disbursementDtoRequest);
    }

    @PutMapping("/{id}")
    public DisbursementDtoResponse updateDisbursement(@Valid @RequestBody DisbursementDtoRequest disbursementDtoRequest, @PathVariable Long id){
        return disbursementService.updateDisbursement(disbursementDtoRequest, id);
    }


    @DeleteMapping("/{id}")
    public void deleteDisbursement(@PathVariable Long id){
        disbursementService.deleteDisbursementById(id);
    }


}

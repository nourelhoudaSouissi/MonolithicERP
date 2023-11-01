package com.csidigital.management.controller;

import com.csidigital.management.service.AdditionalFeeService;
import com.csidigital.shared.dto.request.AdditionalFeeDtoRequest;
import com.csidigital.shared.dto.response.AdditionalFeeDtoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fm/additionalFees")
@CrossOrigin("*")
public class AdditionalFeeController {

    private final AdditionalFeeService additionalFeeService;
    @Autowired
    public AdditionalFeeController(AdditionalFeeService additionalFeeService) {
        this.additionalFeeService = additionalFeeService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AdditionalFeeDtoResponse<Number>> getAllAdditionalFee() {
        return additionalFeeService.getAllAdditionalFee();
    }

    @GetMapping("/{id}")
    public AdditionalFeeDtoResponse<Number> getBAdditionalFeeById(@PathVariable Long id){
        return additionalFeeService.getAdditionalFeeById(id);
    }

    @PostMapping()
    public List<AdditionalFeeDtoResponse<Number>> createAdditionalFees(@RequestBody List<AdditionalFeeDtoRequest> additionalFeeDtoRequests){
        return additionalFeeService.CreateAdditionalFee(additionalFeeDtoRequests);
    }


    @PutMapping("/{id}")
    public AdditionalFeeDtoResponse<Number> updateAdditionalFee(@Valid @RequestBody  AdditionalFeeDtoRequest additionalFeeDtoRequest, @PathVariable Long id){
        return additionalFeeService.updateAdditionalFee(additionalFeeDtoRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAdditionalFee(@PathVariable Long id){
        additionalFeeService.deleteAdditionalFeeById(id);
    }

}

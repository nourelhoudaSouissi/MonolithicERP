package com.csidigital.management.controller;


import com.csidigital.management.service.impl.BenefitRCImpl;
import com.csidigital.shared.dto.request.BenefitRCRequest;
import com.csidigital.shared.dto.response.BenefitRCResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rh/Benefit")
public class BenefitRCController {
    @Autowired
    private BenefitRCImpl benefitRCImpl ;

    @GetMapping("getAll")
    public List<BenefitRCResponse> getAllBenefitRC() {
        return benefitRCImpl.getAllBenefitRC();
    }

    @GetMapping("/getBy/{id}")
    public BenefitRCResponse getBenefitRCById(@PathVariable Long id){
        return benefitRCImpl.getBenefitRCById(id);
    }

    @PostMapping("/add")
    public BenefitRCResponse createBenefitRC(@Valid @RequestBody BenefitRCRequest benefitRCRequest){
        return benefitRCImpl.createBenefitRC(benefitRCRequest);
    }

    @PutMapping("/update/{id}")
    public BenefitRCResponse updateBenefitRC(@Valid @RequestBody BenefitRCRequest benefitRCRequest,
                                                   @PathVariable Long id){
        return benefitRCImpl.updateBenefitRC(benefitRCRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBenefitRC(@PathVariable Long id){
        benefitRCImpl.deleteBenefitRC(id);
    }

}

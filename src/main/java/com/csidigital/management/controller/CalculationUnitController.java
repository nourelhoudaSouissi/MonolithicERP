package com.csidigital.management.controller;

import com.csidigital.management.service.CalculationUnitService;
import com.csidigital.management.service.TvaCodeService;
import com.csidigital.shared.dto.request.CalculationUnitRequest;
import com.csidigital.shared.dto.request.TvaCodeRequest;
import com.csidigital.shared.dto.response.CalculationUnitResponse;
import com.csidigital.shared.dto.response.TvaCodeResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referentiel/calculationUnit")
public class CalculationUnitController {

    @Autowired
    private CalculationUnitService calculationUnitService ;

    @GetMapping("getAllTvaCodesCalculationUnits")
    public List<CalculationUnitResponse> getAllCalculationUnits() {

        return calculationUnitService.getAllCalculationUnits();
    }

    @GetMapping("/getCalculationUnitById/{id}")
    public CalculationUnitResponse getCalculationUnitById(@PathVariable Long id){
        return calculationUnitService.getCalculationUnitById(id);
    }


    @PostMapping(value = "/createCalculationUnit", produces = "application/json")
    @ResponseBody
    public CalculationUnitResponse createCalculationUnit(@Valid @RequestBody CalculationUnitRequest calculationUnitRequest){
        return calculationUnitService.createCalculationUnit(calculationUnitRequest);
    }

    @PutMapping("/updateCalculationUnit/{id}")
    public CalculationUnitResponse updateCalculationUnit(@Valid @RequestBody CalculationUnitRequest calculationUnitRequest,
                                         @PathVariable Long id){
        return calculationUnitService.updateCalculationUnit(calculationUnitRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCalculationUnit(@PathVariable Long id){

        calculationUnitService.deleteCalculationUnit(id);
    }

}

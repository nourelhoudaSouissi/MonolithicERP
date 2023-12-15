package com.csidigital.management.controller;

import com.csidigital.management.service.PaymentTermService;
import com.csidigital.management.service.TvaCodeService;
import com.csidigital.shared.dto.request.PaymentTermRequest;
import com.csidigital.shared.dto.request.TvaCodeRequest;
import com.csidigital.shared.dto.response.PaymentTermResponse;
import com.csidigital.shared.dto.response.TvaCodeResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/referentiel/tvaCode")
public class TvaCodeController {

    @Autowired
    private TvaCodeService tvaCodeService ;

    @GetMapping("getAllTvaCodes")
    public List<TvaCodeResponse> getAllTvaCodes() {
        return tvaCodeService.getAllTvaCodes();
    }

    @GetMapping("/getTvaCodeById/{id}")
    public TvaCodeResponse getTvaCodeById(@PathVariable Long id){
        return tvaCodeService.getTvaCodeById(id);
    }


    @PostMapping(value = "/createTvaCode", produces = "application/json")
    @ResponseBody
    public TvaCodeResponse createTvaCode(@Valid @RequestBody TvaCodeRequest tvaCodeRequest){
        return tvaCodeService.createTvaCode(tvaCodeRequest);
    }

    @PutMapping("/updateTvaCode/{id}")
    public TvaCodeResponse updateTvaCode(@Valid @RequestBody TvaCodeRequest tvaCodeRequest,
                                                      @PathVariable Long id){
        return tvaCodeService.updateTvaCode(tvaCodeRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTvaCode(@PathVariable Long id){

        tvaCodeService.deleteTvaCode(id);
    }

}

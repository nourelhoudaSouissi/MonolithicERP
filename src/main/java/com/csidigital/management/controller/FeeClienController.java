package com.csidigital.management.controller;

import com.csidigital.management.service.FeeClientService;
import com.csidigital.shared.dto.request.FeeClientDtoRequest;
import com.csidigital.shared.dto.response.FeeClientDtoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fm/feeClients")
@CrossOrigin("*")
public class FeeClienController {

    private final FeeClientService feeClientService;
    @Autowired
    public FeeClienController(FeeClientService feeClientService) {
        this.feeClientService = feeClientService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<FeeClientDtoResponse<Number>> getFeeClient() {
        return feeClientService.getAllFeeClient();
    }

    @GetMapping("/{id}")
    public FeeClientDtoResponse<Number> getFeeClientById(@PathVariable Long id){
        return feeClientService.getFeeClientById(id);
    }

    @PostMapping()
    public List<FeeClientDtoResponse<Number>> createFeeClients(@RequestBody List<FeeClientDtoRequest> feeClientDtoRequests){
        return feeClientService.CreateFeeClient(feeClientDtoRequests);
    }

    @PutMapping("/{id}")
    public FeeClientDtoResponse<Number> updateFeeClient(@Valid @RequestBody  FeeClientDtoRequest feeClientDtoRequest, @PathVariable Long id){
        return feeClientService.updateFeeClient(feeClientDtoRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteFeeClient(@PathVariable Long id){
        feeClientService.deleteFeeClientById(id);
    }

}

package com.csidigital.management.controller;

import com.csidigital.management.service.TreasuryService;
import com.csidigital.shared.dto.request.TreasuryDtoRequest;
import com.csidigital.shared.dto.response.TreasuryDtoResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/fm/treasuries")
@CrossOrigin("*")
public class TreasuryController {
    private final TreasuryService treasuryService;

    public TreasuryController(TreasuryService treasuryService) {
        this.treasuryService = treasuryService;
    }

    @GetMapping()
    public List<TreasuryDtoResponse> getAllTreasury() {
        return treasuryService.getAllTreasury();
    }

    @GetMapping("/{id}")
    public TreasuryDtoResponse getTreasuryById(@PathVariable Long id){
        return treasuryService.getTreasuryById(id);
    }

    @PostMapping()
    public TreasuryDtoResponse createTreasury(@Valid @RequestBody TreasuryDtoRequest treasuryDtoRequest){
        return treasuryService.CreateTreasury(treasuryDtoRequest);
    }

    @PutMapping("/{id}")
    public TreasuryDtoResponse updateTreasury(@Valid @RequestBody  TreasuryDtoRequest treasuryDtoRequest, @PathVariable Long id){
        return treasuryService.updateTreasury(treasuryDtoRequest, id);
    }


    @DeleteMapping("/{id}")
    public void deleteTreasury(@PathVariable Long id){
        treasuryService.deleteTreasuryById(id);
    }
}

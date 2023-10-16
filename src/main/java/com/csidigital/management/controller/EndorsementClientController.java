package com.csidigital.management.controller;

import com.csidigital.management.service.impl.EndorsementClientImpl;
import com.csidigital.shared.dto.request.EndorsementClientRequest;
import com.csidigital.shared.dto.response.EndorsementClientResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/endorsementClient")

public class EndorsementClientController {

    @Autowired
    private EndorsementClientImpl endorsementImpl;

    @GetMapping("/getEndorsements")
    public List<EndorsementClientResponse> getAllEndorsements() {

        return endorsementImpl.getAllEndorsements();
    }

    @GetMapping("/get/{id}")
    public EndorsementClientResponse getEndorsementById(@PathVariable Long id){

        return endorsementImpl.getEndorsementById(id);
    }

    @PostMapping("/add")
    public EndorsementClientResponse createEndorsement(@Valid @RequestBody EndorsementClientRequest endorsementRequest){
        System.out.println(endorsementRequest);
        return endorsementImpl.createEndorsement(endorsementRequest);
    }

    @PutMapping("/update/{id}")
    public EndorsementClientResponse updateEndorsement(@Valid @RequestBody EndorsementClientRequest endorsementRequest,
                                                 @PathVariable Long id){
        return endorsementImpl.updateEndorsement(endorsementRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEndorsement(@PathVariable Long id){
        endorsementImpl.deleteEndorsement(id);
    }
}

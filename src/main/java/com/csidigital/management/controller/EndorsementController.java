package com.csidigital.management.controller;


import com.csidigital.management.service.impl.EndorsementImpl;
import com.csidigital.shared.dto.request.EndorsementRequest;
import com.csidigital.shared.dto.response.EndorsementResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/endorsement")
public class EndorsementController {

    @Autowired
    private EndorsementImpl endorsementImpl;

    @GetMapping("/getEndorsements")
    public List<EndorsementResponse> getAllEndorsements() {

        return endorsementImpl.getAllEndorsements();
    }

    @GetMapping("/get/{id}")
    public EndorsementResponse getEndorsementById(@PathVariable Long id){
        return endorsementImpl.getEndorsementById(id);
    }

    @PostMapping("/add")
    public EndorsementResponse createEndorsement(@Valid @RequestBody EndorsementRequest endorsementRequest){
        return endorsementImpl.createEndorsement(endorsementRequest);
    }

    @PutMapping("/update/{id}")
    public EndorsementResponse updateEndorsement(@Valid @RequestBody EndorsementRequest endorsementRequest,
                                         @PathVariable Long id){
        return endorsementImpl.updateEndorsement(endorsementRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEndorsement(@PathVariable Long id){
        endorsementImpl.deleteEndorsement(id);
    }

    @PutMapping("/updateStatusById")
    void updateStatusById(Long id, String status){
        endorsementImpl.updateStatusById(id, status);
    }
    @PutMapping("/updateToAcceptedById/{id}")
    void updateStatusToAcceptedById(@PathVariable Long id){
        endorsementImpl.updateStatusToAcceptedById(id);
    }
    @PutMapping("/updateToRefusedById/{id}")
    void updateStatusToRefusedById(@PathVariable Long id){
        endorsementImpl.updateStatusToRefusedById(id);
    }
    @PutMapping("/updateToSentById/{id}")
    void updateStatusToSentById(@PathVariable Long id){

        endorsementImpl.updateStatusToSentById(id);
    }
    @PutMapping("/updateToExpiredById/{id}")
    void updateStatusToExpiredById(@PathVariable Long id){

        endorsementImpl.updateStatusToExpiredById(id);
    }
    //statistiqques
    @GetMapping("/countAllPending")
    public int countStillPendingStatus(){

        return endorsementImpl.countStillPendingStatus();
    }
    @GetMapping("/countAllRefused")
    public int countRefusedStatus(){

        return endorsementImpl.countRefusedStatus();
    }
    @GetMapping("/countAllAccepted")
    public int countAcceptedStatus(){

        return endorsementImpl.countAcceptedStatus();
    }
    @GetMapping("/countAllSent")
    public int counttSentStatus(){

        return endorsementImpl.countSentStatus();
    }

    @GetMapping("/countAllExpired")
    public int countExpiredStatus(){

        return endorsementImpl.countExpiredStatus();
    }
}

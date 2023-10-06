package com.csidigital.management.controller;

import com.csidigital.dao.entity.*;
import com.csidigital.management.service.impl.ContractImpl;
import com.csidigital.shared.dto.request.ContractRequest;
import com.csidigital.shared.dto.response.ContractResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Transactional
@RequestMapping("/rh/contract")
public class ContractController {

    @Autowired
    private ContractImpl contract;

    @GetMapping("/getAll")
    public List<ContractResponse> getAllContracts(){
        return contract.getAllContracts();
    }

    @GetMapping("/getContract/{id}")
    public ContractResponse getContractById(@PathVariable Long id){
        return contract.getContractById(id);
    }

    @PostMapping("/addContract")
    public ContractResponse createContract(@Valid @RequestBody ContractRequest contractRequest){
        return contract.createContract(contractRequest);
    }

    @PutMapping("/updateContract/{id}")
    public ContractResponse updateContract(@Valid @RequestBody ContractRequest contractRequest,
                                           @PathVariable Long id){
        return contract.updateContract(contractRequest, id);
    }

    @DeleteMapping("/deleteContract/{id}")
    public void deleteContract(@PathVariable Long id){
        contract.deleteContract(id);
    }
    @PutMapping("/updateStatusById")
    void updateStatusById(Long id, String contractStatus){
        contract.updateStatusById(id, contractStatus);
    }
    @PutMapping("/updateToAcceptedById/{id}")
    void updateStatusToAcceptedById(@PathVariable Long id){
        contract.updateStatusToAcceptedById(id);
    }
    @PutMapping("/updateToRefusedById/{id}")
    void updateStatusToRefusedById(@PathVariable Long id){
        contract.updateStatusToRefusedById(id);
    }
    @GetMapping("/{id}/getContractFee")
    public  List<ExceptionalFee> getContractFee(@PathVariable Long id)
    {
        return contract.getContractFee(id);
    }
    @GetMapping("/{id}/getContractBenefits")
    public List<BenefitRC> getContractBenefits(@PathVariable Long id)
    {
        return  contract.getContractBenefits(id);
    }
    @GetMapping("/{id}/getContractEnd")
    public List<Endorsement> getContractEnd(@PathVariable Long id)
    {
        return  contract.getContractEnd(id);}
    @PutMapping("/updateToSentById/{id}")
    void updateStatusToSentById(@PathVariable Long id){

        contract.updateStatusToSentById(id);
    }
    @PutMapping("/updateToExpiredById/{id}")
    void updateStatusToExpiredById(@PathVariable Long id){

        contract.updateStatusToExpiredById(id);
    }
    @GetMapping("/getArticleContractById/{id}")
    public List<ArticleUpdated> getArticleContractById(@PathVariable Long id){
        return  contract.getArticleContractById(id);
    }
    @GetMapping("/countAllPending")
    public int countContractsByStillPendingStatus(){

        return contract.countContractsByStillPendingStatus();
    }
    @GetMapping("/countAllRefused")
    public int countContractsByRefusedStatus(){

        return contract.countContractsByRefusedStatus();
    }
    @GetMapping("/countAllAccepted")
    public int countContractsByAcceptedStatus(){

        return contract.countContractsByAcceptedStatus();
    }
    @GetMapping("/countAllSent")
    public int countContractsBySentStatus(){

        return contract.countContractsBySentStatus();
    }

    @GetMapping("/countAllExpired")
    public int countContractsByExpiredStatus(){

        return contract.countContractsByExpiredStatus();
    }


    @GetMapping("/getAllAccepted")
    public List<Contract> getAllAccepted(){
        return contract.getAllAccepted();
    }
}

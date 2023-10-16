package com.csidigital.management.controller;

import com.csidigital.dao.entity.ArticleClient;
import com.csidigital.management.service.impl.ContractClientImpl;
import com.csidigital.shared.dto.request.ContractClientRequest;
import com.csidigital.shared.dto.response.ContractClientResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/crm/contractClient")
public class ContractClientController {
    @Autowired
    private ContractClientImpl contractClient;


    @GetMapping("/getAll")
    public List<ContractClientResponse> getAll() {

        return contractClient.getAllContractClient();
    }

    @GetMapping("/get/{id}")
    public ContractClientResponse getById(@PathVariable Long id){
        return contractClient.getContractClientById(id);
    }

    @PostMapping("/add")
    public ContractClientResponse createArticle(@Valid @RequestBody ContractClientRequest contractRequest){
        return contractClient.createContractClient(contractRequest);
    }

    @PutMapping("/update/{id}")
    public ContractClientResponse updateArticle(@Valid @RequestBody ContractClientRequest contractRequest,
                                                @PathVariable Long id){
        return contractClient.UpdateContractClient(contractRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContract(@PathVariable Long id){

        contractClient.deleteContractClient(id);
    }
    @PutMapping("/updateStatusById")
    void updateStatusById(Long id, String contractStatus){
        contractClient.updateStatusById(id, contractStatus);
    }
    @PutMapping("/updateToAcceptedById/{id}")
    void updateStatusToAcceptedById(@PathVariable Long id){
        contractClient.updateStatusToAcceptedById(id);
    }
    @PutMapping("/updateToRefusedById/{id}")
    void updateStatusToRefusedById(@PathVariable Long id){
        contractClient.updateStatusToRefusedById(id);
    }
    @PutMapping("/updateToSentById/{id}")
    void updateStatusToSentById(@PathVariable Long id){

        contractClient.updateStatusToSentById(id);
    }
    @GetMapping("/getArticleContractById/{id}")
    public List<ArticleClient> getArticleContractById(@PathVariable Long id){
        return  contractClient.getArticleContractById(id);
    }
}

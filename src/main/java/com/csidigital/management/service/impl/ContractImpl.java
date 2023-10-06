package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.ArticleUpdatedRepository;
import com.csidigital.dao.repository.ContractRepository;
import com.csidigital.dao.repository.EmployeeRepository;
import com.csidigital.management.service.ContractService;
import com.csidigital.shared.dto.request.ContractRequest;
import com.csidigital.shared.dto.response.ContractResponse;
import com.csidigital.shared.enumeration.Status;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@AllArgsConstructor
public class ContractImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ArticleUpdatedRepository articleUpdatedRepository;
    @Autowired

    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;



    @Override
    @Transactional
    public ContractResponse createContract(ContractRequest request) {
        System.out.println(request.getEndDate());
        System.out.println(request.getValidityDate());
        System.out.println(request.getStartDate());
        Employee resource = null;
        if (request.getEmployeeNum() != null) {
            resource = employeeRepository.findById(request.getEmployeeNum())
                    .orElseThrow();
        }

        Contract contract = modelMapper.map(request, Contract.class);
        contract.setEmployee(resource);
        contract.setContractStatus(Status.STILL_PENDING);

        // Définition de la contractDate à la date actuelle
        LocalDate currentDate = LocalDate.now();
        contract.setContractDate(currentDate);
        Contract contractSaved = contractRepository.save(contract);
        // Formatage de la date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = contractSaved.getContractDate().format(dateFormatter);

        // Génération de la référence avec l'ID du contrat enregistré et un suffixe de trois chiffres qui s'incrémente
        String lastName = contractSaved.getEmployee().getLastName();
        String firstName = contractSaved.getEmployee().getFirstName();
        List<Contract> contractsList = contractSaved.getEmployee().getContractsList();
        int contractCount = contractsList.size();
        String suffixe;
        if (contractCount == 0) { // Vérifiez directement la taille de la liste des contrats
            suffixe = "001";
        } else {
            suffixe = String.format("%03d", contractCount);
        }
        String reference = lastName + "_" + firstName + "_" + suffixe;
        contractSaved.setReference(reference);





        for (int i = 0; i < request.getArticles().size(); i++) {
            request.getArticles().get(i).setContract(contractSaved);
            request.getArticles().get(i).setId(null);
            articleUpdatedRepository.save(request.getArticles().get(i));
        }

        return modelMapper.map(contractSaved, ContractResponse.class);
    }
    @Override
    public List<ContractResponse> getAllContracts() {
        List<Contract> contracts = contractRepository.findAll();
        List<ContractResponse> contractResponseList = new ArrayList<>();

        for (Contract contract : contracts) {
            ContractResponse response = modelMapper.map(contract ,ContractResponse.class);
            contractResponseList.add(response);
        }

        return contractResponseList;
    }

    @Override
    public ContractResponse getContractById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract with id " + id + " not found"));
        ContractResponse contractResponse = modelMapper.map(contract, ContractResponse.class);
        return contractResponse;
    }

    @Override
    public ContractResponse updateContract(ContractRequest request, Long id) {
        Contract existingContract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract with id: " + id + " not found"));

        modelMapper.map(request, existingContract);

        // Mettre à jour le statut du contrat s'il est null
        if (existingContract.getContractStatus() == null) {
            existingContract.setContractStatus(Status.STILL_PENDING);
        }

        // Mettre à jour la référence du contrat si nécessaire
        if (request.getReference() != null) {
            existingContract.setReference(request.getReference());
        }
        List<ArticleUpdated> articleUpdatedList = request.getArticles();
        for(ArticleUpdated article : articleUpdatedList ){
            article.setContract(existingContract);

            articleUpdatedRepository.save(article);
        }

        existingContract.setArticles(articleUpdatedList);
        Contract savedContract = contractRepository.save(existingContract);

        return modelMapper.map(savedContract, ContractResponse.class);
    }


    @Override
    public void deleteContract(Long id) {
        /*contractRepository.deleteById(id);*/
        Contract contract = contractRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Contract not found"));

        // Set the contract field to null for all associated articles
        for (ArticleUpdated article : contract.getArticles()) {
            article.setContract(null);
        }

        // Delete the contract
        contractRepository.delete(contract);
    }

    @Override
    public void updateStatusById(Long id, String contractStatus) {
        contractRepository.updateStatusById(id, contractStatus);
    }

    @Override
    public void updateStatusToAcceptedById(Long id) {
        contractRepository.updateStatusToAcceptedById(id);

    }

    @Override
    public void updateStatusToRefusedById(Long id) {
        contractRepository.updateStatusToRefusedById(id);

    }
//test
    @Override
    public void updateStatusToExpiredById(Long id) {
        contractRepository.updateStatusToExpiredById(id);
    }

    @Override
    public List<BenefitRC> getContractBenefits(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract with id " + id + " not found"));
        List<BenefitRC> benefitRC = contract.getBenefitRCSList();

        return benefitRC;
    }

    @Override
    public List<ExceptionalFee> getContractFee(Long id) {

        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract with id " + id + " not found"));
        List<ExceptionalFee> exceptionalFee = contract.getExceptionalFeeList();

        return exceptionalFee;
    }
    public List<Endorsement> getContractEnd(Long id) {

        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract with id " + id + " not found"));
        List<Endorsement> endorsements = contract.getEndorsementList();

        return endorsements;
    }

    @Override
    public void updateStatusToSentById(Long id) {
        contractRepository.updateStatusToSentById(id);
    }

    @Override
    public int countContractsByStillPendingStatus() {
        return contractRepository.countContractsByStillPendingStatus();
    }

    @Override
    public int countContractsByRefusedStatus() {
        return contractRepository.countContractsByRefusedStatus();
    }

    @Override
    public int countContractsByAcceptedStatus() {
        return contractRepository.countContractsByAcceptedStatus();
    }

    @Override
    public int countContractsBySentStatus() {
        return contractRepository.countContractsBySentStatus();
    }

    @Override
    public int countContractsByExpiredStatus() {
        return  contractRepository.countContractsByExpiredStatus();
    }

    @Override
    public List<Contract> getAllAccepted() {
        return contractRepository.getAllAccepted();
    }


    public List<ArticleUpdated> getArticleContractById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract with id " + id + " not found"));

        return contract.getArticles() ;
    }


}

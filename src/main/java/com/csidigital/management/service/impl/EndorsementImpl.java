package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.Contract;
import com.csidigital.dao.entity.Endorsement;
import com.csidigital.dao.repository.ContractRepository;
import com.csidigital.dao.repository.EndorsementRepository;
import com.csidigital.management.service.EndorsementService;
import com.csidigital.shared.dto.request.EndorsementRequest;
import com.csidigital.shared.dto.response.EndorsementResponse;
import com.csidigital.shared.enumeration.Status;
import com.csidigital.shared.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class EndorsementImpl implements EndorsementService {

    @Autowired
    private ModelMapper modelMapper ;
    @Autowired
    private EndorsementRepository endorsementRepository;
    @Autowired
    private ContractRepository contractRepository;

    @Override
    public EndorsementResponse createEndorsement(EndorsementRequest request) {
        Endorsement endorsement = modelMapper.map(request, Endorsement.class);
        Contract contract = contractRepository.findById(request.getContractNum()).orElseThrow();

        //Définition de la contractDate à la date actuelle
        LocalDate currentDate = LocalDate.now();
        endorsement.setEndorsementDate(currentDate);

         endorsement.setContract(contract);
        Endorsement EndorsementSaved = endorsementRepository.save(endorsement);
        // Génération de la référence
        String reference = EndorsementSaved.getContract().getReference();
        String contractId = String.format("%03d", EndorsementSaved.getContract().getId());
        int endorsementCount = EndorsementSaved.getContract().getEndorsementList().size() ;
        String endorsementSuffix;

// Utiliser "001" pour le premier endossement
        if (endorsementCount == 1) {
            endorsementSuffix = "001";
        } else {
            // Utiliser un format de trois chiffres pour les endossements suivants
            endorsementSuffix = String.format("%03d", endorsementCount);
        }

        String finalReference = reference + "_" + endorsementSuffix;
        EndorsementSaved.setReference(finalReference);


        endorsement.setStatus(Status.STILL_PENDING);
        return modelMapper.map( EndorsementSaved , EndorsementResponse.class);
    }
    /*@Override
    public EndorsementResponse createEndorsement(EndorsementRequest request) {
        Endorsement endorsement = modelMapper.map(request, Endorsement.class);
        Endorsement EndorsementSaved = endorsementRepository.save(endorsement);
        return modelMapper.map( EndorsementSaved , EndorsementResponse.class);
    }*/

    @Override
    public List<EndorsementResponse> getAllEndorsements() {
        List<Endorsement> endorsements = endorsementRepository.findAll();
        List<EndorsementResponse> endorsementList = new ArrayList<>();

        for (Endorsement endorsement : endorsements) {
            EndorsementResponse response = modelMapper.map(endorsement, EndorsementResponse.class);
            endorsementList.add(response);
        }

        return endorsementList;
    }

    @Override
    public EndorsementResponse getEndorsementById(Long id) {
        Endorsement endorsement = endorsementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Endorsement with id " +id+ " not found"));
        EndorsementResponse endorsementResponse = modelMapper.map(endorsement, EndorsementResponse.class);
        return endorsementResponse;
    }

    @Override
    public EndorsementResponse updateEndorsement(EndorsementRequest request, Long id) {
        Endorsement existingEndorsement = endorsementRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Article with id: " + id + " not found"));
        modelMapper.map(request, existingEndorsement);
        // Mettre à jour le statut du contrat s'il est null
        if (existingEndorsement.getStatus() == null) {
            existingEndorsement.setStatus(Status.STILL_PENDING);
        }
        Endorsement savedEndorsement = endorsementRepository.save(existingEndorsement);
        return modelMapper.map(savedEndorsement, EndorsementResponse.class);
    }

    @Override
    public void deleteEndorsement(Long id) {
        endorsementRepository.deleteById(id);

    }

    @Override
    public void updateStatusById(Long id, String status) {
        endorsementRepository.updateStatusById(id, status);
    }

    @Override
    public void updateStatusToAcceptedById(Long id) {
    endorsementRepository.updateStatusToAcceptedById(id);
    }

    @Override
    public void updateStatusToRefusedById(Long id) {
     endorsementRepository.updateStatusToRefusedById(id);
    }

    @Override
    public void updateStatusToExpiredById(Long id) {
    endorsementRepository.updateStatusToExpiredById(id);
    }

    @Override
    public void updateStatusToSentById(Long id) {
     endorsementRepository.updateStatusToSentById(id);
    }

    @Override
    public int countStillPendingStatus() {
        return endorsementRepository.countStillPendingStatus();
    }

    @Override
    public int countRefusedStatus() {
        return endorsementRepository.countRefusedStatus();
    }

    @Override
    public int countAcceptedStatus() {
        return endorsementRepository.countAcceptedStatus();
    }

    @Override
    public int countSentStatus() {
        return endorsementRepository.countSentStatus();
    }

    @Override
    public int countExpiredStatus() {
        return endorsementRepository.countExpiredStatus();
    }
}

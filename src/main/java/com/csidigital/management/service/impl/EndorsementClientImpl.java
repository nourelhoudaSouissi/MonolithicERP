package com.csidigital.management.service.impl;


import com.csidigital.dao.entity.ContractClient;
import com.csidigital.dao.entity.EndorsementClient;
import com.csidigital.dao.entity.EndorsementRefSequence;
import com.csidigital.dao.repository.ContractClientRepository;
import com.csidigital.dao.repository.EndorsementClientRepository;
import com.csidigital.dao.repository.EndorsementSequenceRepository;
import com.csidigital.management.service.EndorsementClientService;
import com.csidigital.shared.dto.request.EndorsementClientRequest;
import com.csidigital.shared.dto.response.EndorsementClientResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EndorsementClientImpl implements EndorsementClientService {

    @Autowired
    private ModelMapper modelMapper ;
    @Autowired
    private EndorsementClientRepository endorsementClientRepository;
    @Autowired
    private ContractClientRepository contractRepository;
    @Autowired
    private EndorsementSequenceRepository sequenceRepository;
    private String endorsementReference;

    @Override
    public EndorsementClientResponse createEndorsement(EndorsementClientRequest request) {
        ContractClient contract = contractRepository.findById(request.getContractNum())
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found"));

        EndorsementRefSequence sequence = new EndorsementRefSequence();
        sequenceRepository.save(sequence);

        EndorsementClient endorsementClient = modelMapper.map(request, EndorsementClient.class);
        endorsementReference = String.format("AV_%07d", sequence.getId());
     //   Contract contract = endorsementClientRepository.findById(request.getContractNum()).orElseThrow();
        // Définition de la contractDate à la date actuelle
        LocalDate currentDate = LocalDate.now();
        endorsementClient.setEndorsementDate(currentDate);
        endorsementClient.setContract(contract);
        endorsementClient.setReference(endorsementReference);
        /*sequence.incrementNextValue();
        sequenceRepository.save(sequence);*/

       // endorsement.setContract(contract);
        EndorsementClient EndorsementSaved = endorsementClientRepository.save(endorsementClient);
        // Génération de la référence
      /*  String reference = EndorsementSaved.getContract().getReference();
        String contractId = String.format("%03d", EndorsementSaved.getContract().getId());
        int endorsementCount = EndorsementSaved.getContract().getEndorsementList().size() + 1;
        String endorsementSuffix = String.format("%03d", endorsementCount);


        if (endorsementCount == 1) {
            endorsementSuffix = "001";
        }
        String finalReference = reference + "_" + endorsementSuffix;

        EndorsementSaved.setReference(finalReference);*/


        return modelMapper.map( EndorsementSaved , EndorsementClientResponse.class);
    }

    @Override
    public List<EndorsementClientResponse> getAllEndorsements() {
        List<EndorsementClient> endorsements = endorsementClientRepository.findAll();
        List<EndorsementClientResponse> endorsementList = new ArrayList<>();

        for (EndorsementClient endorsement : endorsements) {
            EndorsementClientResponse response = modelMapper.map(endorsement, EndorsementClientResponse.class);
            endorsementList.add(response);
        }

        return endorsementList;
    }

    @Override
    public EndorsementClientResponse getEndorsementById(Long id) {
        EndorsementClient endorsement = endorsementClientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Endorsement with id " +id+ " not found"));
        EndorsementClientResponse endorsementResponse = modelMapper.map(endorsement, EndorsementClientResponse.class);
        return endorsementResponse;
    }

    @Override
    public EndorsementClientResponse updateEndorsement(EndorsementClientRequest request, Long id) {
        EndorsementClient existingEndorsement = endorsementClientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Article with id: " + id + " not found"));
        modelMapper.map(request, existingEndorsement);
        EndorsementClient savedEndorsement = endorsementClientRepository.save(existingEndorsement);
        return modelMapper.map(savedEndorsement, EndorsementClientResponse.class);
    }

    @Override
    public void deleteEndorsement(Long id) {
        endorsementClientRepository.deleteById(id);
    }
}

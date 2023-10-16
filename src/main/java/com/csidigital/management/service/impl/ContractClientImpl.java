package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.ArticleClient;
import com.csidigital.dao.entity.ContractClient;
import com.csidigital.dao.entity.ContractRefSequence;
import com.csidigital.dao.entity.Partner;
import com.csidigital.dao.repository.ArticleClientRepository;
import com.csidigital.dao.repository.ContractClientRepository;
import com.csidigital.dao.repository.ContractSequenceRepository;
import com.csidigital.dao.repository.PartnerRepository;
import com.csidigital.management.service.ContractClientService;
import com.csidigital.shared.dto.request.ContractClientRequest;
import com.csidigital.shared.dto.response.ContractClientResponse;
import com.csidigital.shared.enumeration.Status;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ContractClientImpl implements ContractClientService {

    @Autowired
    private ContractClientRepository contractClientRepository;
    @Autowired
    private ArticleClientRepository articleClientRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PartnerRepository partnerRepository;
    @Autowired
    private ContractSequenceRepository sequenceRepository;
    private String contractReference;

    @Override
    public ContractClientResponse createContractClient(ContractClientRequest request) {
        System.out.println(request.getPartnerNum());
        Partner partner = partnerRepository.findById(request.getPartnerNum())
                .orElseThrow(() -> new ResourceNotFoundException("Partner not found"));

        ContractRefSequence sequence = new ContractRefSequence();
        sequenceRepository.save(sequence);

        ContractClient contractClient = modelMapper.map(request,  ContractClient.class);
        contractReference = String.format("CT_%07d", sequence.getId());
        contractClient.setPartner(partner);
        contractClient.setReference(contractReference);
        System.out.println(contractClient.getPartner());
        /*sequence.incrementNextValue();
        sequenceRepository.save(sequence);*/
        ContractClient contractClientSaved = contractClientRepository.save(contractClient);

        List<ArticleClient> articleClients = request.getArticleClients();
        if (articleClients != null) {
            for (ArticleClient articleClient : articleClients) {
                articleClient.setContractClient(contractClientSaved);
                //articleClient.setId(null);
                articleClientRepository.save(articleClient);
            }
        }
       contractClient.setContractStatus(Status.STILL_PENDING);
        return modelMapper.map(contractClientSaved, ContractClientResponse.class);
    }

    /*
    public ContractClientResponse createContractClient(ContractClientRequest request) {
        ContractClient contractClient = modelMapper.map(request,  ContractClient.class);
        ContractClient contractClientSaved = contractClientRepository.save(contractClient);
        for (int i = 0; i < request.getArticleClients().size(); i++) {
            request.getArticleClients().get(i).setContractClient(contractClientSaved);
            request.getArticleClients().get(i).setId(null);
            articleClientRepository.save(request.getArticleClients().get(i));
        }
        return modelMapper.map(contractClientSaved, ContractClientResponse.class);
    }*/

    @Override
    public List<ContractClientResponse> getAllContractClient() {
        List<ContractClient> contractClient = contractClientRepository.findAll();
        List<ContractClientResponse>   contractClientList = new ArrayList<>();

        for (ContractClient contractClients :contractClient ) {
            ContractClientResponse response = modelMapper.map(contractClients, ContractClientResponse.class);
            contractClientList.add(response);
        }

        return contractClientList;
    }

    @Override
    public ContractClientResponse getContractClientById(Long id) {
        ContractClient contractClient =contractClientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ArticleUpdated with id " +id+ " not found"));
        ContractClientResponse contractClientResponse = modelMapper.map(contractClient, ContractClientResponse.class);
        return contractClientResponse;
    }

    @Override
    public ContractClientResponse UpdateContractClient(ContractClientRequest request, Long id) {
        ContractClient existingContractClient = contractClientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ArticleUpdated with id: " + id + " not found"));
        modelMapper.map(request, existingContractClient);





            // Mettre à jour le statut du contrat s'il est null
            if (existingContractClient.getContractStatus() == null) {
                existingContractClient.setContractStatus(Status.STILL_PENDING);
            }


            List<ArticleClient> articleClientList = request.getArticleClients();
            for(ArticleClient article : articleClientList){
                article.setContractClient(existingContractClient);

                articleClientRepository.save(article);
            }

        existingContractClient.setArticleClients(articleClientList);
            ContractClient savedContract = contractClientRepository.save(existingContractClient);

            return modelMapper.map(savedContract, ContractClientResponse.class);
        }


    @Override
    public void deleteContractClient(Long id) {

            /*contractRepository.deleteById(id);*/
            ContractClient contractClient = contractClientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Contract not found"));

            // Set the contract field to null for all associated articles
            for (ArticleClient article : contractClient.getArticleClients()) {
                article.setContractClient(null);
            }

            // Delete the contract
        contractClientRepository.delete(contractClient);

    }

    @Override
    public void updateStatusById(Long id, String contractStatus) {
        contractClientRepository.updateStatusById(id, contractStatus);
    }

    @Override
    public void updateStatusToAcceptedById(Long id) {
        contractClientRepository.updateStatusToAcceptedById(id);
    }

    @Override
    public void updateStatusToRefusedById(Long id) {
        contractClientRepository.updateStatusToRefusedById(id);
    }

    @Override
    public void updateStatusToSentById(Long id) {
        contractClientRepository.updateStatusToSentById(id);
    }

    public List<ArticleClient> getArticleContractById(Long id) {
        ContractClient contractClient = contractClientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract with id " + id + " not found"));

        return contractClient.getArticleClients() ;
    }
}

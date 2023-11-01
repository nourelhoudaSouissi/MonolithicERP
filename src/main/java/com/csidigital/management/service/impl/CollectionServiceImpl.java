package com.csidigital.management.service.impl;


import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.CollectionService;
import com.csidigital.shared.dto.request.CollectionDtoRequest;
import com.csidigital.shared.dto.response.CollectionDtoResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private BankReconciliationRepository bankReconciliationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CollectionDtoResponse> getAllCollections() {
        List<Collection> collections = collectionRepository.findAll();
        List<CollectionDtoResponse> collectionList = new ArrayList<>();

        for (Collection collection : collections) {
            CollectionDtoResponse collectionResponse = modelMapper.map(collection, CollectionDtoResponse.class);
            collectionList.add(collectionResponse);
        }

        return collectionList;
    }

    @Override
    public CollectionDtoResponse getByIdCollection(Long id) {
        Collection collection = collectionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Collection with id " +id+ " not found"));
        CollectionDtoResponse collectionDtoResponse = modelMapper.map(collection, CollectionDtoResponse.class);
        return collectionDtoResponse;    }

    @Override
    public CollectionDtoResponse CreateCollection(CollectionDtoRequest collectionDtoRequest) {
        Collection collection = modelMapper.map(collectionDtoRequest, Collection.class);

        // Vérifier si la BankReconciliation existe en base de données
        BankReconciliation bankReconciliation = collection.getBankReconciliation();
        if (bankReconciliation.getId() == null) {
            // Si elle n'existe pas, enregistrer d'abord la BankReconciliation
            bankReconciliation = bankReconciliationRepository.save(bankReconciliation);
            collection.setBankReconciliation(bankReconciliation);
        }

        Collection collectionSaved = collectionRepository.save(collection);
        return modelMapper.map(collectionSaved, CollectionDtoResponse.class);
    }


    @Override
    public CollectionDtoResponse updateCollection(@Valid CollectionDtoRequest request, Long id) {
        Collection existingCollection = collectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collection with id: " + id + " not found"));

        // Conserver la référence du billClient existant
        BillClient billClient = existingCollection.getBillClient();

        // Effectuer la mise à jour des attributs de la collection
        existingCollection.setCategory(request.getCategory());
        existingCollection.setDate(request.getDate());
        existingCollection.setDescription(request.getDescription());
        existingCollection.setTreasuryType(request.getTreasuryType());

        // Rétablir la référence du billClient existant
        existingCollection.setBillClient(billClient);

        Collection savedCollection = collectionRepository.save(existingCollection);
        return modelMapper.map(savedCollection, CollectionDtoResponse.class);
    }




    @Override
    public void deleteCollectionById(Long id) {
        Collection collection = collectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collection with id " + id + " not found"));

        collectionRepository.deleteById(id);
    }

}

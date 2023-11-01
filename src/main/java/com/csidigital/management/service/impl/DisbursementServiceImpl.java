package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.DisbursementService;
import com.csidigital.shared.dto.request.DisbursementDtoRequest;
import com.csidigital.shared.dto.response.DisbursementDtoResponse;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service

public class DisbursementServiceImpl implements DisbursementService {



        @Autowired
        private ModelMapper modelMapper;
        @Autowired
        private DisbursementRepository disbursementRepository;
        @Autowired
        private BankReconciliationRepository bankReconciliationRepository;
        @Override
        public List<DisbursementDtoResponse> getAllDisbursement() {
                List<Disbursement> disbursements = disbursementRepository.findAll();
                List<DisbursementDtoResponse> disbursementList = new ArrayList<>();

                for (Disbursement disbursement : disbursements) {
                        DisbursementDtoResponse disbursementDtoResponse = modelMapper.map(disbursement, DisbursementDtoResponse.class);
                        disbursementList.add(disbursementDtoResponse);
                }

                return disbursementList;
        }

        @Override
        public DisbursementDtoResponse getByIdDisbursement(Long id) {
                Disbursement disbursement= disbursementRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("Disbursement with id " +id+ " not found"));
                DisbursementDtoResponse disbursementDtoResponse = modelMapper.map(disbursement, DisbursementDtoResponse.class);
                return disbursementDtoResponse;
        }

        @Override
        public DisbursementDtoResponse CreateDisbursement(DisbursementDtoRequest disbursementDtoRequest) {
                Disbursement disbursement = modelMapper.map(disbursementDtoRequest, Disbursement.class);

                // Vérifier si la BankReconciliation existe en base de données
                BankReconciliation bankReconciliation = disbursement.getBankReconciliation();
                if (bankReconciliation.getId() == null) {
                        // Si elle n'existe pas, enregistrer d'abord la BankReconciliation
                        bankReconciliation = bankReconciliationRepository.save(bankReconciliation);
                        disbursement.setBankReconciliation(bankReconciliation);
                }

                Disbursement disbursementSaved = disbursementRepository.save(disbursement);
                return modelMapper.map(disbursementSaved, DisbursementDtoResponse.class);
        }

        @Override
        public DisbursementDtoResponse updateDisbursement(@Valid DisbursementDtoRequest request, Long id) {
                Disbursement existingDisbursement = disbursementRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Disbursement with id: " + id + " not found"));

                // Conserver la référence du billClient existant
                Bill bill = existingDisbursement.getBill();

                // Effectuer la mise à jour des attributs de la disbursement
                existingDisbursement.setCategory(request.getCategory());
                existingDisbursement.setDate(request.getDate());
                existingDisbursement.setDescription(request.getDescription());
                existingDisbursement.setTreasuryType(request.getTreasuryType());

                // Rétablir la référence du billClient existant
                existingDisbursement.setBill(bill);

                Disbursement savedDisbursement = disbursementRepository.save(existingDisbursement);
                return modelMapper.map(savedDisbursement, DisbursementDtoResponse.class);
        }

        @Override
        public void deleteDisbursementById(Long id) {
                {disbursementRepository.deleteById(id);}

        }
}

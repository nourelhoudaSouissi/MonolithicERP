package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.QuotationService;
import com.csidigital.shared.dto.request.ProfileUpdatedRequest;
import com.csidigital.shared.dto.request.QuotationRequest;
import com.csidigital.shared.dto.response.ProfileResponse;
import com.csidigital.shared.dto.response.QuotationResponse;
import com.csidigital.shared.dto.response.RequirementResponse;
import com.csidigital.shared.enumeration.QuotationStatus;
import com.csidigital.shared.enumeration.RequirementStatus;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuotationServiceImpl implements QuotationService {
    @Autowired
    private QuotationRepository quotationRepository ;
    @Autowired
    private RequirementRepository requirementRepository ;
    @Autowired
    private PartnerRepository partnerRepository ;
    @Autowired
    private ProfileUpdatedRepository profileUpdatedRepository;
    @Autowired
    private ProfileServiceImpl profileService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private QuotationSequenceRepository sequenceRepository;
    private String quotationReference;
    @Override
    public QuotationResponse createQuotation(QuotationRequest request) {
        System.out.println(request.getRequirementNum());
        System.out.println("profiles");
        System.out.println(request.getProfiles());

        Requirement requirement = requirementRepository.findById(request.getRequirementNum())
                .orElseThrow(() -> new ResourceNotFoundException("Requirement not found"));

        QuotationReferenceSequence sequence = new QuotationReferenceSequence();
        sequenceRepository.save(sequence);

        List<ProfileUpdatedRequest> updatedProfiles = request.getProfiles();
        Quotation quotation = modelMapper.map(request, Quotation.class);
        System.out.println(quotation);
        quotationReference = String.format("QT_%07d", sequence.getId());
        quotation.setRef(quotationReference);
        quotation.setQuotationStatus(QuotationStatus.IN_PROGRESS);
        quotation.setQuotationDate(LocalDate.now());
        quotation.setRequirement(requirement);
        /*sequence.incrementNextValue();
        sequenceRepository.save(sequence);*/
        quotation.calculateQuotationRevenue();
        System.out.println("ORDER REVENUE" + quotation.getRevenueOrd());
        quotation.getRequirement().setRequirementStatus(RequirementStatus.IN_PROGRESS);
        quotation.setProfiles(new ArrayList<>());
        Quotation quotationSaved = quotationRepository.save(quotation);
        System.out.println("HEEEEEEEEEEEEEEEEEEEERE");
        System.out.println(quotationSaved);
        List<ProfileUpdatedRequest> profiles = request.getProfiles();
        if (!profiles.isEmpty()) {
            for (ProfileUpdatedRequest profile : profiles) {
                Profile pro = profile.getProfile();
                System.out.println(pro.getId());
                System.out.println("HEEEEEEEEEEEEEEEEEEEEYYYYYYYYYYYYYYYYY");
                System.out.println(profile);
                ProfileUpdated profile1 = modelMapper.map(profile, ProfileUpdated.class);
                // Save the Profile in the database if it has not been saved yet
                profile1.setExperience(pro.getExperience());
                profile1.setFunction(pro.getFunction());
                //Calcule Totale sans pourcentage de chaque ligne de profile dans devis
                profile1.setTotal(profile1.getCandidateDailyCost() * profile1.getPeriod() * profile1.getCandidateNumber());
                //Calcule Totale avec pourcentage de chaque ligne de profile dans devis

               /* double totalDiscount = profile1.getTotal() * (profile1.getProfileDiscount() / 100); //profileDiscount est en pourcentage
                profile1.setTotalDiscount(totalDiscount);*/

                double total = profile1.getTotal(); // Obtenez le montant total initial
                double discount = profile1.getTotal() * (profile1.getProfileDiscount() / 100); // Calcul du montant du rabais
                double totalAfterDiscount = total - discount; // Calcul du montant après remise
                profile1.setTotalDiscount(totalAfterDiscount); // Définir le montant total après la remise


                profile1.setQuotation(quotationSaved);
                //profile1.setCandidateDailyCost(pro.getCandidateDailyCost());
                System.out.println("HOOOOOOOOOOOOOOOOOOLAAAAAAAAAAAAAAA");
                System.out.println(profile1);
                ProfileUpdated profile2 = profileUpdatedRepository.save(profile1);
            }
        }
        return modelMapper.map(quotationSaved, QuotationResponse.class);
    }

    @Override
    public QuotationResponse updateQuotation(QuotationRequest request, Long id) {
        System.out.println(id);
        System.out.println(request);
        Quotation existingQuotation = quotationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Quotation with id: " + id + " not found"));
        System.out.println("1");
        //delete old profiles updated from existing quotation
        List<ProfileUpdated> oldPros = existingQuotation.getProfiles();
        System.out.println("2");
        for (ProfileUpdated profile : oldPros) {
            System.out.println("3");
            Long oldId = profile.getId();
            System.out.println("4");
            profileUpdatedRepository.deleteById(oldId);
            System.out.println("5");
        }
        oldPros.clear();
        System.out.println("6");
        modelMapper.map(request, existingQuotation);
        existingQuotation.setId(id);
        System.out.println("ORDER REVENUE" + existingQuotation.getRevenueOrd());
        //List<ProfileUpdated> listProfiles = new ArrayList<>();
        existingQuotation.setProfiles(oldPros);
        System.out.println("7");
        List<ProfileUpdatedRequest> profiles = request.getProfiles();
        System.out.println("8");
        if (!profiles.isEmpty()) {
            for (ProfileUpdatedRequest profile : profiles) {
                System.out.println("9");
                Long proId = profile.getProfileNum();
                System.out.println(proId);
                ProfileResponse profileRes = profileService.getProfileById(proId);
                System.out.println("10");
                Profile pro = modelMapper.map(profileRes, Profile.class);
                System.out.println("11");
                profile.setProfile(pro);
                System.out.println("12");
                profile.setQuotationNum(id);
                System.out.println("13");
                System.out.println(pro.getId());
                System.out.println("HEEEEEEEEEEEEEEEEEEEEYYYYYYYYYYYYYYYYY");
                System.out.println(profile);
                System.out.println("14");
                ProfileUpdated profile1 = modelMapper.map(profile, ProfileUpdated.class);
                System.out.println("15");
                // Save the Profile in the database if it has not been saved yet
                profile1.setExperience(pro.getExperience());
                System.out.println("16");
                profile1.setFunction(pro.getFunction());
                System.out.println("17");
                Double cost = pro.getCandidateDailyCost() * existingQuotation.getChangeRate();

                System.out.println("18");
                profile1.setCandidateDailyCost((double)cost.longValue());

                profile1.setTotal(profile1.getCandidateDailyCost() * profile1.getPeriod() * profile1.getCandidateNumber());
                System.out.println(profile1.getTotal());
                double totalDiscount = profile1.getTotal() * (profile1.getProfileDiscount() / 100); //profileDiscount est en pourcentage
                profile1.setTotalDiscount(totalDiscount);
                profile1.setQuotation(existingQuotation);
                System.out.println("HOOOOOOOOOOOOOOOOOOLAAAAAAAAAAAAAAA");
                profile1.setProfile(pro);
                //ProfileUpdated profile2 = profileUpdatedRepository.save(profile1);
                oldPros.add(profile1);
            }
        }
        existingQuotation.setProfiles(oldPros);
        existingQuotation.calculateQuotationRevenue();
        System.out.println("ORDER REVENUE" + existingQuotation.getRevenueOrd());
        Quotation savedQuotation = quotationRepository.save(existingQuotation);
        return modelMapper.map(savedQuotation, QuotationResponse.class);
    }

    @Override
    public List<QuotationResponse> getAllQuotations() {
        List<Quotation> quotations = quotationRepository.findAll();
        List<QuotationResponse> quotationList = new ArrayList<>();

        for (Quotation quotation : quotations) {
            QuotationResponse response = modelMapper.map(quotation, QuotationResponse.class);
            quotationList.add(response);
        }

        return quotationList;
    }

    @Override
    public QuotationResponse getQuotationById(Long id) {
        Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Quotation with id " +id+ " not found"));
        QuotationResponse quotationResponse = modelMapper.map(quotation, QuotationResponse.class);
        return quotationResponse;
    }



    @Override
    public void updateStatusToAccepted(Long id) {
        Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Quotation with id " +id+ " not found"));
        Partner partner = partnerRepository.findById(quotation.getPartnerNum())
                .orElseThrow(()-> new ResourceNotFoundException("Partner with id " +id+ " not found"));
        partnerRepository.updateStatusToClient(partner.getId());
        quotationRepository.updateStatusToAccepted(id);
    }

    @Override
    public void updateStatusToInProgress(Long id) {
        quotationRepository.updateStatusToInProgress(id);
    }

    @Override
    public void updateStatusToRefused(Long id) {
        quotationRepository.updateStatusToRefused(id);
    }

    @Override
    public void updateStatusToUnanswered(Long id) {
        quotationRepository.updateStatusToUnanswered(id);
    }

    @Override
    public RequirementResponse getReqByQotId(Long qotId) {
        Quotation quotation = quotationRepository.findById(qotId)
                .orElseThrow(()-> new ResourceNotFoundException("Quotation with id " +qotId+ " not found"));
        return modelMapper.map(quotation.getRequirement(), RequirementResponse.class);
    }

    /*@Override
    public Double calculateQuotationRevenue(Quotation quotation) {
        Double quotationRevenue = 0.0;
        List<ProfileUpdated> profiles = quotation.getProfiles();
        for (ProfileUpdated profile : profiles){
            quotationRevenue += profile.getCandidateDailyCost() * profile.getCandidateNumber() * profile.getPeriod();
        }
        quotationRevenue += quotationRevenue * quotation.getTva();
        return quotationRevenue;
    }*/

    @Override
    public void deleteQuotation(Long id) {
        quotationRepository.deleteById(id);
    }
}

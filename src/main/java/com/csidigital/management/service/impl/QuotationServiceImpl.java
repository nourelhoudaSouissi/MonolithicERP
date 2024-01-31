package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.*;
import com.csidigital.management.service.QuotationService;
import com.csidigital.management.service.ServiceService;
import com.csidigital.shared.dto.request.ProfileUpdatedRequest;
import com.csidigital.shared.dto.request.QuotationRequest;
import com.csidigital.shared.dto.request.ServiceUpdatedRequest;
import com.csidigital.shared.dto.response.ProfileResponse;
import com.csidigital.shared.dto.response.QuotationResponse;
import com.csidigital.shared.dto.response.RequirementResponse;
import com.csidigital.shared.dto.response.ServiceUpdatedResponse;
import com.csidigital.shared.enumeration.QuotationStatus;
import com.csidigital.shared.enumeration.RequirementStatus;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
    private ServiceUpdatedRepository serviceUpdatedRepository;
    @Autowired
    private ProfileServiceImpl profileService;
    @Autowired
    private ServiceServiceImpl serviceService;
    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    public void configureModelMapper() {
       // modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }
    @Autowired
    private QuotationSequenceRepository sequenceRepository;
    private String quotationReference;
    @Override
    public QuotationResponse createQuotation(QuotationRequest request) {


        Requirement requirement = requirementRepository.findById(request.getRequirementNum())
                .orElseThrow(() -> new ResourceNotFoundException("Requirement not found"));

        QuotationReferenceSequence sequence = new QuotationReferenceSequence();
        sequenceRepository.save(sequence);

        List<ProfileUpdatedRequest> updatedProfiles = request.getProfiles();
        List<ServiceUpdatedRequest> updatedServices = request.getServices();


        Quotation quotation = modelMapper.map(request, Quotation.class);


        quotationReference = String.format("QT_%07d", sequence.getId());
        quotation.setRef(quotationReference);
        quotation.setQuotationStatus(QuotationStatus.IN_PROGRESS);
        quotation.setQuotationDate(LocalDate.now());
        quotation.setRequirement(requirement);

        quotation.calculateQuotationRevenue();
        quotation.getRequirement().setRequirementStatus(RequirementStatus.IN_PROGRESS);

        quotation.setProfiles(new ArrayList<>());
        quotation.setServices(new ArrayList<>());

        Quotation quotationSaved = quotationRepository.save(quotation);

        List<ProfileUpdatedRequest> profiles = request.getProfiles();
        List<ServiceUpdatedRequest> services = request.getServices();

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

                double total = profile1.getTotal(); // Obtenez le montant total initial
                double discount = profile1.getTotal() * (profile1.getProfileDiscount() / 100); // Calcul du montant du rabais
                double totalAfterDiscount = total - discount; // Calcul du montant après remise
                profile1.setTotalDiscount(totalAfterDiscount); // Définir le montant total après la remise



                //Calcule Totale avec pourcentage de tva de chaque ligne de profile dans devis
                double total2 = profile1.getTotal(); // Obtenez le montant total initial
                double tva = profile1.getTotal() * (profile1.getTvaPercentage() / 100); // Calcul du montant du rabais tva
                double totalAfterTva = total2 + tva; // Calcul du montant après remise tva
                profile1.setTotalTva(totalAfterTva); // Définir le montant total après la remise

                profile1.setQuotation(quotationSaved);

                ProfileUpdated profile2 = profileUpdatedRepository.save(profile1);
            }
        }
        if (services != null && !services.isEmpty()) {
            for (ServiceUpdatedRequest service : services) {
                com.csidigital.dao.entity.Service ser = service.getService();

                ServiceUpdated service1 = modelMapper.map(service, ServiceUpdated.class);
                // Save the Profile in the database if it has not been saved yet
                service1.setTitle(ser.getTitle());
                service1.setCode(ser.getCode());

                //Calcule Totale sans pourcentage de tva de chaque ligne de profile dans devis
                service1.setTotal(service1.getAmount() * service1.getPeriod() * service1.getServiceQuantity());

                double totalLine = service1.getTotal(); // Obtenez le montant total initial
                double discount = service1.getTotal() * (service1.getServiceDiscount() / 100); // Calcul du montant du rabais
                double totalAfterDiscount = totalLine - discount; // Calcul du montant après remise
                service1.setTotalDiscount(totalAfterDiscount); // Définir le montant total après la remise

                //Calcule Totale avec pourcentage de tva de chaque ligne de profile dans devis
                //double total = service1.getTotal(); // Obtenez le montant total initial
                double tva = service1.getTotal() * (service1.getTvaPercentage() / 100); // Calcul du montant du rabais tva
                double totalAfterTva = totalAfterDiscount + tva; // Calcul du montant après remise tva
                service1.setTotalTva(totalAfterTva); // Définir le montant total après la remise
                service1.setQuotation(quotationSaved);
                ServiceUpdated service2 = serviceUpdatedRepository.save(service1);
            }
        } else {
            // Handle the case when the services list is empty
            // For example, log a message or perform necessary actions
            System.out.println("The services list is empty.");
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
        Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Quotation with id " +id+ " not found"));
        quotation.setRefusedDate(LocalDate.now());
        quotationRepository.updateStatusToRefused(id);
    }

    @Override
    public void updateStatusToUnanswered(Long id) {
        Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Quotation with id " +id+ " not found"));
        quotation.setUnansweredDate(LocalDate.now());
        quotationRepository.updateStatusToUnanswered(id);
    }

    @Override
    public void updateStatusToSentToClient(Long id) {
        Quotation quotation = quotationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Quotation with id " +id+ " not found"));
        quotation.setSentDate(LocalDate.now());
        Long limitDuration = quotation.getLimitDuration();
        if (limitDuration != null && quotation.getSentDate() != null) {
            LocalDate validationDate = quotation.getSentDate().plusDays(limitDuration);
            quotation.setValidationDate(validationDate);
        } else {
            quotation.setValidationDate(null);
        }

        quotationRepository.updateStatusToSentToClient(id);
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

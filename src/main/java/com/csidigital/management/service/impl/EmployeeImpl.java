package com.csidigital.management.service.impl;

import com.csidigital.dao.entity.*;
import com.csidigital.dao.repository.EmployeeReferenceSequenceRepository;
import com.csidigital.dao.repository.EmployeeRepository;
import com.csidigital.dao.repository.OfferRepository;
import com.csidigital.management.service.EmployeeService;
import com.csidigital.shared.dto.request.EmployeeRequest;
import com.csidigital.shared.dto.response.*;
import com.csidigital.shared.enumeration.EmployeeStatus;
import com.csidigital.shared.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository ;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ModelMapper modelMapper ;

    @Autowired
    private EmployeeReferenceSequenceRepository employeeReferenceSequenceRepository;


    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employee employee = modelMapper.map(request, Employee.class);
        employee.setEmployeeStatus(EmployeeStatus.IN_PROCESS);
        Employee employeeSaved = employeeRepository.save(employee);
        return modelMapper.map(employeeSaved, EmployeeResponse.class);

    }




   @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }





    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id " + id + " not found"));
        EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
        List<Offer> offers = new ArrayList<>();
        // Calculate remainingPaidLeave using getSoldeConges method
        if (employeeResponse.getHireDate()!=null) {
            Double remainingPaidLeave = getSoldeConges(id);

            // Set remainingPaidLeave with correct precision
            BigDecimal remainingPaidLeaveBigDecimal = BigDecimal.valueOf(remainingPaidLeave).setScale(2, RoundingMode.HALF_UP);
            employeeResponse.setRemainingPaidLeave(remainingPaidLeaveBigDecimal.doubleValue());
        }
        List<OfferCandidate> offerCandidates = employee.getOfferCandidateList();
        List<AssOfferCandidateResponse> assOfferCandidateResponseList = new ArrayList<>();

        for (OfferCandidate offerCandidate : offerCandidates){
            AssOfferCandidateResponse response = modelMapper.map(offerCandidate, AssOfferCandidateResponse.class);
            assOfferCandidateResponseList.add(response);

        }
        for(AssOfferCandidateResponse offer : assOfferCandidateResponseList){
            offers.add(offerRepository.findById(offer.getOfferId()).orElseThrow());
        }
employeeResponse.setOffers(offers);
        return employeeResponse;
    }








    @Override
    public EmployeeResponse updateEmployee(EmployeeRequest request, Long id) {
        System.out.println(id);
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id: " + id + " not found"));
        modelMapper.map(request, existingEmployee);
        existingEmployee.setId(id);
        Employee savedEmployee = employeeRepository.save(existingEmployee);
        return modelMapper.map(savedEmployee, EmployeeResponse.class);
    }



    /*
    @Override
    public void deleteEmployee(Long id) {
    Employee employee = employeeRepository.findById(id).orElseThrow();
    List<Equipment> equipment = employee.getEquipmentList();
    for(Equipment eq : equipment) {
        eq.setEmployee(null);
        equipmentRepository.save(eq);
    }
        employeeRepository.deleteById(id);
    }
*/

    // La méthode Get technical file
    @Override
    public TechnicalFileResponse getEmployeeTechnicalFile(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id " +id+ " not found"));
        TechnicalFile technicalFile = employee.getTechnicalFile();
        TechnicalFileResponse technicalFileResponse = modelMapper.map(technicalFile, TechnicalFileResponse.class);
        return technicalFileResponse ;
    }


    // La méthode Get Education Employee
    @Override
    public List<EducationResponse> getEmployeeEducation(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id " +id+ " not found"));
        TechnicalFile technicalFile = employee.getTechnicalFile();
        List<Education> educations = technicalFile.getEducations()  ;
        List<EducationResponse> educationResponseList = new ArrayList<>();
        for (Education education : educations) {
            EducationResponse response = modelMapper.map(education, EducationResponse.class);
            educationResponseList.add(response);
        }
        return educationResponseList ;
    }

    // La méthode Get Expérience Employee
    @Override
    public List<ExperienceResponse> getEmployeeExperience(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id " +id+ " not found"));
        TechnicalFile technicalFile = employee.getTechnicalFile();

        List<Experience> experiences = technicalFile.getExperiences();
        List<ExperienceResponse> experienceResponseList = new ArrayList<>();

        for (Experience experience : experiences) {
            ExperienceResponse response = modelMapper.map(experience, ExperienceResponse.class);
            experienceResponseList.add(response);
        }
        return experienceResponseList ;
    }


    // La méthode Get Certification Employee
    @Override
    public List<CertificationResponse> getEmployeeCertification(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id " +id+ " not found"));
        TechnicalFile technicalFile = employee.getTechnicalFile();
        List<Certification> certifications = technicalFile.getCertifications();
        List<CertificationResponse> certificationResponseList = new ArrayList<>();

        for (Certification certification : certifications) {
            CertificationResponse response = modelMapper.map(certification, CertificationResponse.class);
            certificationResponseList.add(response);
        }
        return certificationResponseList ;
    }


    // La méthode Get language  Employee
    @Override
    public List<LanguageResponse> getEmployeeLanguage(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id " +id+ " not found"));
        TechnicalFile technicalFile = employee.getTechnicalFile();
        List<Language> languages = technicalFile.getLanguages();
        List<LanguageResponse> languageResponseList = new ArrayList<>();

        for (Language language : languages) {
            LanguageResponse response = modelMapper.map(language, LanguageResponse.class);
            languageResponseList.add(response);
        }
        return languageResponseList ;
    }


    // La méthode Get compétences Employee
    @Override
    public List<SkillsResponse> getEmployeeSkills(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id " +id+ " not found"));
        TechnicalFile technicalFile = employee.getTechnicalFile();
        List<Skills> skills = technicalFile.getSkills();
        List<SkillsResponse> skillsResponseList = new ArrayList<>();

        for (Skills skill : skills) {
            SkillsResponse response = modelMapper.map(skill, SkillsResponse.class);
            skillsResponseList.add(response);
        }
        return skillsResponseList ;
    }

    // La méthode Get Offre Employee
    @Override
    public List<AssOfferCandidateResponse> getOfferCandidates(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id " +id+ " not found"));
        List<OfferCandidate> offerCandidates = employee.getOfferCandidateList();
        List<AssOfferCandidateResponse> assOfferCandidateResponseList = new ArrayList<>();
        List<Offer> offers = new ArrayList<>();
        for (OfferCandidate offerCandidate : offerCandidates){
            AssOfferCandidateResponse response = modelMapper.map(offerCandidate, AssOfferCandidateResponse.class);
            assOfferCandidateResponseList.add(response);

        }
        for(AssOfferCandidateResponse offer : assOfferCandidateResponseList){
            offers.add(offerRepository.findById(offer.getOfferId()).orElseThrow());
        }

        return assOfferCandidateResponseList ;
    }

    // Les méthodes du changement de status
    @Override
    public void updateStatusToInProcessById(Long id) {

        employeeRepository.updateStatusToInProcessById(id);
    }

    @Override
    public void updateStatusToInProgressById(Long id) {

        employeeRepository.updateStatusToInProcessById(id);
    }


    @Override
    public void updateStatusToTopProfilesById(Long id) {
        employeeRepository.updateStatusToTopProfilesById(id);
    }

    @Override
    public void updateStatusToPreQualifiedById(Long id) {

        employeeRepository.updateStatusToPreQualifiedById(id);
    }

    @Override
    public void updateStatusToCreateContractById(Long id){
        employeeRepository.updateStatusToCreateContractById(id);
    }

    @Override
    public void updateStatusToConvertedToResourceById(Long id) {
       Employee employee = employeeRepository.findById(id).orElseThrow();
        EmployeeReferenceSequence sequence = employeeReferenceSequenceRepository.findById(1L)
                .orElse(null) ;
        if (sequence == null) {
            sequence = new EmployeeReferenceSequence();
            sequence = employeeReferenceSequenceRepository.save(sequence);
        }
        String employeeReference = String.format("M_%04d", sequence.getNextValue());
        sequence.incrementNextValue();
        employeeReferenceSequenceRepository.save(sequence);
        employee.setSerialNumber(employeeReference);
        employeeRepository.updateStatusToConvertedToResourceById(id);
    }

    @Override
    public void updateStatusToDoNotContactById(Long id) {

        employeeRepository.updateStatusToDoNotContactById(id);
    }

    @Override
    public void updateStatusToArchiveById(Long id) {

        employeeRepository.updateStatusToArchiveById(id);
    }

    @Override
    public List<Employee> getConvertedCandidates() {
       return employeeRepository.getConvertedCandidates();
    }

    @Override
    public List<Employee> getNotConvertedCandidates() {
        return  employeeRepository.getNotConvertedCandidates();
    }


    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);    }
    @Override
    public AdministrativeData getEmployeeAdministartiveData(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id " +id+ " not found"));
        AdministrativeData administrativeData = employee.getAdministrativeData();
        //AdministrativeDataResponse administrativeDataResponse = modelMapper.map(administrativeData, AdministrativeDataResponse.class);
        return administrativeData ;
    }
    @Override
    public boolean hasAdministrativeData(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with ID: " + employeeId));

        return employee.getAdministrativeData() != null;
    }


    @Override
    public String employeeSerialNumberGenerator() {
        String lastCode = employeeRepository.resourceLastCode();
        if (lastCode == null) {
            return "P_00000001";
        }
        Integer codeNumber = Integer.parseInt(lastCode.substring(2));

        if (codeNumber < 100000000) {
            codeNumber= codeNumber+Integer.parseInt(String.format("%08d", 1));
        }
        String numbers= codeNumber.toString();
        if (numbers.length()<8){
            for(int i=0;i<7;i++){
                numbers='0'+ numbers;
            }

        }
        return "P_" + numbers;
    }


    @Override
    public List<Employee> findByEmployeeStatus() {
        return employeeRepository.findByEmployeeStatus(EmployeeStatus.CONVERTED_TO_RESOURCE);
    }

    @Override
    public List<Employee> getAllCandidates() {
        return employeeRepository.getAllCandidates();
    }



    // Méthode Get tous les BackOffices

    @Override
    public List<Employee> getAllResourcesBackOffice() {
        return employeeRepository.getAllResourcesBackOffice();
    }



    // Méthode Get tous les Internes

    @Override
    public List<Employee> getAllResourcesInterne() {
        return employeeRepository.getAllResourcesInterne();
    }


    // Méthode Get tous les Externes

    @Override
    public List<Employee> getAllResourcesExterne() {
        return employeeRepository.getAllResourcesExterne();
    }


    @Override
    public List<Employee> getAllInternes() {

        return employeeRepository.getAllInternes();
    }
    @Override
    public List<Employee> getAllExternes() {

        return employeeRepository.getAllExternes();
    }
    @Override
    public List<Contract> getContractsEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract with id " + id + " not found"));
        List<Contract> contract = employee.getContractsList();

        return contract;
    }

    @Override
    public List<Availability> getAvailabilityEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract with id " + id + " not found"));
        List<Availability> availabilityList = employee.getAvailabilityList();

        return availabilityList;
    }

    @Override
    public int countConvertedToRessource() {
        return employeeRepository.countConvertedToRessource();
    }

    @Override
    public int countArchived() {
        return employeeRepository.countArchived();
    }

    @Override
    public int countDONOTCONTACT() {
        return employeeRepository.countDONOTCONTACT();
    }

    @Override
    public int countINPROCESS() {
        return employeeRepository.countINPROCESS();
    }

    @Override
    public int countPREQUALIFIED() {
        return employeeRepository.countPREQUALIFIED();
    }

    @Override
    public int countTOPPROFILES() {
        return employeeRepository.countTOPPROFILES();
    }

    @Override
    public int countINPROGRESS() {
        return employeeRepository.countINPROGRESS();
    }


    //méthodes congés

    @Override
    public Double getSoldeConges(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));
        LocalDate hireDate = employee.getHireDate();
        LocalDate today = LocalDate.now();
        long days = 0;
        for (LocalDate date = hireDate; date.isBefore(today); date = date.plusDays(1)) {
            if (date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                days++;
            }
        }
        double nouveauSolde=0;
        double increment = days * 0.07;
        if(employee.getRemainingPaidLeave()!=null){
                     nouveauSolde = employee.getRemainingPaidLeave() + increment;
                   }
        return nouveauSolde;

    }
    @Override
    public Double getSpecialPaidLeaveRest(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));
        return Stream.of(employee.getCirconcisionLeaveRest(), employee.getDeathLeaveRest(),
                        employee.getMarriageLeaveRest(), employee.getMaternityLeaveRest())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public Double getSicknessLeaveRest(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + employeeId));
        return Stream.of(employee.getSickLeaveRest(), employee.getCompassioateLeaveRest())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public List<EvaluationResponse> getEmployeeEvaluations(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee with id " +id+ " not found"));
        List<Evaluation> evaluations = employee.getEvaluation();
        List<EvaluationResponse> evaluationResponseList = new ArrayList<>();
        for (Evaluation evaluation : evaluations) {
            EvaluationResponse response = modelMapper.map(evaluation, EvaluationResponse.class);
            evaluationResponseList.add(response);
        }
        return evaluationResponseList ;
    }



    //getAllSupérieurHiérarchique
    public List<Employee> getAllSuperior() {
        return employeeRepository.getAllSuperior();
    }


    public void updateSuperiorById(Long id, Long hierarchicalSuperiorNum) {
        employeeRepository.updateSuperiorById(id,hierarchicalSuperiorNum);
    }

    @Override
    public String findEmployeeNamesByEquipmentId(Long equipmentId) {
        return employeeRepository.findEmployeeNamesByEquipmentId(equipmentId);
    }


    @Override
    public Double updateNoteEvaluationFromGlobalAppreciation(){
        return employeeRepository.updateNoteEvaluationFromGlobalAppreciation();
    }

}



package com.csidigital.management.service;

import com.csidigital.dao.entity.*;
import com.csidigital.shared.dto.request.EmployeeRequest;
import com.csidigital.shared.dto.response.*;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);
    List<Employee> getAllEmployees();
    EmployeeResponse getEmployeeById(Long id);

    EmployeeResponse updateEmployee(EmployeeRequest request, Long id);
    boolean hasAdministrativeData(Long employeeId);
    void deleteEmployee(Long id);
    AdministrativeData getEmployeeAdministartiveData(Long id);
   // void deleteEmployee(Long id);

    TechnicalFileResponse getEmployeeTechnicalFile(Long id);

    List<EducationResponse> getEmployeeEducation(Long id);
    List<ExperienceResponse> getEmployeeExperience(Long id);
    List<CertificationResponse> getEmployeeCertification(Long id);
    List<LanguageResponse> getEmployeeLanguage(Long id);
    List<SkillsResponse> getEmployeeSkills(Long id);


    String employeeSerialNumberGenerator ();

    List<Employee> findByEmployeeStatus();

    List<Employee> getAllCandidates ();
    List<Employee> getAllResourcesBackOffice ();

    List<Employee> getAllResourcesInterne ();

    List<Employee> getAllResourcesExterne();
    List<Employee> getAllInternes();
    List<Employee> getAllExternes();

    List<AssOfferCandidateResponse> getOfferCandidates(Long id);
    void updateStatusToInProcessById (Long id);
    void updateStatusToInProgressById (Long id);
    void updateStatusToTopProfilesById (Long id);
    void updateStatusToPreQualifiedById (Long id);
    void updateStatusToConvertedToResourceById (Long id);
    void  updateStatusToDoNotContactById(Long id);
    void updateStatusToArchiveById (Long id);
    void updateStatusToCreateContractById (Long id);

    List<Employee>  getConvertedCandidates();
    List<Employee> getNotConvertedCandidates();
    List<Contract> getContractsEmployee(Long id);

    List<Availability> getAvailabilityEmployee(Long id);

    //Statistiques

    int countConvertedToRessource();

    int countArchived();


    int countDONOTCONTACT();

    int countINPROCESS();

    int countPREQUALIFIED();

    int countTOPPROFILES();
    int countINPROGRESS();



    //les méthodes du congés
    Double getSoldeConges(Long employeeId);

    Double getSpecialPaidLeaveRest(Long employeeId);

    Double getSicknessLeaveRest(Long employeeId);
    List<Employee>  getAllSuperior();
    void updateSuperiorById(Long id, Long hierarchicalSuperiorNum);
    String findEmployeeNamesByEquipmentId(Long equipmentId);

    Double updateNoteEvaluationFromGlobalAppreciation();

}


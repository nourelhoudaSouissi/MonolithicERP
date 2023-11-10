package com.csidigital.management.controller;

import com.csidigital.dao.entity.*;
import com.csidigital.management.service.impl.EmployeeImpl;
import com.csidigital.shared.dto.request.EmployeeRequest;
import com.csidigital.shared.dto.response.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

@RequestMapping("/rh/employee")
public class EmployeeController {
    @Autowired
    private EmployeeImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/{employeeId}/hasAdministrativeData")
    public ResponseEntity<Boolean> hasAdministrativeData(@PathVariable Long employeeId) {
        boolean hasAdminData = employeeService.hasAdministrativeData(employeeId);
        return ResponseEntity.ok(hasAdminData);
    }
    @GetMapping("get/{id}/evaluation")
    public List<EvaluationResponse> getEmployeeEvaluation(@PathVariable Long id) {
        return employeeService.getEmployeeEvaluations(id);
    }
    @GetMapping("/get/{id}/administrativeData")
    public AdministrativeData getEmployeeAdministrativeData(@PathVariable Long id) {
        return employeeService.getEmployeeAdministartiveData(id);
    }
    @GetMapping("/get/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Long id) {
         return employeeService.getEmployeeById(id);
    }

    @GetMapping("/get/{id}/technicalFile")
    public TechnicalFileResponse getEmployeeTechnicalFile(@PathVariable Long id) {
        return employeeService.getEmployeeTechnicalFile(id);
    }

    @GetMapping("get/{id}/education")
    public List<EducationResponse> getEmployeeEducation(@PathVariable Long id) {
        return employeeService.getEmployeeEducation(id);
    }

    @GetMapping("get/{id}/experience")
    public List<ExperienceResponse> getEmployeeExperience(@PathVariable Long id) {
        return employeeService.getEmployeeExperience(id);
    }

    @GetMapping("get/{id}/certificaton")
    public List<CertificationResponse> getEmployeeCertification(@PathVariable Long id) {
        return employeeService.getEmployeeCertification(id);
    }

    @GetMapping("get/{id}/language")
    public List<LanguageResponse> getEmployeeLanguage(@PathVariable Long id) {
        return employeeService.getEmployeeLanguage(id);
    }

    @GetMapping("get/{id}/skills")
    public List<SkillsResponse> getEmployeeSkills(@PathVariable Long id) {
        return employeeService.getEmployeeSkills(id);
    }

    @GetMapping("get/{id}/candidature")
    public List<AssOfferCandidateResponse> getOfferCandidates(@PathVariable Long id) {
        return employeeService.getOfferCandidates(id);
    }

    @PostMapping("/add")
    public EmployeeResponse createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.createEmployee(employeeRequest);
    }

    @PutMapping("/update/{id}")
    public EmployeeResponse updateEmployee(@Valid @RequestBody EmployeeRequest employeeRequest,
                                           @PathVariable Long id) {
        return employeeService.updateEmployee(employeeRequest, id);
    }


        @DeleteMapping("/delete/{id}")
        public void deleteEmployee(@PathVariable Long id) {
            employeeService.deleteEmployee(id);
        }

    @GetMapping("/testV1")
    public List<Employee> getEmployee() {
        return employeeService.findByEmployeeStatus();
    }


    @GetMapping("/getAllResourcesBackOffice")
    public List<Employee> getAllResourcesBackOffice() {
        return employeeService.getAllResourcesBackOffice();
    }

    @GetMapping("/getAllResourcesInterne")
    public List<Employee> getAllResourcesInterne() {
        return employeeService.getAllResourcesInterne();
    }

    @GetMapping("/getAllResourcesExterne")
    public List<Employee> getAllResourcesExterne() {
        return employeeService.getAllResourcesExterne();
    }

    @GetMapping("/getAllInternes")
    public List<Employee> getAllInternes() {
        return employeeService.getAllInternes();
    }
    @GetMapping("/getAllExternes")
    public List<Employee> getAllExternes() {
        return employeeService.getAllExternes();
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getConvertedCandidates() {
        return employeeService.getConvertedCandidates();
    }

    @GetMapping("/getAllNotConverted")
    public List<Employee> getNotConvertedCandidates() {
        return employeeService.getNotConvertedCandidates();
    }


    @PutMapping("/updateToInProcessById/{id}")
    void updateStatusToInProcessById(@PathVariable Long id) {
        employeeService.updateStatusToInProcessById(id);
    }

    @PutMapping("/updateToInProgressById/{id}")
    void updateStatusToInProgressById(@PathVariable Long id) {
        employeeService.updateStatusToInProgressById(id);
    }

    @PutMapping("/updateToTopProfilesById/{id}")
    void updateStatusToTopProfilesById(@PathVariable Long id) {
        employeeService.updateStatusToTopProfilesById(id);
    }

    @PutMapping("/updateToPreQualifiedById/{id}")
    void updateStatusToPreQualifiedById(@PathVariable Long id) {
        employeeService.updateStatusToPreQualifiedById(id);
    }

    @PutMapping("/updateToConvertedToResourceById/{id}")
    void updateStatusToConvertedToResourceById(@PathVariable Long id) {
        employeeService.updateStatusToConvertedToResourceById(id);
    }

    @PutMapping("/updateToCreateContractById/{id}")
    void updateStatusToCreateContractById(@PathVariable Long id) {
        employeeService.updateStatusToCreateContractById(id);
    }

    @PutMapping("/updateToDoNotContactById/{id}")
    void updateStatusToDoNotContactById(@PathVariable Long id) {
        employeeService.updateStatusToDoNotContactById(id);
    }

    @PutMapping("/updateToArchiveById/{id}")
    void updateStatusToArchiveById(@PathVariable Long id) {
        employeeService.updateStatusToArchiveById(id);
    }

    /*
    @PostMapping("/{id}/assignEquipment/{equipmentId}")
    public ResponseEntity<String> assignEquipmentToEmployee(
            @PathVariable Long employeeId,
            @PathVariable Long equipmentId
    ) {
        try {
            employeeService.assignEquipmentToEmployee(equipmentId, employeeId);
            return ResponseEntity.ok("Equipment assigned to employee successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid employee ID or equipment ID.");
        }
    }*/

    @GetMapping("/{id}/getContractsEmployee")
    public List<Contract> getContractsEmployee(@PathVariable Long id) {
        return employeeService.getContractsEmployee(id);
    }

    @GetMapping("/{id}/getAvailabilityEmployee")
    public List<Availability> getAvailabilityEmployee(@PathVariable Long id) {
        return employeeService.getAvailabilityEmployee(id);
    }


    @GetMapping("/countAllConvertedToResource")
    public int countConvertedToRessource(){
        return employeeService.countConvertedToRessource();
    }
    @GetMapping("/countAllArchived")
    public int countArchived(){
        return employeeService.countArchived();
    }
    @GetMapping("/countAllDoNotContact")
    public int countDoNotContact(){
        return employeeService.countDONOTCONTACT();
    }
    @GetMapping("/countAllInProcess")
    public int countINPROCESS(){
        return employeeService.countINPROCESS();
    }
    @GetMapping("/countAllPreQualified")
    public int countPREQUALIFIED(){
        return employeeService.countPREQUALIFIED();
    }
    @GetMapping("/countAllTopProfiles")
    public int countTOPPROFILES(){
        return employeeService.countTOPPROFILES();
    }
    @GetMapping("/countAllInProgress")
    public int countINPROGRESS(){
        return employeeService.countINPROGRESS();
    }
    @GetMapping("/{id}/soldeconges")
    public Double getSoldeConges(@PathVariable Long id) {
        Double soldeConges = employeeService.getSoldeConges(id);
        return soldeConges;
    }
    @GetMapping("/{id}/specialPaidLeaveRest")
    public Double getSpecialPaidLeaveRest(@PathVariable Long id) {
        Double specialPaidLeaveRest = employeeService.getSpecialPaidLeaveRest(id);
        return specialPaidLeaveRest;
    }
    @GetMapping("/{id}/sicknessLeaveRest")
    public Double getSicknessLeaveRest(@PathVariable Long id) {
        Double sicknessLeaveRest = employeeService.getSicknessLeaveRest(id);
        return sicknessLeaveRest;
    }

    @GetMapping("/getAllSuperior")
    public List<Employee> getAllSuperior(){
        return employeeService.getAllSuperior();
    }



   /* @PutMapping("/updateSuperiorById/{id}")
    public void updateSuperiorById(
            @PathVariable Long id,
            @RequestBody Long hierarchicalSuperiorNum
    ) {
        employeeService.updateSuperiorById(id, hierarchicalSuperiorNum);
    }
*/

    @PutMapping("/updateSuperiorById/{id}")
    public ResponseEntity<Void> updateSuperiorById(
            @PathVariable Long id,
            @RequestBody Long hierarchicalSuperiorNum
    ) {
        try {
            employeeService.updateSuperiorById(id, hierarchicalSuperiorNum);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findEmployeeNamesByEquipmentId/{equipmentId}")
    public String findEmployeeNamesByEquipmentId(@PathVariable Long equipmentId) {
        return "{\"name\":\"" +  employeeService.findEmployeeNamesByEquipmentId(equipmentId) + "\"}";
    }


    @PutMapping("/updateNoteEvaluation")
    public ResponseEntity<Double> updateNoteEvaluationFromGlobalAppreciation() {
        Double updatedNoteEvaluation = employeeService.updateNoteEvaluationFromGlobalAppreciation();
        return ResponseEntity.ok(updatedNoteEvaluation);
    }


    @GetMapping("/getAllResources")
    public List<Employee>  getAllResources(){
        return employeeService.getAllResources();
    }
}




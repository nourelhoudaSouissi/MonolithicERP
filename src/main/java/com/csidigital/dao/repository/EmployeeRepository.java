package com.csidigital.dao.repository;

import com.csidigital.dao.entity.Employee;
import com.csidigital.shared.enumeration.EmployeeStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query(value ="SELECT serial_number FROM employee WHERE serial_number IS NOT NULL  ORDER BY Id DESC LIMIT 1;",nativeQuery = true)
    String resourceLastCode();


    //Filtrer les candidats
    @Query(value ="SELECT * FROM employee WHERE dtype= 'Employee'", nativeQuery = true)
    List<Employee> getAllCandidates ();


    //Filtrer les Ressources internes et backoffices
    @Query(value ="SELECT * FROM employee WHERE resource_type= 'INTERNAL_RESOURCE' OR  resource_type= 'BACKOFFICE_RESOURCE' and employee_status='CONVERTED_TO_RESOURCE'", nativeQuery = true)
    List<Employee> getAllInternes();

    // filter les Ressources externes
    @Query(value ="SELECT * FROM employee WHERE resource_type= 'EXTERNAL_RESOURCE' and employee_status='CONVERTED_TO_RESOURCE'", nativeQuery = true)
    List<Employee> getAllExternes();

    @Modifying
    @Query(value = " UPDATE employee SET employee_status = 'IN_PROCESS' WHERE id =:id", nativeQuery = true)
    void updateStatusToInProcessById(@Param("id") Long id);


    @Modifying
    @Query(value = " UPDATE employee SET employee_status = 'IN_PROGRESS' WHERE id =:id", nativeQuery = true)
    void updateStatusToInProgressById(@Param("id") Long id);


    @Modifying
    @Query(value = " UPDATE employee SET employee_status = 'TOP_PROFILES' WHERE id =:id", nativeQuery = true)
    void updateStatusToTopProfilesById(@Param("id") Long id);


    @Modifying
    @Query(value = " UPDATE employee SET employee_status = 'PRE_QUALIFIED' WHERE id =:id", nativeQuery = true)
    void updateStatusToPreQualifiedById(@Param("id") Long id);


    @Modifying
    @Query(value = " UPDATE employee SET employee_status = 'CONVERTED_TO_RESOURCE' WHERE id =:id", nativeQuery = true)
    void updateStatusToConvertedToResourceById(@Param("id") Long id);


    @Modifying
    @Query(value = " UPDATE employee SET employee_status = 'DO_NOT_CONTACT' WHERE id =:id", nativeQuery = true)
    void updateStatusToDoNotContactById(@Param("id") Long id);



    @Modifying
    @Query(value = " UPDATE employee SET employee_status = 'ARCHIVE' WHERE id =:id", nativeQuery = true)
    void updateStatusToArchiveById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE employee SET employee_status = 'CREATE_CONTRACT' WHERE id =:id", nativeQuery = true)
    void updateStatusToCreateContractById(@Param("id") Long id);
    //Récupérer tous les candidats converti en ressource
    @Modifying
    @Query(value = " SELECT * FROM employee WHERE employee_status= 'CONVERTED_TO_RESOURCE'", nativeQuery = true)
    List<Employee>  getConvertedCandidates();

    //Récupérer tous les candidats non conveti en ressource
    @Modifying
    @Query(value = "SELECT * FROM employee WHERE employee_status != 'CONVERTED_TO_RESOURCE'", nativeQuery = true)
    List<Employee> getNotConvertedCandidates();



    //Filtrer les Ressources BackOffice
    @Query(value ="SELECT * FROM employee WHERE dtype= 'BackOffice'", nativeQuery = true)
    List<Employee> getAllResourcesBackOffice ();

    //Filtrer les Ressources Internes
    @Query(value ="SELECT * FROM employee WHERE dtype= 'Resource'", nativeQuery = true)
    List<Employee> getAllResourcesInterne ();

    //Filtrer les Ressources externes
    @Query(value ="SELECT * FROM employee WHERE dtype= 'ExternalResource'", nativeQuery = true)
    List<Employee> getAllResourcesExterne();


    //filtrer les employés selon leurs status
    List<Employee> findByEmployeeStatus(EmployeeStatus employeeStatus);

    //Statistiques
    @Query(value = "SELECT COUNT(*) FROM employee WHERE employee_status = 'CONVERTED_TO_RESOURCE'", nativeQuery = true)
    int countConvertedToRessource();
    @Query(value = "SELECT COUNT(*) FROM employee WHERE employee_status = 'ARCHIVE'", nativeQuery = true)
    int countArchived();

    @Query(value = "SELECT COUNT(*) FROM employee WHERE employee_status = 'DO_NOT_CONTACT'", nativeQuery = true)
    int countDONOTCONTACT();
    @Query(value = "SELECT COUNT(*) FROM employee WHERE employee_status = 'IN_PROCESS'", nativeQuery = true)
    int countINPROCESS();
    @Query(value = "SELECT COUNT(*) FROM employee WHERE employee_status = 'IN_PROGRESS'", nativeQuery = true)
    int countINPROGRESS();
    @Query(value = "SELECT COUNT(*) FROM employee WHERE employee_status = 'PRE_QUALIFIED'", nativeQuery = true)
    int countPREQUALIFIED();
    @Query(value = "SELECT COUNT(*) FROM employee WHERE employee_status = 'TOP_PROFILES'", nativeQuery = true)
    int countTOPPROFILES();


    @Modifying
    @Query(value = "UPDATE employee SET hierarchical_superior_id = :hierarchicalSuperiorNum WHERE id = :id", nativeQuery = true)
    void updateSuperiorById(@Param("id") Long id, @Param("hierarchicalSuperiorNum") Long hierarchicalSuperiorNum);

    //getAllConvertedToResoures : supérieur hiérarchique
    @Query(value ="SELECT * FROM employee WHERE employee_status = 'CONVERTED_TO_RESOURCE'", nativeQuery = true)
    List<Employee> getAllSuperior();

    @Query(value = "SELECT string_agg(concat(e.first_name, ' ', e.last_name), ', ') " +
            "FROM employee AS e " +
            "INNER JOIN ass_equipment_employee AS ee ON e.id = ee.employee_id " +
            "INNER JOIN equipment AS eq ON ee.equipment_id = eq.id " +
            "WHERE eq.id = :equipmentId", nativeQuery = true)
    String findEmployeeNamesByEquipmentId(Long equipmentId);


    //get la note d'évaluation finale
    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.noteEvaluation = (SELECT AVG(ev.globalAppreciation) FROM Evaluation ev WHERE ev.employee = e)")
    Double updateNoteEvaluationFromGlobalAppreciation();
    @Query(value ="SELECT * FROM employee WHERE employee_status='CONVERTED_TO_RESOURCE' and resource_type= 'EXTERNAL_RESOURCE' or resource_type= 'INTERNAL_RESOURCE' ", nativeQuery = true)
    List<Employee> getAllResources();
}

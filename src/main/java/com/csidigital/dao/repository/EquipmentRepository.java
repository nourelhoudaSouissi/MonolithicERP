package com.csidigital.dao.repository;

import com.csidigital.dao.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {

    @Modifying
    @Query(value = " UPDATE equipment SET status = 'AVAILABLE' WHERE id =:id", nativeQuery = true)
    void updateStatusToAvailableById(@Param("id") Long id);


    @Modifying
    @Query(value = " UPDATE equipment SET status = 'UNAVAILABLE' WHERE id =:id", nativeQuery = true)
    void updateStatusToUnavailableById(@Param("id") Long id);


    @Modifying
    @Query(value = "UPDATE equipment SET motif_unavailability = :motifUnavailability, disponibilityDate = :disponibilityDate WHERE id = :id", nativeQuery = true)
    void updateMotifById(@Param("id") Long id, @Param("motifUnavailability") String motifUnavailability, @Param("disponibilityDate")LocalDate disponibilityDate);

    @Modifying
    @Query(value = "UPDATE equipment SET affectation = 'AFFECTED', status = 'UNAVAILABLE', motif_unavailability = 'Affecté à un employé' WHERE id = :id", nativeQuery = true)
    void updateAffectedById(@Param("id") Long id);


    @Modifying
    @Query(value = " UPDATE equipment SET affectation = 'UNAFFECTED' WHERE id =:id", nativeQuery = true)
    void updateUnaffectedById(@Param("id") Long id);

    @Query(value ="SELECT * FROM equipment WHERE affectable IS TRUE ORDER BY Id DESC;", nativeQuery = true)
    List<Equipment> getAffectableEquipments();



    @Query("SELECT COUNT(e) FROM Equipment e")
    Long countEquipment();

    @Modifying
    @Query(value = "UPDATE equipment SET return_comment = :returnComment, return_status= :returnStatus,return_date = :returnDate WHERE id = :id", nativeQuery = true)
    void updateRestituationById(@Param("id") Long id, @Param("returnComment") String returnComment, @Param("returnStatus") String returnStatus, @Param("returnDate") LocalDate returnDate);

    @Query(value = "SELECT COUNT(*) FROM equipment WHERE affectable = 'true'", nativeQuery = true)
    int countAffectables();
    @Query(value = "SELECT COUNT(*) FROM equipment WHERE affectable = 'false'", nativeQuery = true)
    int countNonAffectables();

    @Query(value = "SELECT COUNT(*) FROM equipment WHERE amortizable = 'true'", nativeQuery = true)
    int countAmortizables();
    @Query(value = "SELECT COUNT(*) FROM equipment WHERE amortizable = 'false'", nativeQuery = true)
    int countNonAmortizables();
    @Query(value = "SELECT COUNT(*) FROM equipment WHERE status = 'AVAILABLE'", nativeQuery = true)
    int countAvailalbe();
    @Query(value = "SELECT COUNT(*) FROM equipment WHERE status = 'UNAVAILABLE'", nativeQuery = true)
    int countUnavailalbe();
   @Query(value = "SELECT COUNT(*) FROM equipment WHERE affectation = 'AFFECTED'", nativeQuery = true)
    int countAffectation();

    @Query(value = "SELECT COUNT(*) FROM equipment WHERE affectation = 'UNAFFECTED'", nativeQuery = true)
    int countUnaffectation();

    @Query(value = "SELECT COUNT(*) FROM equipment WHERE status = 'AVAILABLE' AND affectable = 'true'", nativeQuery = true)
    int countAvailalbeAff();
    @Query(value = "SELECT COUNT(*) FROM equipment WHERE status = 'UNAVAILABLE' AND affectable = 'true'", nativeQuery = true)
    int countUnavailalbeAff();
}

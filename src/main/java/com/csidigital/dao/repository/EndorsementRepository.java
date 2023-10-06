package com.csidigital.dao.repository;

import com.csidigital.dao.entity.Endorsement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EndorsementRepository extends JpaRepository<Endorsement,Long> {

    @Modifying
    @Query(value = "UPDATE endorsement SET status =:status WHERE id =:id", nativeQuery = true)
    void updateStatusById(@Param("id") Long id, @Param("status") String status);

    @Modifying
    @Query(value = " UPDATE endorsement SET status = 'ACCEPTED' WHERE id =:id", nativeQuery = true)
    void updateStatusToAcceptedById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE endorsement SET status = 'REFUSED' WHERE id =:id", nativeQuery = true)
    void updateStatusToRefusedById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE endorsement SET status = 'EXPIRED' WHERE id =:id", nativeQuery = true)
    void updateStatusToExpiredById(@Param("id") Long id);
    @Modifying
    @Query(value = " UPDATE endorsement SET status = 'SENT' WHERE id =:id", nativeQuery = true)
    void updateStatusToSentById(@Param("id") Long id);


    //Statistiques
    @Query(value = "SELECT COUNT(*) FROM endorsement WHERE status = 'STILL_PENDING'", nativeQuery = true)
    int countStillPendingStatus();

    @Query(value = "SELECT COUNT(*) FROM endorsement WHERE status = 'REFUSED'", nativeQuery = true)
    int countRefusedStatus();

    @Query(value = "SELECT COUNT(*) FROM endorsement WHERE status = 'ACCEPTED'", nativeQuery = true)
    int countAcceptedStatus();

    @Query(value = "SELECT COUNT(*) FROM endorsement WHERE status = 'SENT'", nativeQuery = true)
    int countSentStatus();

    @Query(value = "SELECT COUNT(*) FROM endorsement WHERE status = 'EXPIRED'", nativeQuery = true)
    int countExpiredStatus();
}

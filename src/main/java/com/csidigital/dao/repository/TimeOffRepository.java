package com.csidigital.dao.repository;

import com.csidigital.dao.entity.TimeOff;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Transactional
@Repository
public interface TimeOffRepository extends JpaRepository<TimeOff,Long> {

    @Modifying
    @Query(value = "UPDATE time_off SET request_status =:requestStatus WHERE id =:id", nativeQuery = true)
    void updateStatusById(@Param("id") Long id, @Param("requestStatus") String requestStatus);

    @Modifying
    @Query(value = " UPDATE time_off SET request_status = 'VALIDATED' WHERE id =:id", nativeQuery = true)
    void updateStatusToValidatedById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE time_off SET request_status = 'REJECTED' WHERE id =:id", nativeQuery = true)
    void updateStatusToRejectedById(@Param("id") Long id);


    @Query(value = "SELECT lt.name, SUM(tf.time_off_period) AS totalDuration " +
            "FROM time_off tf " +
            "JOIN tf.employee emp " +
            "JOIN tf.leave_type lt " +
            "WHERE emp.Id = :employeeId AND tf.request_status = 'VALIDATED' " +
            "GROUP BY lt.name",
            nativeQuery = true)
    List<Object[]> getTotalDurationByLeaveTypeAndEmployeeId(@Param("employeeId") Long employeeId);

    @Query(value = "SELECT lt.name, SUM(tf.time_off_period) AS totalDuration " +
            "FROM time_off tf " +
            "JOIN employee emp ON tf.employee_id = emp.id " +
            "JOIN leave_type lt ON tf.leave_type_id = lt.id " +
            "WHERE emp.id = :employeeId AND tf.request_status = 'VALIDATED' AND lt.time_off_type = 'SPECIAL_PAID_LEAVE' " +
            "GROUP BY lt.name",
            nativeQuery = true)
    List<Object[]> getTotalDurationSpecialPaidLeaveByLeaveTypeAndEmployeeId(@Param("employeeId") Long employeeId);
    @Query(value = "SELECT lt.name, SUM(tf.time_off_period) AS totalDuration " +
            "FROM time_off tf " +
            "JOIN employee emp ON tf.employee_id = emp.id " +
            "JOIN leave_type lt ON tf.leave_type_id = lt.id " +
            "WHERE emp.id = :employeeId AND tf.request_status = 'VALIDATED' AND lt.time_off_type = 'SICKNESS_LEAVE' " +
            "GROUP BY lt.name",
            nativeQuery = true)
    List<Object[]> getTotalDurationSicknessLeaveByLeaveTypeAndEmployeeId(@Param("employeeId") Long employeeId);


    @Query(value = "  SELECT SUM(tf.time_off_period) As totalDuration " +
            "FROM time_off tf " +
            "JOIN employee emp ON tf.employee_id = emp.id " +
            "JOIN leave_type lt ON tf.leave_type_id = lt.id " +
            "WHERE emp.id = 1 AND tf.request_status = 'VALIDATED' AND lt.time_off_type = 'SPECIAL_PAID_LEAVE' ",
            nativeQuery = true)
    Double getTotalDurationSpecialPaidLeaveEmployeeId(@Param("employeeId") Long employeeId);

    @Query(value = "  SELECT SUM(tf.time_off_period) As totalDuration " +
            "FROM time_off tf " +
            "JOIN employee emp ON tf.employee_id = emp.id " +
            "JOIN leave_type lt ON tf.leave_type_id = lt.id " +
            "WHERE emp.id = 1 AND tf.request_status = 'VALIDATED' AND lt.time_off_type = 'SICKNESS_LEAVE' ",
            nativeQuery = true)
    Double getTotalDurationSicknessLeaveEmployeeId(@Param("employeeId") Long employeeId);

    @Query(value = "SELECT SUM(tf.time_off_period) As totalDuration " +
            "FROM time_off tf " +
            "JOIN employee emp ON tf.employee_id = emp.id " +
            "JOIN leave_type lt ON tf.leave_type_id = lt.id " +
            "WHERE emp.id = 1 AND tf.request_status = 'VALIDATED' AND lt.time_off_type = 'PAID_LEAVE'",
            nativeQuery = true)
    Double getTotalDurationPaidLeaveEmployeeId(@Param("employeeId") Long employeeId);

    @Query(value = "SELECT SUM(tf.time_off_period) As totalDuration " +
            "FROM time_off tf " +
            "JOIN employee emp ON tf.employee_id = emp.id " +
            "JOIN leave_type lt ON tf.leave_type_id = lt.id " +
            "WHERE emp.id = 1 AND tf.request_status = 'VALIDATED' AND lt.time_off_type = 'UNPAIED_TIME_OFF'",
            nativeQuery = true)
    Double getTotalDurationUnpaidLeaveEmployeeId(@Param("employeeId") Long employeeId);

    @Query(value = "SELECT COUNT(*) FROM  time_off  WHERE request_status = 'VALIDATED'", nativeQuery = true)
    int countValidated();

    @Query(value = "SELECT COUNT(*) FROM  time_off  WHERE request_status = 'REJECTED'", nativeQuery = true)
    int countRejected();

    @Query(value = "SELECT COUNT(*) FROM  time_off  WHERE request_status = 'PENDING'", nativeQuery = true)
    int countPending();

}

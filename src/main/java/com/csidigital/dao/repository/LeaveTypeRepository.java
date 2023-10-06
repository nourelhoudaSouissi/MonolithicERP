package com.csidigital.dao.repository;

import com.csidigital.dao.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveTypeRepository extends JpaRepository<LeaveType,Long> {
    @Query(value = " SELECT SUM(duration) AS total_sickness_leave_duration FROM leave_type WHERE time_off_type = 'SICKNESS_LEAVE'", nativeQuery = true)
    Double getTotalDurationSicknessLeave();
    @Query(value = " SELECT SUM(duration) AS total_special_paid_leave_duration FROM leave_type WHERE time_off_type = 'SPECIAL_PAID_LEAVE'", nativeQuery = true)
    Double getTotalDurationSpecialPaidLeave();


    @Query(value = " SELECT name, duration FROM leave_type WHERE time_off_type = 'SPECIAL_PAID_LEAVE'", nativeQuery = true)
    List<Object[]>  getSpecialPaidLeaveList();

    @Query(value = " SELECT name, duration FROM leave_type WHERE time_off_type = 'SICKNESS_LEAVE'", nativeQuery = true)
    List<Object[]>  getSicknessLeaveList();

}

package com.csidigital.dao.repository;

import com.csidigital.dao.entity.CalendarMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarMonthRepository extends JpaRepository<CalendarMonth,Long> {
}

package com.csidigital.dao.repository;

import com.csidigital.dao.entity.AppointmentNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentNoteRepository extends JpaRepository<AppointmentNote, Long> {
}

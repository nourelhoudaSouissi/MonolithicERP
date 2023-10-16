package com.csidigital.dao.repository;

import com.csidigital.dao.entity.ContactNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactNoteRepository extends JpaRepository<ContactNote,Long> {
}

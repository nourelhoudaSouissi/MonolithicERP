package com.csidigital.dao.repository;

import com.csidigital.dao.entity.OfferCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssOfferCandidateRepository extends JpaRepository<OfferCandidate, Long> {
//     List<OfferCandidate> findByOffer(Offer offer);
//     List<OfferCandidate> findByCandidate(Employee employee);
}

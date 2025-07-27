package com.zarpas_safaris.repository;

import com.zarpas_safaris.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    // Find contacts by responded status with pagination
    Page<Contact> findByResponded(boolean responded, Pageable pageable);

    // Find contacts within a date range
    List<Contact> findByContactDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find contacts by email
    List<Contact> findByEmail(String email);

    // Find contacts containing search term in subject or message
    @Query("SELECT c FROM Contact c WHERE c.subject LIKE %:searchTerm% OR c.message LIKE %:searchTerm%")
    Page<Contact> searchContacts(String searchTerm, Pageable pageable);

    // Update responded status
    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.responded = :responded WHERE c.id = :id")
    int updateRespondedStatus(Long id, boolean responded);

    // Count unresponded contacts
    long countByRespondedFalse();

    // Find latest contacts
    Page<Contact> findByOrderByContactDateDesc(Pageable pageable);
}

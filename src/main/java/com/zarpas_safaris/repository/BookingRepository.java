package com.zarpas_safaris.repository;

import com.zarpas_safaris.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Find bookings by confirmation status with pagination
    Page<Booking> findByConfirmed(boolean confirmed, Pageable pageable);

    // Find bookings for a specific tour
    Page<Booking> findByTourName(String tourName, Pageable pageable);

    // Find bookings within a date range
    List<Booking> findByTourDateBetween(LocalDate startDate, LocalDate endDate);

    // Find bookings by email
    List<Booking> findByEmail(String email);

    // Search bookings by tour name or customer name
    @Query("SELECT b FROM Booking b WHERE b.tourName LIKE %:searchTerm% OR b.fullName LIKE %:searchTerm%")
    Page<Booking> searchBookings(String searchTerm, Pageable pageable);

    // Update confirmation status
    @Transactional
    @Modifying
    @Query("UPDATE Booking b SET b.confirmed = :confirmed WHERE b.id = :id")
    int updateConfirmationStatus(Long id, boolean confirmed);

    // Count unconfirmed bookings
    long countByConfirmedFalse();

    // Find upcoming bookings
    @Query("SELECT b FROM Booking b WHERE b.tourDate >= CURRENT_DATE ORDER BY b.tourDate ASC")
    Page<Booking> findUpcomingBookings(Pageable pageable);

    // Find bookings by number of guests range
    Page<Booking> findByNumberOfGuestsBetween(int minGuests, int maxGuests, Pageable pageable);
}

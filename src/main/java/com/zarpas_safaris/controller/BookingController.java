package com.zarpas_safaris.controller;

import com.zarpas_safaris.Daos.BookingRequest;
import com.zarpas_safaris.Daos.BookingResponse;
import com.zarpas_safaris.model.Booking;
import com.zarpas_safaris.repository.BookingRepository;
import com.zarpas_safaris.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequest request) {
        try {
            BookingResponse response = bookingService.createBooking(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<Booking> confirmBooking(@PathVariable Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            booking.setConfirmed(true);
            Booking updatedBooking = bookingRepository.save(booking);
            return ResponseEntity.ok(updatedBooking);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
package com.zarpas_safaris.service;

import com.zarpas_safaris.Daos.BookingRequest;
import com.zarpas_safaris.Daos.BookingResponse;
import com.zarpas_safaris.model.Booking;
import com.zarpas_safaris.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EmailService emailService;

    public BookingResponse createBooking(BookingRequest bookingRequest) {
        // Convert DTO to Entity
        Booking booking = new Booking();
        booking.setTourName(bookingRequest.getTourName());
        booking.setFullName(bookingRequest.getFullName());
        booking.setEmail(bookingRequest.getEmail());
        booking.setPhone(bookingRequest.getPhone());
        booking.setTourDate(bookingRequest.getTourDate());
        booking.setNumberOfGuests(bookingRequest.getNumberOfGuests());
        booking.setSpecialRequirements(bookingRequest.getSpecialRequirements());

        // Save to database
        Booking savedBooking = bookingRepository.save(booking);

        // Send confirmation emails
        sendConfirmationEmails(savedBooking);

        // Convert Entity to DTO
        BookingResponse response = new BookingResponse();
        response.setBookingId(savedBooking.getId());
        response.setFullName(savedBooking.getFullName());
        response.setEmail(savedBooking.getEmail());
        response.setTourName(savedBooking.getTourName());
        response.setTourDate(savedBooking.getTourDate());
        response.setNumberOfGuests(savedBooking.getNumberOfGuests());
        response.setMessage("Booking created successfully. Confirmation email sent.");

        return response;
    }

    private void sendConfirmationEmails(Booking booking) {
        // Email to customer
        String customerSubject = "Your Zarpas Africana Safari Booking Confirmation";
        String customerText = String.format(
                "Dear %s,\n\nThank you for booking your %s safari with us on %s.\n\n" +
                        "We will contact you shortly to confirm all details.\n\n" +
                        "Best regards,\nZarpas Africana Safaris Team",
                booking.getFullName(),
                booking.getTourName(),
                booking.getTourDate()
        );
        emailService.sendConfirmationEmail(booking.getEmail(), customerSubject, customerText);

        // Email to admin
        String adminSubject = "New Safari Booking: " + booking.getTourName();
        String adminText = String.format(
                "New booking received:\n\n" +
                        "Tour: %s\n" +
                        "Name: %s\n" +
                        "Email: %s\n" +
                        "Phone: %s\n" +
                        "Date: %s\n" +
                        "Guests: %d\n" +
                        "Special Requirements: %s",
                booking.getTourName(),
                booking.getFullName(),
                booking.getEmail(),
                booking.getPhone(),
                booking.getTourDate(),
                booking.getNumberOfGuests(),
                booking.getSpecialRequirements()
        );
        emailService.sendConfirmationEmail("bookings@zarpas.org", adminSubject, adminText);
    }
}
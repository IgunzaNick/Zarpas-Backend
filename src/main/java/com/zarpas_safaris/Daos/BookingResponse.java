package com.zarpas_safaris.Daos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingResponse {
    private Long bookingId;
    private String fullName;
    private String email;
    private String tourName;
    private LocalDate tourDate;
    private int numberOfGuests;
    private String message;

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public void setTourDate(LocalDate tourDate) {
        this.tourDate = tourDate;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

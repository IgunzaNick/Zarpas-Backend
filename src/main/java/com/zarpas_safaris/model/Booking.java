package com.zarpas_safaris.model;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tourName;
    private String fullName;
    private String email;
    private String phone;
    private LocalDate tourDate;
    private int numberOfGuests;
    private String specialRequirements;
    private LocalDate bookingDate = LocalDate.now();
    private boolean confirmed = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Booking() {
    }

    public Booking(Long id, String tourName, String fullName, String email, String phone, LocalDate tourDate, int numberOfGuests, String specialRequirements, LocalDate bookingDate, boolean confirmed) {
        this.id = id;
        this.tourName = tourName;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.tourDate = tourDate;
        this.numberOfGuests = numberOfGuests;
        this.specialRequirements = specialRequirements;
        this.bookingDate = bookingDate;
        this.confirmed = confirmed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getTourDate() {
        return tourDate;
    }

    public void setTourDate(LocalDate tourDate) {
        this.tourDate = tourDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getSpecialRequirements() {
        return specialRequirements;
    }

    public void setSpecialRequirements(String specialRequirements) {
        this.specialRequirements = specialRequirements;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}

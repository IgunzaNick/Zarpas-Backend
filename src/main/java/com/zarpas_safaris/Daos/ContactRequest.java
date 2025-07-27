package com.zarpas_safaris.Daos;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ContactRequest {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    private String phone;

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Message is required")
    @Size(min = 10, message = "Message should be at least 10 characters")
    private String message;

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
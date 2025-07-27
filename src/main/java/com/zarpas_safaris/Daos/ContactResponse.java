package com.zarpas_safaris.Daos;

import lombok.Data;

@Data
public class ContactResponse {
    private Long contactId;
    private String fullName;
    private String email;
    private String subject;
    private String message;
    private String status;

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

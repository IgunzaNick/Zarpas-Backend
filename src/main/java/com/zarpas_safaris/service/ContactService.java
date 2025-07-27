package com.zarpas_safaris.service;

import com.zarpas_safaris.Daos.ContactRequest;
import com.zarpas_safaris.Daos.ContactResponse;
import com.zarpas_safaris.model.Contact;
import com.zarpas_safaris.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EmailService emailService;

    public ContactResponse createContact(ContactRequest contactRequest) {
        // Convert DTO to Entity
        Contact contact = new Contact();
        contact.setFullName(contactRequest.getFullName());
        contact.setEmail(contactRequest.getEmail());
        contact.setPhone(contactRequest.getPhone());
        contact.setSubject(contactRequest.getSubject());
        contact.setMessage(contactRequest.getMessage());

        // Save to database
        Contact savedContact = contactRepository.save(contact);

        // Send confirmation emails
        sendConfirmationEmails(savedContact);

        // Convert Entity to DTO
        ContactResponse response = new ContactResponse();
        response.setContactId(savedContact.getId());
        response.setFullName(savedContact.getFullName());
        response.setEmail(savedContact.getEmail());
        response.setSubject(savedContact.getSubject());
        response.setMessage("Thank you for contacting us. We'll get back to you soon.");
        response.setStatus("Message received successfully");

        return response;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    private void sendConfirmationEmails(Contact contact) {
        // Email to customer
        String customerSubject = "Thank you for contacting Zarpas Africana Safaris";
        String customerText = String.format(
                "Dear %s,\n\nThank you for your message regarding '%s'.\n\n" +
                        "We have received your inquiry and our team will respond within 24-48 hours.\n\n" +
                        "Your message:\n%s\n\n" +
                        "Best regards,\nZarpas Africana Safaris Team",
                contact.getFullName(),
                contact.getSubject(),
                contact.getMessage()
        );
        emailService.sendConfirmationEmail(contact.getEmail(), customerSubject, customerText);

        // Email to admin
        String adminSubject = "New Contact Message: " + contact.getSubject();
        String adminText = String.format(
                "New contact message received:\n\n" +
                        "From: %s\n" +
                        "Email: %s\n" +
                        "Phone: %s\n" +
                        "Subject: %s\n" +
                        "Message:\n%s\n\n" +
                        "Please respond within 24 hours.",
                contact.getFullName(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getSubject(),
                contact.getMessage()
        );
        emailService.sendConfirmationEmail("contact@zarpas.org", adminSubject, adminText);
    }
}
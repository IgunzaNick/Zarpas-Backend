package com.zarpas_safaris.controller;

import com.zarpas_safaris.Daos.ContactRequest;
import com.zarpas_safaris.Daos.ContactResponse;
import com.zarpas_safaris.model.Contact;
import com.zarpas_safaris.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<?> createContact(@Valid @RequestBody ContactRequest request) {
        try {
            ContactResponse response = contactService.createContact(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }
}

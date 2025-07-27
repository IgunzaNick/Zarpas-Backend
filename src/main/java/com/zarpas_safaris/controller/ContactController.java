package com.zarpas_safaris.controller;

import com.zarpas_safaris.Daos.ContactRequest;
import com.zarpas_safaris.Daos.ContactResponse;
import com.zarpas_safaris.model.Contact;
import com.zarpas_safaris.repository.ContactRepository;
import com.zarpas_safaris.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

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

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/respond")
    public ResponseEntity<Contact> markAsResponded(@PathVariable Long id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            contact.setResponded(true);
            Contact updatedContact = contactRepository.save(contact);
            return ResponseEntity.ok(updatedContact);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
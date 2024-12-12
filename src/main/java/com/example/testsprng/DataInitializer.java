package com.example.testsprng;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class DataInitializer {

    private ContactService contactService;

    @Autowired
    public DataInitializer(ContactService contactService) {
        this.contactService = contactService;
    }

    public void init() {
        contactService.loadContactsFromFile();
            }
}

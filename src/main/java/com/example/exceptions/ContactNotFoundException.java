package com.example.exceptions;

import java.util.List;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message) {
        super(message);
    }

}

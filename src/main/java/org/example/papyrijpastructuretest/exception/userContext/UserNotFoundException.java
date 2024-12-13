package org.example.papyrijpastructuretest.exception.userContext;

import java.io.Serializable;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

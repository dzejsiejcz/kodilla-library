package com.crud.library.controller;

public class RentNotFoundException extends RuntimeException {
    public RentNotFoundException(String message) {
        super(message);
    }

}

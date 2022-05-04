package com.crud.library.controller;

public class TitleNotFoundException extends RuntimeException {

    public TitleNotFoundException(String message) {
        super(message);
    }

}

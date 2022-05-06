package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Getter
public enum Status {
    BORROWED,
    AVAILABLE,
    DESTROYED,
    LOST;

    @JsonCreator
    public Status of(String status){
        return Status.valueOf(status.toUpperCase(Locale.ROOT));
    }
}

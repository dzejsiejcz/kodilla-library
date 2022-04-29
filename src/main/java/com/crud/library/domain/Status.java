package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum Status {
    BORROWED("BORROWED"),
    AVAILABLE("AVAILABLE"),
    DESTROYED("DESTROYED"),
    LOST("LOST");

    final String description;
    private static final List<Status> statuses = new ArrayList<>(Arrays.asList(Status.values()));

    Status(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    public static Status searchStatus(Status value) {
        for (Status status : statuses) {
            if (status.equals(value)) {
                return status;
            }
        }
        return null;
    }

}

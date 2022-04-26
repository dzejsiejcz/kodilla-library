package com.crud.library.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Status {
    BORROWED("BORROWED"),
    AVAILABLE("AVAILABLE"),
    DESTROYED("DESTROYED"),
    LOST("LOST");

    final String description;

    Status(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    private static final List<Status> statuses = new ArrayList<>(Arrays.asList(Status.values()));

    public static Status searchStatus(String value) {
        for (Status status : statuses) {
            if (status.toString().equals(value)) {
                return status;
            }
        }
        return null;
    }
}

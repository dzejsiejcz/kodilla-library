package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReaderDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Date created;
    private List<RentDto> rentDtos;

    @Builder
    public ReaderDto(int id, String firstName, String lastName, String email, Date created) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.created = created;
    }
}

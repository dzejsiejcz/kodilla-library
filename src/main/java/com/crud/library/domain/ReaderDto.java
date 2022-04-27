package com.crud.library.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReaderDto {

    private String firstName;
    private String lastName;
    private String email;
    private List<RentDto> rentDtos;

    @Builder
    public ReaderDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}

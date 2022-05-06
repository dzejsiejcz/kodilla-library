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

}

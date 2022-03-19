package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CopyDto {

    private int id;
    private List<RentDto> rentDtos;
    private Title title;
    private Status status;

    public CopyDto(Title title, Status status) {
        this.title = title;
        this.status = status;
    }

}

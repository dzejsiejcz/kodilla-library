package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CopyDto {

    private int id;
    private List<RentDto> rentDtos;
    private TitleDto titleDto;
    private Status status;

    public CopyDto(TitleDto titleDto, Status status) {
        this.titleDto = titleDto;
        this.status = status;
    }

}

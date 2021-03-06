package com.crud.library.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class RentDto {

    private int id;
    private Date rented;
    private Date returned;
    private CopyDto copyDto;

    @Builder
    public RentDto(int id, Date rented, Date returned, CopyDto copyDto) {
        this.id = id;
        this.rented = rented;
        this.returned = returned;
        this.copyDto = copyDto;
    }
}

package com.crud.library.domain;

import lombok.AllArgsConstructor;
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
    private Reader reader;
    private Copy copy;

    @Builder
    public RentDto(Date rented, Date returned, Reader reader, Copy copy) {
        this.rented = rented;
        this.returned = returned;
        this.reader = reader;
        this.copy = copy;
    }

}

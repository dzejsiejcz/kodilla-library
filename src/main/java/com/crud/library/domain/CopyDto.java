package com.crud.library.domain;

import lombok.*;

@Data
@NoArgsConstructor
public class CopyDto {

    private int id;
    private TitleDto titleDto;
    private Status status;

    public CopyDto(TitleDto titleDto) {
        this.titleDto = titleDto;
    }
}

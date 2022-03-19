package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TitleDto {

    private int id;
    private String name;
    private String author;
    private int releasedYear;
    private List<CopyDto> copyDtos;

    public TitleDto(String name, String author, int releasedYear) {
        this.name = name;
        this.author = author;
        this.releasedYear = releasedYear;
    }

}

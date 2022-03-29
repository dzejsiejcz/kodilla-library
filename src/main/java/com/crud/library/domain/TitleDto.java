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
public class TitleDto {

    private int id;
    private String name;
    private String author;
    private int releasedYear;
    private List<CopyDto> copyDtos;

    public TitleDto(int id, String name, String author, int releasedYear) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.releasedYear = releasedYear;
    }

}

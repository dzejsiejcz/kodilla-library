package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TitleDto {

    private int id;
    private String name;
    private String author;
    private int releasedYear;

    public TitleDto(String name, String author, int releasedYear) {
        this.name = name;
        this.author = author;
        this.releasedYear = releasedYear;
    }
}
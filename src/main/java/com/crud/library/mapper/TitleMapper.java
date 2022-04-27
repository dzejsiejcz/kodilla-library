package com.crud.library.mapper;


import com.crud.library.domain.Title;
import com.crud.library.domain.TitleDto;
import org.springframework.stereotype.Service;

@Service
public class TitleMapper {

    public static Title mapToTitle (final TitleDto titleDto) {
        return new Title(
                titleDto.getName(),
                titleDto.getAuthor(),
                titleDto.getReleasedYear()
        );
    }

    public static TitleDto mapToTitleDto (final Title title) {
        return new TitleDto(
                title.getId(),
                title.getName(),
                title.getAuthor(),
                title.getReleasedYear()
        );
    }
}

package com.crud.library.mapper;


import com.crud.library.domain.Title;
import com.crud.library.domain.TitleDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TitleMapper {

    public Title mapToTitle (final TitleDto titleDto) {
        return new Title(
                titleDto.getName(),
                titleDto.getAuthor(),
                titleDto.getReleasedYear()
        );
    }

    public TitleDto mapToTitleDto (final Title title) {
        return new TitleDto(
                title.getName(),
                title.getAuthor(),
                title.getReleasedYear()
        );
    }

    public List<TitleDto> mapToTitleDtoList(final List<Title> titleList) {
        return titleList.stream()
                .map(this::mapToTitleDto)
                .collect(Collectors.toList());
    }

}

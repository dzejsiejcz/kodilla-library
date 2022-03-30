package com.crud.library.mapper;


import com.crud.library.domain.Title;
import com.crud.library.domain.TitleDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        TitleDto titleDto = new TitleDto(
                title.getName(),
                title.getAuthor(),
                title.getReleasedYear()
        );
        titleDto.setCopyDtos(CopyMapper.mapToCopyDtoList(title.getCopies()));
        return titleDto;
    }

    public static List<TitleDto> mapToTitleDtoList(final List<Title> titleList) {
        return titleList.stream()
                .map(TitleMapper::mapToTitleDto)
                .collect(Collectors.toList());
    }

}

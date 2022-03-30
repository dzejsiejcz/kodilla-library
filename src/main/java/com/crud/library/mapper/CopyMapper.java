package com.crud.library.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.crud.library.domain.Copy;
import com.crud.library.domain.CopyDto;
import com.crud.library.service.DbService;

public class CopyMapper {

    private CopyMapper() {
    }

    public static Copy mapToCopy(final CopyDto copyDto) {
        return new Copy(TitleMapper.mapToTitle(copyDto.getTitleDto()));
    }

    public static CopyDto mapToCopyDto(final Copy copy) {
        CopyDto copyDto = new CopyDto(
                TitleMapper.mapToTitleDto(copy.getTitle()));
        copyDto.setId(copy.getId());
        copyDto.setRentDtos(copy.getRents().stream()
                .map(RentMapper::mapToRentDto)
                .collect(Collectors.toList()));
        return copyDto;
    }

    public static List<CopyDto> mapToCopyDtoList(final List<Copy> copyList) {
        return copyList.stream()
                .map(CopyMapper::mapToCopyDto)
                .collect(Collectors.toList());
    }

}

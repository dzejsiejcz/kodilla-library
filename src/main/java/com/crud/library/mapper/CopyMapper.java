package com.crud.library.mapper;

import com.crud.library.domain.Copy;
import com.crud.library.domain.CopyDto;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CopyMapper {

    public static CopyDto mapToCopyDto(final Copy copy) {
        CopyDto copyDto = new CopyDto(
                TitleMapper.mapToTitleDto(copy.getTitle()));
        copyDto.setId(copy.getId());
        copyDto.setStatus(copy.getStatus());
        return copyDto;
    }

    public static List<CopyDto> mapToCopyDtoList(List<Copy> copies) {
        return copies.stream()
                .map(CopyMapper::mapToCopyDto)
                .collect(Collectors.toList());
    }
}

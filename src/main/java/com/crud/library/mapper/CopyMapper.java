package com.crud.library.mapper;

import com.crud.library.domain.Copy;
import com.crud.library.domain.CopyDto;

public class CopyMapper {

    private CopyMapper() {
    }

    public static CopyDto mapToCopyDto(final Copy copy) {
        CopyDto copyDto = new CopyDto(
                TitleMapper.mapToTitleDto(copy.getTitle()));
        copyDto.setId(copy.getId());
        copyDto.setStatus(copy.getStatus());
        return copyDto;
    }

}

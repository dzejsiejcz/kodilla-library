package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderMapper {

    public static Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getFirstName(),
                readerDto.getLastName(),
                readerDto.getEmail()
        );
    }

    public static ReaderDto mapToReaderDto(final Reader reader) {
        ReaderDto readerDto = new ReaderDto(
                reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getEmail(),
                reader.getCreated()
        );
        readerDto.setRentDtos(RentMapper.mapToRentDtoList(reader.getRents()));
        return readerDto;
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(ReaderMapper::mapToReaderDto)
                .collect(Collectors.toList());
    }


}

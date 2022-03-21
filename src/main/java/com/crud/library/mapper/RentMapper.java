package com.crud.library.mapper;

import com.crud.library.domain.Rent;
import com.crud.library.domain.RentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentMapper {

    public static Rent mapToRent(final RentDto rentDto) {
      return new Rent(
              rentDto.getRented(),
              rentDto.getReturned(),
              rentDto.getReader(),
              rentDto.getCopy()
      );
    }

    public static RentDto mapToRentDto(final Rent rent) {
        return new RentDto(
                rent.getRented(),
                rent.getReturned(),
                rent.getReader(),
                rent.getCopy()
        );
    }

    public static List<RentDto> mapToRentDtoList(List<Rent> rentList) {
        return rentList.stream()
                .map(RentMapper::mapToRentDto)
                .collect(Collectors.toList());
    }

}

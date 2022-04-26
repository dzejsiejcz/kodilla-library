package com.crud.library.mapper;

import com.crud.library.domain.Rent;
import com.crud.library.domain.RentDto;
import org.springframework.stereotype.Service;

@Service
public class RentMapper {

    private RentMapper() {
    }

    public static RentDto mapToRentDto(final Rent rent) {
        return new RentDto(
                rent.getId(),
                rent.getRented(),
                rent.getReturned(),
                CopyMapper.mapToCopyDto(rent.getCopy())
        );
    }

}

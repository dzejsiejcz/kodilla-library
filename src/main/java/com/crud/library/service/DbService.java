package com.crud.library.service;

import com.crud.library.domain.Reader;
import com.crud.library.repositories.CopyRepository;
import com.crud.library.repositories.ReaderRepository;
import com.crud.library.repositories.RentRepository;
import com.crud.library.repositories.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DbService {

    private final CopyRepository copyRepository;
    private final ReaderRepository readerRepository;
    private final RentRepository rentRepository;
    private final TitleRepository titleRepository;


    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }


}

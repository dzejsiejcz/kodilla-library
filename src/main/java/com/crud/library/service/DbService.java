package com.crud.library.service;

import com.crud.library.domain.Copy;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Title;
import com.crud.library.repositories.CopyRepository;
import com.crud.library.repositories.ReaderRepository;
import com.crud.library.repositories.RentRepository;
import com.crud.library.repositories.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public Title findTitleById(final int id) {
        return titleRepository.findById(id);
    }

    public Copy saveCopy(final Copy copy) {
        return copyRepository.save(copy);
    }

    public List<Copy> findAllByTitle(final Title title) {
        return copyRepository.findByTitle(title);
    }

    public Optional<Copy> findCopyById(final int id) {
        return copyRepository.findById(id);
    }




}

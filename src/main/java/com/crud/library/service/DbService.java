package com.crud.library.service;

import com.crud.library.controller.CopyNotFoundException;
import com.crud.library.controller.ReaderNotFoundException;
import com.crud.library.controller.RentNotFoundException;
import com.crud.library.controller.TitleNotFoundException;
import com.crud.library.domain.*;
import com.crud.library.repositories.CopyRepository;
import com.crud.library.repositories.ReaderRepository;
import com.crud.library.repositories.RentRepository;
import com.crud.library.repositories.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Reader findReaderByEmail(final String email) throws ReaderNotFoundException {
        Optional<Reader> reader = Optional.ofNullable(readerRepository.findByEmail(email));
        return reader.orElseThrow(() -> new ReaderNotFoundException(
                "Reader not found for this email: " + email
        ));
    }

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public Title findTitleById(final int id) throws TitleNotFoundException {
        Optional<Title> title = titleRepository.findById(id);
        return title.orElseThrow(() -> new TitleNotFoundException(
                "Title not found for id: " + id
        ));
    }

    public List<Title> findAllTitles(){
        Iterable<Title> all = titleRepository.findAll();
        List<Title> titles = new ArrayList<>();
        all.forEach(titles::add);
        return titles;
    }

    public Rent saveRent(final Rent rent) {
        return rentRepository.save(rent);
    }

    public Rent findRentById(final int id) throws RentNotFoundException {
        Optional<Rent> rent = rentRepository.findById(id);
        return rent.orElseThrow(() -> new RentNotFoundException(
                "Rent not found"
        ));
    }
}

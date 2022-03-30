package com.crud.library.dto;


import com.crud.library.domain.*;
import com.crud.library.repositories.CopyRepository;
import com.crud.library.repositories.ReaderRepository;
import com.crud.library.repositories.RentRepository;
import com.crud.library.repositories.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RentTestSuite {


    @Autowired
    RentRepository rentRepository;
    @Autowired
    CopyRepository copyRepository;
    @Autowired
    TitleRepository titleRepository;
    @Autowired
    ReaderRepository readerRepository;

    @Test
    void testAddNewRentals() {
        //Given
        Reader reader = new Reader("Jan", "Kowalski", "jan@gmail.com");
        List<Title> titleList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Title title = new Title("Title " + i, "Author " + i, (1999 + i));
            titleList.add(title);
        }
        readerRepository.save(reader);
        titleRepository.saveAll(titleList);

        List<Title> resultTitleList = titleRepository.findAll();

        for (Title title : resultTitleList) {
            Copy copy = new Copy(title);
            copyRepository.save(copy);
            Date date = new Date(System.currentTimeMillis());
            Rent rent = new Rent(reader, copy);
            rentRepository.save(rent);
            int id = copy.getId();
            copyRepository.findById(id).get().setStatus(Status.BORROWED);
        }

        for (Title title : resultTitleList) {
            copyRepository.save(new Copy(title));
        }

        //When
        List<Copy> resultList = copyRepository.findByStatus(Status.BORROWED);

        //Then
        assertEquals(10, resultList.size());

        //CleanUp
        rentRepository.deleteAll();

        copyRepository.deleteAll();
        titleRepository.deleteAll();
        readerRepository.deleteAll();


    }


}

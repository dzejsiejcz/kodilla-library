package com.crud.library.dao;


import com.crud.library.domain.*;
import com.crud.library.repositories.CopyRepository;
import com.crud.library.repositories.ReaderRepository;
import com.crud.library.repositories.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReaderTestSuite {

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    void testReaderDaoSave() {
        //Given
        Reader reader = new Reader("Jan", "Kowalski", "jan@gmail.com", new Date(System.currentTimeMillis()));

        //When
        readerRepository.save(reader);
        //Then

        int id = reader.getId();
        Optional<Reader> readReader = readerRepository.findById(id);
        assertTrue(readReader.isPresent());

        //CleanUp
        readerRepository.deleteById(id);
    }
}

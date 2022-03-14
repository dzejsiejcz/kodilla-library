package com.crud.library.dao;


import com.crud.library.domain.*;
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
public class LibraryTestSuite {

    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private HireRepository hireRepository;
    @Autowired
    private CopyRepository copyRepository;
    @Autowired
    private TitleRepository titleRepository;
    public static final String DESCRIPTION = "Test: Library Application with DB";


//    @Test
//    void testReaderDaoSave() {
//        //Given
//        Reader reader = new Reader("Jan", "Kowalski", "jan@gmail.com", new Date(System.currentTimeMillis()));
//
//        //When
//        readerRepository.save(reader);
//        //Then
//
//        int id = reader.getId();
//        Optional<Reader> readReader = readerRepository.findById(id);
//        assertTrue(readReader.isPresent());
//
//        //CleanUp
//        //readerDao.deleteById(id);
//    }

    @Test
    void testAddNewBooks() {
        //Given
        List<Title> titleList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Title title = new Title("Title " + i, "Author " + i, (1999+i));
            titleList.add(title);
        }

        //titleRepository.saveAll(titleList);

        List<Title> resultTitleList = titleRepository.findAll();
        System.out.println("ROZMIAR TABLICY TITLes " + resultTitleList.size());
        for (Title title : resultTitleList) {
            title.getCopies().add(new Copy(title, Status.AVAILABLE));
            //copyRepository.save(new Copy(title, Status.LOST));
        }

        for (Title title : resultTitleList) {
            title.getCopies().add(new Copy(title, Status.BORROWED));
            //copyRepository.save(new Copy(title, Status.BORROWED));
        }

        titleRepository.saveAll(titleList);



        //When
        List<Copy> resultList = copyRepository.findByStatus(Status.BORROWED);



        //Then
        assertEquals(10, resultList.size());



        //CleanUp

        //copyRepository.deleteAll();
        //titleRepository.deleteAll();



    }



}

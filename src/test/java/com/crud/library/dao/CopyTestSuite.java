package com.crud.library.dao;

import com.crud.library.domain.Copy;
import com.crud.library.domain.Status;
import com.crud.library.domain.Title;
import com.crud.library.repositories.CopyRepository;
import com.crud.library.repositories.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CopyTestSuite {

    @Autowired
    TitleRepository titleRepository;
    @Autowired
    CopyRepository copyRepository;


    @Test
    void testAddNewBooks() {
        //Given
        List<Title> titleList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Title title = new Title("Title " + i, "Author " + i, (1999+i));
            titleList.add(title);
        }

        titleRepository.saveAll(titleList);

        List<Title> resultTitleList = titleRepository.findAll();
        System.out.println("ROZMIAR TABLICY TITLes " + resultTitleList.size());
        for (Title title : resultTitleList) {
            copyRepository.save(new Copy(title, Status.LOST));
        }

        for (Title title : resultTitleList) {
            copyRepository.save(new Copy(title, Status.BORROWED));
        }

        //When
        List<Copy> resultList = copyRepository.findByStatus(Status.BORROWED);

        //Then
        assertEquals(10, resultList.size());

        //CleanUp
        titleRepository.deleteAll();
        copyRepository.deleteAll();

    }
}

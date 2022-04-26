package com.crud.library.dto;


import com.crud.library.domain.Title;
import com.crud.library.repositories.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TitleTestSuite {


    @Autowired
    private TitleRepository titleRepository;


    @Test
    void getTitles() {
        //Given
        List<Title> titleList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Title title = new Title("Title " + i, "Author " + i, (1999 + i));
            titleList.add(title);
        }

        titleRepository.saveAll(titleList);

        //When
        String result = titleRepository.findByName("Title 1").getName();

        //Then
        assertEquals("Title 1", result);

        //CleanUp

        titleRepository.deleteAll();
    }


}

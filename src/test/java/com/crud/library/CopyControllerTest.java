package com.crud.library;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.crud.library.controller.CopyController;
import com.crud.library.domain.Copy;
import com.crud.library.domain.CopyDto;
import com.crud.library.domain.Title;
import com.crud.library.repositories.CopyRepository;
import com.crud.library.repositories.TitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CopyControllerTest {

    @Autowired
    private CopyController copyController;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Test
    void getCopy() {
        //Given
        List<Copy> copies = prepareTitlesAndCopies();

        //When
        CopyDto copyResult = copyController.getCopy(copies.get(0).getId());

        //Then
        assertNotNull(copyResult);
    }

    private List<Copy> prepareTitlesAndCopies() {
        List<Title> titleList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Title title = new Title("Title " + i, "Author " + i, (1999 + i));
            titleList.add(title);
        }

        titleRepository.saveAll(titleList);

        List<Title> resultTitleList = titleRepository.findAll();
        List<Copy> copies = new ArrayList<>();
        System.out.println("ROZMIAR TABLICY TITLes " + resultTitleList.size());
        for (Title title : resultTitleList) {
            Copy copy = new Copy(title);
            copyRepository.save(copy);
            copies.add(copy);
        }
        return copies;
    }
}
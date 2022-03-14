package com.crud.library.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.crud.library.domain.Copy;
import com.crud.library.domain.Status;
import com.crud.library.domain.Title;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CopyRepositoryTest {

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Test
    void findByStatus() {
        //Given
        Title title = Title.builder()
                .name("name")
                .author("author")
                .releasedYear(1999)
                .build();
        titleRepository.save(title);

        Copy copy = Copy.builder()
                .title(title)
                .status(Status.AVAILABLE)
                .build();
        copyRepository.save(copy);

        //When
        List<Copy> byStatus = copyRepository.findByStatus(Status.AVAILABLE);

        //Then
        assertEquals(1, byStatus.size());

        //Clean up
        copyRepository.deleteAll();
        titleRepository.deleteAll();

    }
}
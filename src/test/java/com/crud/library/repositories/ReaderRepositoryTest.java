package com.crud.library.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.crud.library.domain.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReaderRepositoryTest {

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    void findByEmail() {
        //Given
        String email = "test-email";
        Reader reader = Reader.builder()
                .created(Instant.now())
                .email(email)
                .firstName("test-firstname")
                .lastName("test-lastname")
                .build();
        readerRepository.save(reader);

        //When
        Optional<Reader> byEmail = readerRepository.findByEmail(email);

        //Then
        assertTrue(byEmail.isPresent());
        Reader readerFromDb = byEmail.get();
        assertEquals(reader.getEmail(), readerFromDb.getEmail());
        assertEquals(reader.getCreated(), readerFromDb.getCreated());
        assertEquals(reader.getFirstName(), readerFromDb.getFirstName());
        assertEquals(reader.getLastName(), readerFromDb.getLastName());

        //Clean up
        readerRepository.deleteAll();
    }
}
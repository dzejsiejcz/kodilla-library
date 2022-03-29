package com.crud.library.controller;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import com.crud.library.mapper.CopyMapper;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.mapper.RentMapper;
import com.crud.library.mapper.TitleMapper;
import com.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.crud.library.mapper.ReaderMapper.mapToReader;

@CrossOrigin("*")
@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {

        private final DbService dbService;
//        private final CopyMapper copyMapper;
//        private final ReaderMapper readerMapper;
//        private final RentMapper rentMapper;
//        private final TitleMapper titleMapper;

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> createReader(@RequestBody ReaderDto readerDto) {
                Reader reader = mapToReader(readerDto);
                dbService.saveReader(reader);
                return ResponseEntity.ok().build();
        }





}

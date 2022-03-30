package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.crud.library.mapper.CopyMapper.mapToCopyDto;
import static com.crud.library.mapper.ReaderMapper.mapToReader;
import static com.crud.library.mapper.TitleMapper.mapToTitle;

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

        @PostMapping(value = "/create-user", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> createReader(@RequestBody ReaderDto readerDto) {
                Reader reader = mapToReader(readerDto);
                dbService.saveReader(reader);
                return ResponseEntity.ok().build();
        }

        @PostMapping(value ="/create-title", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> createTitle(@RequestBody TitleDto titleDto) {
                Title title = mapToTitle(titleDto);
                dbService.saveTitle(title);
                return ResponseEntity.ok().build();
        }

        @PostMapping(value = "/{titleId}/create-copy", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> createCopy(@RequestBody Integer quantity, @PathVariable int titleId) {
                Title title = dbService.findTitleById(titleId);
                for (int i = 0; i< quantity; i++) {
                        dbService.saveCopy(new Copy(title));
                }
                return ResponseEntity.ok().build();
        }

        @PutMapping(value = "/{titleId}/{copyId}/set-status", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CopyDto> modifyCopyStatus(@RequestBody Status status, @PathVariable int copyId) {
                Optional<Copy> copy = dbService.findCopyById(copyId);
                if (copy.isPresent()) {
                        Copy copyPresent = copy.get();
                        copyPresent.setStatus(status);
                        dbService.saveCopy(copyPresent);
                        return ResponseEntity.ok(mapToCopyDto(copyPresent));
                }
                return (ResponseEntity<CopyDto>) ResponseEntity.notFound();
        }

       // @GetMapping(value = "/{titleId}", co)





}

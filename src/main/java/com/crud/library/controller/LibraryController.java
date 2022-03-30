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
import static com.crud.library.mapper.RentMapper.mapToRentDto;
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
        public ResponseEntity<Void> createCopy(@RequestBody Integer quantity, @PathVariable int titleId) throws TitleNotFoundException {
                Title title = dbService.findTitleById(titleId);
                for (int i = 0; i< quantity; i++) {
                        dbService.saveCopy(new Copy(title));
                }
                return ResponseEntity.ok().build();
        }

        //nie działa
        @PutMapping(value = "/{titleId}/{copyId}/set-status", consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<CopyDto> modifyCopyStatus(@RequestBody Status status, @PathVariable int copyId) throws TitleNotFoundException, CopyNotFoundException {
                Optional<Copy> copyFound = dbService.findCopyById(copyId);
                Copy copy = copyFound.get();
                copy.setStatus(status);
                dbService.saveCopy(copy);
                return ResponseEntity.ok(mapToCopyDto(copy));
        }

        // nie działa title not found
               @GetMapping(value = "/{titleId}/get-available")
        public ResponseEntity<Integer> getTitleQuantity(@PathVariable int titleId) throws TitleNotFoundException {
                Title title = dbService.findTitleById(titleId);
                int availableQuantity = (int) title.getCopies().stream()
                        .filter(copy -> copy.getStatus().equals(Status.AVAILABLE))
                        .count();
                return ResponseEntity.ok(availableQuantity);
       }

        @GetMapping(value = "/{titleId}/rent")
        public ResponseEntity<RentDto> rentCopy(@RequestBody ReaderDto readerDto, @PathVariable int titleId) throws TitleNotFoundException {
                Title title = dbService.findTitleById(titleId);
                Optional<Copy> availableCopy = title.getCopies().stream()
                        .filter(copy -> copy.getStatus().equals(Status.AVAILABLE))
                        .findAny();
                if (availableCopy.isPresent()) {
                        Rent rent = new Rent(mapToReader(readerDto),availableCopy.get());
                        dbService.saveRent(rent);
                        int id = availableCopy.get().getId();
                        dbService.setCopyStatus(id, Status.BORROWED);
                        return ResponseEntity.ok(mapToRentDto(rent));
                }
                return (ResponseEntity<RentDto>) ResponseEntity.badRequest();
        }





}

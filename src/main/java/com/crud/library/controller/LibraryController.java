package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
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

    @PostMapping(value = "/create-user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReader(@RequestBody ReaderDto readerDto) {
        Reader reader = mapToReader(readerDto);
        dbService.saveReader(reader);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/create-title", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTitle(@RequestBody TitleDto titleDto) {
        Title title = mapToTitle(titleDto);
        dbService.saveTitle(title);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{titleId}/create-copy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCopy(@RequestBody Integer quantity, @PathVariable int titleId) throws TitleNotFoundException {
        Title title = dbService.findTitleById(titleId);
        for (int i = 0; i < quantity; i++) {
            dbService.saveCopy(new Copy(title));
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping(value = "/{titleId}/{copyId}/set-status")
    public ResponseEntity<CopyDto> modifyCopyStatus(@RequestParam("status") String status, @PathVariable int titleId, @PathVariable int copyId) throws CopyNotFoundException, TitleNotFoundException {
        dbService.findTitleById(titleId);
        Copy copy = dbService.findCopyById(copyId);
        Status statusFound = Status.searchStatus(status);
        if (statusFound != null) {
            copy.setStatus(statusFound);
            dbService.saveCopy(copy);
            return ResponseEntity.ok(mapToCopyDto(copy));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{titleId}/get-available")
    public ResponseEntity<TitleQuantityDto> getTitleQuantity(@PathVariable int titleId) throws TitleNotFoundException {
        Title title = dbService.findTitleById(titleId);
        int availableQuantity = (int) title.getCopies().stream()
                .filter(copy -> copy.getStatus().equals(Status.AVAILABLE))
                .count();
        return ResponseEntity.ok(new TitleQuantityDto(availableQuantity, title));
    }

    @Transactional
    @GetMapping(value = "/{titleId}/rent")
    public ResponseEntity<RentDto> rentCopy(@RequestBody ReaderDto readerDto, @PathVariable int titleId) throws TitleNotFoundException, ReaderNotFoundException {
        Title title = dbService.findTitleById(titleId);
        Optional<Copy> availableCopy = title.getCopies().stream()
                .filter(copy -> copy.getStatus().equals(Status.AVAILABLE))
                .findAny();
        Reader reader = dbService.findReaderByEmail(readerDto.getEmail());
        if (availableCopy.isPresent()) {
            Rent rent = new Rent(reader, availableCopy.get());
            Rent savedRent = dbService.saveRent(rent);
            availableCopy.get().setStatus(Status.BORROWED);
            return ResponseEntity.ok(mapToRentDto(savedRent));
        }
        return ResponseEntity.badRequest().build();
    }

    @Transactional
    @PutMapping(value = "return/{rentId}")
    public ResponseEntity<CopyDto> returnCopy(@RequestParam("status") String status, @RequestBody ReaderDto readerDto, @PathVariable int rentId) throws CopyNotFoundException, RentNotFoundException {
        Rent rent = dbService.findRentById(rentId);

        if (rent.getReader().getEmail().equals(readerDto.getEmail())) {
            Copy copy = dbService.findCopyById(rent.getCopy().getId());
            Status statusFound = Status.searchStatus(status);
            if (statusFound != null) {
                copy.setStatus(statusFound);
            } else {
                return ResponseEntity.badRequest().build();
            }
            rent.setReturned(new Date());
            return ResponseEntity.ok(mapToCopyDto(copy));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

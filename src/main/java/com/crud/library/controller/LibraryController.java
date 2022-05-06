package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.service.CopyService;
import com.crud.library.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import static com.crud.library.mapper.CopyMapper.mapToCopyDto;
import static com.crud.library.mapper.ReaderMapper.mapToReader;
import static com.crud.library.mapper.RentMapper.mapToRentDto;
import static com.crud.library.mapper.TitleMapper.mapToTitle;
import static com.crud.library.mapper.TitleMapper.mapToTitleDtoList;

@CrossOrigin("*")
@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {

    private final DbService dbService;
    private final CopyService copyService;

    @PostMapping(value = "/create-user")
    public ResponseEntity<Void> createReader(@RequestBody ReaderDto readerDto) {
        Reader reader = mapToReader(readerDto);
        dbService.saveReader(reader);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TitleDto>> getAllTitles(){
        List<Title> titles = dbService.findAllTitles();
        return ResponseEntity.ok(mapToTitleDtoList(titles));
    }

    @PostMapping(value = "/create-title")
    public ResponseEntity<Void> createTitle(@RequestBody TitleDto titleDto) {
        Title title = mapToTitle(titleDto);
        dbService.saveTitle(title);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{titleId}/get-available")
    public ResponseEntity<Integer> getTitleQuantity(@PathVariable int titleId) {
        Title title = dbService.findTitleById(titleId);
        Integer availableQuantity = (int) title.getCopies().stream()
                .filter(copy -> copy.getStatus().equals(Status.AVAILABLE))
                .count();
        return ResponseEntity.ok(availableQuantity);
    }

    @Transactional
    @GetMapping(value = "/{titleId}/rent")
    public ResponseEntity<RentDto> rentCopy(@RequestBody ReaderDto readerDto, @PathVariable int titleId) {
        Optional<Copy> availableCopy = copyService.getAllAvailableCopies(titleId).stream()
                .findAny();
        Reader reader = dbService.findReaderByEmail(readerDto.getEmail());
        if (availableCopy.isPresent()) {
            Rent rent = new Rent(reader, availableCopy.get());
            Rent savedRent = dbService.saveRent(rent);
            availableCopy.get().setStatus(Status.BORROWED);
            return ResponseEntity.ok(mapToRentDto(savedRent));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @PutMapping(value = "return/{rentId}")
    public ResponseEntity<CopyDto> returnCopy(@RequestBody ReaderDto readerDto,
                                              @PathVariable int rentId) {

        Rent rent = dbService.findRentById(rentId);

        if (rent.getReader().getEmail().equals(readerDto.getEmail())) {
            Copy copy = copyService.findCopyById(rent.getCopy().getId());
            copy.setStatus(Status.AVAILABLE);
            rent.setReturned(new Date());
            return ResponseEntity.ok(mapToCopyDto(copy));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    @PutMapping(value = "lost/{rentId}")
    public ResponseEntity<CopyDto> lostCopy(@RequestBody ReaderDto readerDto,
                                              @PathVariable int rentId) {
        Rent rent = dbService.findRentById(rentId);
        if (rent.getReader().getEmail().equals(readerDto.getEmail())) {
            Copy copy = copyService.findCopyById(rent.getCopy().getId());
            copy.setStatus(Status.LOST);
            return ResponseEntity.ok(mapToCopyDto(copy));
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}

package com.crud.library.controller;

import com.crud.library.domain.Status;
import com.crud.library.domain.Title;
import com.crud.library.service.CopyService;
import com.crud.library.domain.Copy;
import com.crud.library.domain.CopyDto;
import com.crud.library.mapper.CopyMapper;
import com.crud.library.service.DbService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.crud.library.domain.Status.searchStatus;
import static com.crud.library.mapper.CopyMapper.mapToCopyDto;

@RestController
@AllArgsConstructor
@RequestMapping("/copy")
public class CopyController {

    private final CopyService copyService;
    private final DbService dbService;

    @GetMapping("/{id}")
    public CopyDto getCopy(@PathVariable int id) throws CopyNotFoundException {
        Copy copy = copyService.findCopyById(id);
        return CopyMapper.mapToCopyDto(copy);
    }

    @GetMapping(value = "/{titleId}/get-copies")
    public ResponseEntity<List<CopyDto>> getAllCopies(@PathVariable int titleId) throws TitleNotFoundException {
        Title title = dbService.findTitleById(titleId);
        List<Copy> copies = copyService.findAllCopiesByTitle(title).stream()
                .filter(copy -> copy.getStatus().equals(Status.AVAILABLE))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CopyMapper.mapToCopyDtoList(copies));
    }

    @PostMapping(value = "/{titleId}/create-copy", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCopy(@RequestBody Integer quantity, @PathVariable int titleId) throws TitleNotFoundException {
        Title title = dbService.findTitleById(titleId);
        for (int i = 0; i < quantity; i++) {
            copyService.saveCopy(new Copy(title));
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping(value = "/{titleId}/{copyId}/set-status")
    public ResponseEntity<CopyDto> modifyCopyStatus(@RequestParam("status") Status status, @PathVariable int titleId, @PathVariable int copyId) throws CopyNotFoundException {
        Copy copy = copyService.findCopyById(copyId);
        Status statusFound = searchStatus(status);
        if (statusFound != null) {
            copy.setStatus(statusFound);
            copyService.saveCopy(copy);
            return ResponseEntity.ok(mapToCopyDto(copy));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

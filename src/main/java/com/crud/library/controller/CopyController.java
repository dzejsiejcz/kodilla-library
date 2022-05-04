package com.crud.library.controller;

import static com.crud.library.mapper.CopyMapper.mapToCopyDto;

import java.util.List;
import javax.transaction.Transactional;

import com.crud.library.domain.Copy;
import com.crud.library.domain.CopyDto;
import com.crud.library.domain.Status;
import com.crud.library.domain.Title;
import com.crud.library.mapper.CopyMapper;
import com.crud.library.service.CopyService;
import com.crud.library.service.DbService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/copy")
@Log4j2
public class CopyController {

    private final CopyService copyService;
    private final DbService dbService;

    public CopyController(CopyService copyService, DbService dbService) {
        this.copyService = copyService;
        this.dbService = dbService;
        Copy copy = new Copy();
        copy.setRents(List.of());
        copy.setStatus(Status.BORROWED);
        copyService.saveCopy(copy);
        log.info("\n\n\n {} \n\n\n", copy);
    }

    @GetMapping("/{id}")
    public CopyDto getCopy(@PathVariable int id) throws CopyNotFoundException {
        Copy copy = copyService.findCopyById(id);
        return CopyMapper.mapToCopyDto(copy);
    }

    @GetMapping(value = "/{titleId}/get-copies")
    public ResponseEntity<List<CopyDto>> getAllCopies(@PathVariable int titleId) {
        log.info("Get all copies {}", titleId);

        List<Copy> copies = copyService.getAllAvailableCopies(titleId);

        return ResponseEntity.ok(CopyMapper.mapToCopyDtoList(copies));
    }

    @PostMapping(value = "/{titleId}/{quantity}/create-copy")
    public ResponseEntity<Void> createCopies(@PathVariable int quantity, @PathVariable int titleId) {
        log.info("Create copies {}", titleId);

        Title title = dbService.findTitleById(titleId);
        for (int i = 0; i < quantity; i++) {
            copyService.saveCopy(new Copy(title));
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping(value = "/{status}/{copyId}/set-status")
    public ResponseEntity<CopyDto> modifyCopyStatus(@PathVariable Status status,
                                                    @PathVariable int copyId) {
        Copy copy = copyService.findCopyById(copyId);
        copy.setStatus(status);
        copyService.saveCopy(copy);
        return ResponseEntity.ok(mapToCopyDto(copy));
    }

}

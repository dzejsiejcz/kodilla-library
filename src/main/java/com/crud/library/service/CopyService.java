package com.crud.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.crud.library.controller.CopyNotFoundException;
import com.crud.library.controller.TitleNotFoundException;
import com.crud.library.domain.Copy;
import com.crud.library.domain.Status;
import com.crud.library.domain.Title;
import com.crud.library.repositories.CopyRepository;
import com.crud.library.repositories.TitleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CopyService {

    private CopyRepository copyRepository;
    private TitleRepository titleRepository;

    public Copy saveCopy(final Copy copy) {
        return copyRepository.save(copy);
    }

    public Copy findCopyById(final int id) throws CopyNotFoundException {
        Optional<Copy> copy = copyRepository.findById(id);
        return copy.orElseThrow(() -> new CopyNotFoundException(
                "Copy not found for id: " + id
        ));
    }

    public List<Copy> findAllCopiesByTitle(Title title) {
        Iterable<Copy> all = copyRepository.findAll();

        List<Copy> copies = new ArrayList<>();
        for (Copy copy : all) {
            if (copy.getTitle().equals(title)) {
                copies.add(copy);
            }
        }
        return copies;
    }

    public List<Copy> getAllAvailableCopies(int titleId) {
        Optional<Title> titleOpt = titleRepository.findById(titleId);
        if (titleOpt.isEmpty()) {
            throw new TitleNotFoundException("todo");
        }

        return copyRepository.findByTitleAndStatus(titleOpt.get(), Status.AVAILABLE);
    }
}

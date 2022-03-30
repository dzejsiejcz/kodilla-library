package com.crud.library;

import com.crud.library.domain.Copy;
import com.crud.library.repositories.CopyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CopyService {

    private CopyRepository copyRepository;

    public Copy findById(int id) {
        return copyRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("blah blah"));
    }
}

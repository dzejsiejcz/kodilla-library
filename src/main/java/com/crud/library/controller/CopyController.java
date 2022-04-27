package com.crud.library.controller;

import com.crud.library.service.CopyService;
import com.crud.library.domain.Copy;
import com.crud.library.domain.CopyDto;
import com.crud.library.mapper.CopyMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CopyController {

    private final CopyService copyService;

    @GetMapping("copy/{id}")
    public CopyDto getCopy(@PathVariable int id){
        Copy copy = copyService.findById(id);
        return CopyMapper.mapToCopyDto(copy);
    }



}

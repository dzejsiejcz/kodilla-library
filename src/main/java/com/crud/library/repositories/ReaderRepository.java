package com.crud.library.repositories;

import com.crud.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ReaderRepository extends CrudRepository<Reader, Integer> {
    Reader findByEmail(String email);
}

package com.crud.library.repositories;

import java.util.Optional;

import com.crud.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ReaderRepository extends CrudRepository<Reader, Integer> {

    Optional<Reader> findByEmail(String email);
}

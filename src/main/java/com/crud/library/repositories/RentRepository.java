package com.crud.library.repositories;

import com.crud.library.domain.Rent;
import com.crud.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface RentRepository extends CrudRepository<Rent, Integer> {

    List<Rent> findByReader(Reader reader);

}

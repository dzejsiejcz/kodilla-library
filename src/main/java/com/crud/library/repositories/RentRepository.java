package com.crud.library.repositories;

import com.crud.library.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface RentRepository extends CrudRepository<Rent, Integer> {
}

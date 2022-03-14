package com.crud.library.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface HireRepository extends CrudRepository<Hire, Integer> {

    List<Hire> findByReader(Reader reader);

}

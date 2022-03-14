package com.crud.library.repositories;

import com.crud.library.domain.Hire;
import com.crud.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface HireRepository extends CrudRepository<Hire, Integer> {

    List<Hire> findByReader(Reader reader);

}

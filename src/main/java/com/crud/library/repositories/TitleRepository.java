package com.crud.library.repositories;

import com.crud.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TitleRepository extends CrudRepository<Title, Integer> {
    Title findByName(String name);
    List<Title> findAll();
}

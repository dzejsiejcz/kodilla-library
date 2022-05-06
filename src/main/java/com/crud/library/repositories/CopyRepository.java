package com.crud.library.repositories;

import com.crud.library.domain.Copy;
import com.crud.library.domain.Status;
import com.crud.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CopyRepository extends CrudRepository<Copy, Integer> {
    List<Copy> findByStatus(Status status);

    List<Copy> findByTitleAndStatus(Title title, Status status);
}

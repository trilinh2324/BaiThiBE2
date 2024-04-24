package com.baithi.Repository;

import com.baithi.model.Personnel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonnelRepository extends CrudRepository<Personnel, Integer> {
}

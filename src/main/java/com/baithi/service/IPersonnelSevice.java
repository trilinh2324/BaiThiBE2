package com.baithi.service;

import com.baithi.model.Personnel;

import java.util.Optional;

public interface IPersonnelSevice {
    Iterable<Personnel> findAll();

    Personnel save(Personnel personnel);

    Optional<Personnel> findById(int id);

    void remove(int id);


}

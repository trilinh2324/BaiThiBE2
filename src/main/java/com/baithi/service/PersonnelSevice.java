package com.baithi.service;

import com.baithi.Repository.IPersonnelRepository;
import com.baithi.model.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonnelSevice implements IPersonnelSevice {
    @Autowired
    private IPersonnelRepository iPersonnelRepository;

    @Override
    public Iterable<Personnel> findAll() {
        return iPersonnelRepository.findAll();
    }

    @Override
    public Personnel save(Personnel personnel) {
        return iPersonnelRepository.save(personnel);
    }

    @Override
    public Optional<Personnel> findById(int id) {
        return iPersonnelRepository.findById(id);
    }

    @Override
    public void remove(int id) {
        iPersonnelRepository.deleteById(id);
    }
}

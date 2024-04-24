package com.baithi.controller.api;

import com.baithi.model.Personnel;
import com.baithi.service.IPersonnelSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/personnel")
public class PersonnelAPI {
    @Autowired
    private IPersonnelSevice iPersonnelSevice;

    @GetMapping
    public ResponseEntity<Page<Personnel>> findAllPersonnel() {
        Page<Personnel> customers = (Page<Personnel>) iPersonnelSevice.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personnel> findPersonnelById(@PathVariable int id) {
        Optional<Personnel> customerOptional = iPersonnelSevice.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Personnel> savePersonnel(@RequestBody Personnel personnel) {
        return new ResponseEntity<>(iPersonnelSevice.save(personnel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personnel> updatePersonnel(@PathVariable int id, @RequestBody Personnel personnel) {
        Optional<Personnel> customerOptional = iPersonnelSevice.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        personnel.setId(customerOptional.get().getId());
        return new ResponseEntity<>(iPersonnelSevice.save(personnel), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Personnel> deletePersonnel(@PathVariable int id) {
        Optional<Personnel> customerOptional = iPersonnelSevice.findById(id);
        if (!customerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iPersonnelSevice.remove(id);
        return new ResponseEntity<>(customerOptional.get(), HttpStatus.OK);
    }
}

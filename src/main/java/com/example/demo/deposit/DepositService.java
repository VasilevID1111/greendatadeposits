package com.example.demo.deposit;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DepositService {

    @Autowired
    private DepositRepo depositRepo;
    public List<DepositObject> findAll() {
        Iterable<DepositObject> iterable = depositRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<DepositObject> findById(Integer id) {
        return depositRepo.findById(id);
    }

    public DepositObject save(DepositObject deposit) {
        return depositRepo.save(deposit);
    }

    public boolean existsById(Integer id) {
        return depositRepo.existsById(id);
    }

    public void deleteById(Integer id) {
        if (existsById(id)) depositRepo.deleteById(id);
    }
}
package com.example.demo.bank;

import com.example.demo.deposit.DepositObject;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class BankService {
    @Autowired
    private BankRepo bankRepo;
    public List<BankObject> findAll() {
        Iterable<BankObject> iterable = bankRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<BankObject> findById(Integer id) {
        return bankRepo.findById(id);
    }

    public BankObject save(BankObject bank) {
        return bankRepo.save(bank);
    }

    public boolean existsById(Integer id) {
        return bankRepo.existsById(id);
    }

    public void deleteById(Integer id) {
        if (existsById(id))
            bankRepo.deleteById(id);
    }
}

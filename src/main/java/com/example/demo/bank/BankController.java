package com.example.demo.bank;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/banks")
@AllArgsConstructor
public class BankController {
    @Autowired
    private final BankService bankService;

    @GetMapping
    public Iterable<BankObject> getAllBanks() {
        return bankService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankObject> getBankById(@PathVariable Integer id) {
        Optional<BankObject> BanksOptional = bankService.findById(id);
        return BanksOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BankObject> createBank(@RequestBody BankObject bank) {
        BankObject savedBank = bankService.save(bank);
        return ResponseEntity.created(URI.create("/banks/" + savedBank.getId())).body(savedBank);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankObject> updateBank(@PathVariable Integer id, @RequestBody BankObject bank) {
        if (!bankService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bank.setId(id);
        BankObject updatedBank = bankService.save(bank);
        return ResponseEntity.ok(updatedBank);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable Integer id) {
        if (!bankService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bankService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

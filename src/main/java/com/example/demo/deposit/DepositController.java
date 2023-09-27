package com.example.demo.deposit;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/deposits")
@AllArgsConstructor
public class DepositController {
    @Autowired
    private final DepositService depositService;

    @GetMapping
    public Iterable<DepositObject> getAllDeposits() {
        return depositService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepositObject> getDepositById(@PathVariable Integer id) {
        Optional<DepositObject> depositOptional = depositService.findById(id);
        return depositOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DepositObject> createDeposit(@RequestBody DepositObject deposit) {
        DepositObject saveDeposit = depositService.save(deposit);
        return ResponseEntity.created(URI.create("/deposits/" + saveDeposit.getId())).body(saveDeposit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepositObject> updateDeposit(@PathVariable Integer id, @RequestBody DepositObject deposit) {
        if (!depositService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        deposit.setId(id);
        DepositObject updatedDeposit = depositService.save(deposit);
        return ResponseEntity.ok(updatedDeposit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeposit(@PathVariable Integer id) {
        if (!depositService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        depositService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

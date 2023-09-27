package com.example.demo.сlient;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    @Autowired
    private final ClientService clientService;

    @GetMapping
    public Iterable<ClientObject> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientObject> getClientById(@PathVariable Integer id) {
        Optional<ClientObject> clientOptional = clientService.findById(id);
        return clientOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClientObject> createClient(@RequestBody ClientObject client) {
        ClientObject savedClient = clientService.save(client);
        return ResponseEntity.created(URI.create("/clients/" + savedClient.getId())).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientObject> updateClient(@PathVariable Integer id, @RequestBody ClientObject client) {
        if (!clientService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        client.setId(id);
        ClientObject updatedClient = clientService.save(client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        if (!clientService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

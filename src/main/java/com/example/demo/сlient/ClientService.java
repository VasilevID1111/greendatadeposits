package com.example.demo.сlient;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;
    public List<ClientObject> findAll() {
        Iterable<ClientObject> iterable = clientRepo.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<ClientObject> findById(Integer id) {
        return clientRepo.findById(id);
    }

    public ClientObject save(ClientObject client) {
        return clientRepo.save(client);
    }

    public boolean existsById(Integer id) {
        return clientRepo.existsById(id);
    }

    public void deleteById(Integer id) {
        if (existsById(id)) clientRepo.deleteById(id);
    }
}

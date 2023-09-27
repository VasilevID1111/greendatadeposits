package com.example.demo.сlient;

import com.example.demo.сlient.ClientObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends CrudRepository<ClientObject, Integer> {
}

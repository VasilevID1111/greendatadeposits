package com.example.demo.deposit;

import com.example.demo.сlient.ClientObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepo extends CrudRepository<DepositObject, Integer> {
}


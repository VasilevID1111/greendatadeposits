package com.example.demo.сlient;

import com.example.demo.deposit.DepositObject;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Table(name = "clients")
@Data
public class ClientObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String short_name;
    @Column(name = "address")
    private String address;
    @Column(name = "legal_form")
    private String legal_form;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY,
            mappedBy = "client")
    @JsonManagedReference
    private List<DepositObject> deposits;

    public ClientObject (Integer id, String name, String short_name, String address, String legal_form) {
        this.id = id;
        this.name = name;
        this.short_name = short_name;
        this.address = address;
        this.legal_form = legal_form;
    }
    public ClientObject() {}
}

package com.example.demo.bank;

import com.example.demo.deposit.DepositObject;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "banks")
@Data
public class BankObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "bik")
    private String bik;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY,
            mappedBy = "bank")
    @JsonManagedReference
    private List<DepositObject> deposits;

    public BankObject(Integer id, String name, String bik) {
        this.id = id;
        this.name = name;
        this.bik = bik;
    }

    public BankObject() {}
}

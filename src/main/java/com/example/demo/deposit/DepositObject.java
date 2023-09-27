package com.example.demo.deposit;

import com.example.demo.bank.BankObject;
import com.example.demo.сlient.ClientObject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "deposits")
@Data
public class DepositObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_of_created")
    private Date dateOfCreated;
    @Column(name = "percent")
    private Integer percent;
    @Column(name = "term_in_months")
    private Integer term_in_months;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    @JsonBackReference
    private BankObject bank;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private ClientObject client;

    public DepositObject(Integer id, Date date, Integer percent, Integer term_in_months, BankObject bank, ClientObject client) {
        this.id = id;
        this.dateOfCreated = date;
        this.percent = percent;
        this.term_in_months = term_in_months;
        this.bank = bank;
        this.client = client;
    }
    public DepositObject() {}

}
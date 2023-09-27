package com.example.demo.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
public class BankServiceTest {
    private BankObject bank1, bank2;
    @InjectMocks
    private BankService bankService;
    @Mock
    private BankRepo bankRepo;

    @BeforeEach
    void setUp(){
        bank1 = new BankObject(1, "Moskva","1110223");
        bank2 = new BankObject(2, "Sber","332");
    }
    @Test
    void findAll_shouldFindAllBanks_whenAddedTwoBanks() {
        Mockito.when(bankRepo.findAll()).thenReturn(List.of(bank1,bank2));

        List<BankObject> banks = bankService.findAll();

        assertEquals(2, banks.size());
        assertTrue(banks.contains(bank1));
        assertTrue(banks.contains(bank2));
    }

    @Test
    void findById_shouldFindFirstBank_whenAddedTwoBanks() {
        Mockito.when(bankRepo.findById(1)).thenReturn(Optional.ofNullable(bank1));

        Optional<BankObject> bank = bankService.findById(1);

        assertEquals(bank1, bank.get());
    }

    @Test
    void save_shouldSaveBankObject() {
        Mockito.when(bankRepo.save(any(BankObject.class))).thenReturn(bank1);

        BankObject savedBank = bankService.save(bank1);

        assertEquals(bank1, savedBank);
        Mockito.verify(bankRepo, Mockito.times(1)).save(any(BankObject.class));
    }

    @Test
    void existsById_shouldReturnTrue_whenEntityExists() {
        Mockito.when(bankRepo.existsById(1)).thenReturn(true);

        boolean exists = bankService.existsById(1);

        assertTrue(exists);
    }

    @Test
    void existsById_shouldReturnFalse_whenEntityDoesNotExist() {
        Mockito.when(bankRepo.existsById(1)).thenReturn(false);

        boolean exists = bankService.existsById(1);

        assertFalse(exists);
    }

    @Test
    void deleteById_shouldDeleteEntity_whenEntityExists() {
        Mockito.when(bankRepo.existsById(1)).thenReturn(true);

        bankService.deleteById(1);

        Mockito.verify(bankRepo, Mockito.times(1)).deleteById(1);
    }

    @Test
    void deleteById_shouldNotThrowExceptionWhenEntityDoesNotExist() {
        Mockito.when(bankRepo.existsById(1)).thenReturn(false);

        bankService.deleteById(1);

        Mockito.verify(bankRepo, Mockito.never()).deleteById(1);
    }
}
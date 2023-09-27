package com.example.demo.deposit;

import com.example.demo.bank.BankObject;
import com.example.demo.deposit.DepositObject;
import com.example.demo.deposit.DepositService;
import com.example.demo.deposit.DepositRepo;
import com.example.demo.сlient.ClientObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class DepositServiceTest {
    private DepositObject deposit1, deposit2;
    @InjectMocks
    private DepositService depositService;
    @Mock
    private DepositRepo depositRepo;

    @BeforeEach
    void setUp(){
        deposit1 = new DepositObject(1, new Date(), 1000, 12,
                        new BankObject(1, "Moskva", "21234"),
                        new ClientObject(1, "ABC Company", "ABC", "123 Main St", "LLC"));
        deposit2 = new DepositObject(1, new Date(), 1000, 12,
                new BankObject(2, "Belar", "223"),
                new ClientObject(1, "ABC Company", "ABC", "123 Main St", "LLC"));

    }
    @Test
    void findAll_shouldFindAllDeposits_whenAddedTwoDeposits() {
        Mockito.when(depositRepo.findAll()).thenReturn(List.of(deposit1,deposit2));

        List<DepositObject> deposits = depositService.findAll();

        assertEquals(2, deposits.size());
        assertTrue(deposits.contains(deposit1));
        assertTrue(deposits.contains(deposit2));
    }

    @Test
    void findById_shouldFindFirstDeposit_whenAddedTwoDeposits() {
        Mockito.when(depositRepo.findById(1)).thenReturn(Optional.ofNullable(deposit1));

        Optional<DepositObject> deposit = depositService.findById(1);

        assertEquals(deposit1, deposit.get());
    }

    @Test
    void save_shouldSaveDepositObject() {
        Mockito.when(depositRepo.save(any(DepositObject.class))).thenReturn(deposit1);

        DepositObject savedDeposit = depositService.save(deposit1);

        assertEquals(deposit1, savedDeposit);
        Mockito.verify(depositRepo, Mockito.times(1)).save(any(DepositObject.class));
    }

    @Test
    void existsById_shouldReturnTrue_whenEntityExists() {
        Mockito.when(depositRepo.existsById(1)).thenReturn(true);

        boolean exists = depositService.existsById(1);

        assertTrue(exists);
    }

    @Test
    void existsById_shouldReturnFalse_whenEntityDoesNotExist() {
        Mockito.when(depositRepo.existsById(1)).thenReturn(false);

        boolean exists = depositService.existsById(1);

        assertFalse(exists);
    }

    @Test
    void deleteById_shouldDeleteEntity_whenEntityExists() {
        Mockito.when(depositRepo.existsById(1)).thenReturn(true);

        depositService.deleteById(1);

        Mockito.verify(depositRepo, Mockito.times(1)).deleteById(1);
    }

    @Test
    void deleteById_shouldNotThrowExceptionWhenEntityDoesNotExist() {
        Mockito.when(depositRepo.existsById(1)).thenReturn(false);

        depositService.deleteById(1);

        Mockito.verify(depositRepo, Mockito.never()).deleteById(1);
    }
}

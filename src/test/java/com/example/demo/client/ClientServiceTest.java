package com.example.demo.client;

import com.example.demo.сlient.ClientObject;
import com.example.demo.сlient.ClientService;
import com.example.demo.сlient.ClientRepo;
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
public class ClientServiceTest {
    private ClientObject client1, client2;
    @InjectMocks
    private ClientService clientService;
    @Mock
    private ClientRepo clientRepo;

    @BeforeEach
    void setUp(){
        client1 = new ClientObject(1, "ABC Company", "ABC", "123 Main St", "LLC");
        client2 = new ClientObject(2, "XYZ Corporation", "XYZ Corp", "456 Elm St", "Inc");
    }
    @Test
    void findAll_shouldFindAllClients_whenAddedTwoClients() {
        Mockito.when(clientRepo.findAll()).thenReturn(List.of(client1,client2));

        List<ClientObject> clients = clientService.findAll();

        assertEquals(2, clients.size());
        assertTrue(clients.contains(client1));
        assertTrue(clients.contains(client2));
    }

    @Test
    void findById_shouldFindFirstClient_whenAddedTwoClients() {
        Mockito.when(clientRepo.findById(1)).thenReturn(Optional.ofNullable(client1));

        Optional<ClientObject> client = clientService.findById(1);

        assertEquals(client1, client.get());
    }

    @Test
    void save_shouldSaveClientObject() {
        Mockito.when(clientRepo.save(any(ClientObject.class))).thenReturn(client1);

        ClientObject savedClient = clientService.save(client1);

        assertEquals(client1, savedClient);
        Mockito.verify(clientRepo, Mockito.times(1)).save(any(ClientObject.class));
    }

    @Test
    void existsById_shouldReturnTrue_whenEntityExists() {
        Mockito.when(clientRepo.existsById(1)).thenReturn(true);

        boolean exists = clientService.existsById(1);

        assertTrue(exists);
    }

    @Test
    void existsById_shouldReturnFalse_whenEntityDoesNotExist() {
        Mockito.when(clientRepo.existsById(1)).thenReturn(false);

        boolean exists = clientService.existsById(1);

        assertFalse(exists);
    }

    @Test
    void deleteById_shouldDeleteEntity_whenEntityExists() {
        Mockito.when(clientRepo.existsById(1)).thenReturn(true);

        clientService.deleteById(1);

        Mockito.verify(clientRepo, Mockito.times(1)).deleteById(1);
    }

    @Test
    void deleteById_shouldNotThrowExceptionWhenEntityDoesNotExist() {
        Mockito.when(clientRepo.existsById(1)).thenReturn(false);

        clientService.deleteById(1);

        Mockito.verify(clientRepo, Mockito.never()).deleteById(1);
    }
}


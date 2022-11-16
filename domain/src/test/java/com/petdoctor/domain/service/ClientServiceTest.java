package com.petdoctor.domain.service;

import com.petdoctor.data.entity.ClientEntity;
import com.petdoctor.data.repository.ClientRepository;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClientServiceTest {
    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ClientServiceImpl clientService;

    @Test
    public void saveClient() {
        var clientEntity = new ClientEntity(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit",
                new ArrayList<>());

        var clientDto = new ClientDto(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit");

        Mockito.when(clientRepository.save(Mockito.any(ClientEntity.class))).thenReturn(clientEntity);
        ClientDto savedClientDto = clientService.saveClient(clientDto);

        assertEquals(savedClientDto.getId(), clientEntity.getId());
        assertEquals(savedClientDto.getName(), clientEntity.getName());
        assertEquals(savedClientDto.getSurname(), clientEntity.getSurname());
        assertEquals(savedClientDto.getEmail(), clientEntity.getEmail());
        assertEquals(savedClientDto.getPetProblem(), clientEntity.getPetProblem());
        assertEquals(savedClientDto.getPetName(), clientEntity.getPetName());
    }

    @Test
    public void findAllClient() {
        var clientEntity1 = new ClientEntity(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit",
                new ArrayList<>());

        var clientEntity2 = new ClientEntity(2L,
                "Alex",
                "Podiketckiy",
                "kek@gmail.com",
                "trotil",
                "bolit",
                new ArrayList<>());

        var clientEntity3 = new ClientEntity(3L,
                "Rostics",
                "Vape",
                "rostov@gmail.com",
                "cat",
                "ill",
                new ArrayList<>());

        Mockito.when(clientRepository
                .findAll()).thenReturn(List.of(clientEntity1, clientEntity2, clientEntity3));

        List<ClientDto> clientDtos = clientService.findAllClient();

        assertEquals(clientDtos.get(0).getId(), clientEntity1.getId());
        assertEquals(clientDtos.get(0).getName(), clientEntity1.getName());
        assertEquals(clientDtos.get(0).getSurname(), clientEntity1.getSurname());
        assertEquals(clientDtos.get(0).getEmail(), clientEntity1.getEmail());
        assertEquals(clientDtos.get(0).getPetProblem(), clientEntity1.getPetProblem());
        assertEquals(clientDtos.get(0).getPetName(), clientEntity1.getPetName());

        assertEquals(clientDtos.get(1).getId(), clientEntity2.getId());
        assertEquals(clientDtos.get(1).getName(), clientEntity2.getName());
        assertEquals(clientDtos.get(1).getSurname(), clientEntity2.getSurname());
        assertEquals(clientDtos.get(1).getEmail(), clientEntity2.getEmail());
        assertEquals(clientDtos.get(1).getPetProblem(), clientEntity2.getPetProblem());
        assertEquals(clientDtos.get(1).getPetName(), clientEntity2.getPetName());

        assertEquals(clientDtos.get(2).getId(), clientEntity3.getId());
        assertEquals(clientDtos.get(2).getName(), clientEntity3.getName());
        assertEquals(clientDtos.get(2).getSurname(), clientEntity3.getSurname());
        assertEquals(clientDtos.get(2).getEmail(), clientEntity3.getEmail());
        assertEquals(clientDtos.get(2).getPetProblem(), clientEntity3.getPetProblem());
        assertEquals(clientDtos.get(2).getPetName(), clientEntity3.getPetName());
    }

    @Test
    public void getClientById() {
        var clientEntity = new ClientEntity(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit",
                new ArrayList<>());

        Mockito.when(clientRepository.getReferenceById(1L)).thenReturn(clientEntity);
        ClientDto clientDto = clientService.getClientById(1L);

        assertEquals(clientDto.getId(), clientEntity.getId());
        assertEquals(clientDto.getName(), clientEntity.getName());
        assertEquals(clientDto.getSurname(), clientEntity.getSurname());
        assertEquals(clientDto.getEmail(), clientEntity.getEmail());
        assertEquals(clientDto.getPetProblem(), clientEntity.getPetProblem());
        assertEquals(clientDto.getPetName(), clientEntity.getPetName());
    }

    @Test
    public void updateClient() {
        var clientEntity = new ClientEntity(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit",
                new ArrayList<>());

        Mockito.when(clientRepository.getReferenceById(1L)).thenReturn(clientEntity);

        var repositoryReturnedEntity = new ClientEntity(1L,
                "Alex",
                "Podik",
                "niceeeeee@gmail.com",
                "sobaka",
                "bolit",
                new ArrayList<>());

        Mockito.when(clientRepository.save(Mockito.any(ClientEntity.class))).thenReturn(repositoryReturnedEntity);

        var newClientDto = new ClientDto(1L,
                "Alex",
                "Podik",
                "niceeeeee@gmail.com",
                null,
                null);

        ClientDto updatedClientDto = clientService.updateClient(newClientDto);

        assertEquals(repositoryReturnedEntity.getId(), updatedClientDto.getId());
        assertEquals(repositoryReturnedEntity.getName(), updatedClientDto.getName());
        assertEquals(repositoryReturnedEntity.getSurname(), updatedClientDto.getSurname());
        assertEquals(repositoryReturnedEntity.getEmail(), updatedClientDto.getEmail());
        assertEquals(repositoryReturnedEntity.getPetProblem(), updatedClientDto.getPetProblem());
        assertEquals(repositoryReturnedEntity.getPetName(), updatedClientDto.getPetName());
    }

    @Test
    public void deleteClientById() {
        clientService.deleteClientById(1L);
        Mockito.verify(clientRepository, Mockito.times(1)).deleteById(1L);
    }
}

package com.petdoctor.domain.mapper;

import com.petdoctor.data.entity.ClientEntity;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.model.client.Client;
import com.petdoctor.domain.tool.mapper.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClientMapperTest {
    @Autowired
    private Mapper<ClientEntity, Client, ClientDto> clientMapper;

    @Test
    void mapEntityToModel() {
        var clientEntity = new ClientEntity(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit",
                null);
        Client client = clientMapper.toModelFromEntity(clientEntity);

        assertEquals(client.getId(), clientEntity.getId());
        assertEquals(client.getName(), clientEntity.getName());
        assertEquals(client.getSurname(), clientEntity.getSurname());
        assertEquals(client.getPetName(), clientEntity.getPetName());
        assertEquals(client.getPetProblem(), clientEntity.getPetProblem());
    }

    @Test
    void mapModelToEntity() {
        var client = new Client(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit");
        ClientEntity clientEntity = clientMapper.toEntityFromModel(client);

        assertEquals(client.getId(), clientEntity.getId());
        assertEquals(client.getName(), clientEntity.getName());
        assertEquals(client.getSurname(), clientEntity.getSurname());
        assertEquals(client.getPetName(), clientEntity.getPetName());
        assertEquals(client.getPetProblem(), clientEntity.getPetProblem());
    }

    @Test
    void mapDtoToModel() {
        ClientDto clientDto = new ClientDto(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit");

        Client client = clientMapper.toModelFromDto(clientDto);

        assertEquals(client.getId(), clientDto.getId());
        assertEquals(client.getName(), clientDto.getName());
        assertEquals(client.getSurname(), clientDto.getSurname());
    }

    @Test
    void mapModelToDto() {
        Client client = new Client(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit");

        ClientDto clientDto = clientMapper.toDtoFromModel(client);

        assertEquals(client.getId(), clientDto.getId());
        assertEquals(client.getName(), clientDto.getName());
        assertEquals(client.getSurname(), clientDto.getSurname());
    }
}

package com.petdoctor.domain.service.impl;

import com.petdoctor.data.entity.ClientEntity;
import com.petdoctor.data.repository.ClientRepository;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.model.client.Client;
import com.petdoctor.domain.model.client.ClientInfo;
import com.petdoctor.domain.service.ClientService;
import com.petdoctor.domain.tool.exception.PetDoctorNotFoundException;
import com.petdoctor.domain.tool.exception.PetDoctorNullException;
import com.petdoctor.domain.tool.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final Mapper<ClientEntity, Client, ClientDto> clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, Mapper<ClientEntity, Client, ClientDto> clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDto> findAllClient() {
        List<ClientInfo> clientInfos = clientRepository
                .findAll()
                .stream()
                .map(appointmentEntity -> (ClientInfo) clientMapper.toModelFromEntity(appointmentEntity))
                .toList();

        return clientInfos
                .stream()
                .map(a -> clientMapper.toDtoFromModel((Client) a))
                .toList();
    }

    @Override
    public ClientDto getClientById(Long id) {
        if (id == null) {
            throw new PetDoctorNullException("id is null!");
        }

        try {
            ClientInfo clientInfo = clientMapper.toModelFromEntity(clientRepository.getReferenceById(id));
            return clientMapper.toDtoFromModel((Client) clientInfo);
        } catch (EntityNotFoundException e) {
            throw new PetDoctorNotFoundException(id.toString());
        }
    }

    @Override
    public ClientDto saveClient(ClientDto clientDto) {
        if (clientDto == null) {
            throw new PetDoctorNullException("the appointmentDto is null!");
        }

        ClientInfo clientInfo = clientMapper.toModelFromDto(clientDto);
        var appointmentEntity =
                clientRepository.save(clientMapper.toEntityFromModel((Client) clientInfo));
        return clientMapper.toDtoFromModel(clientMapper.toModelFromEntity(appointmentEntity));
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        if (clientDto == null) {
            throw new PetDoctorNullException("the appointmentDto is null!");
        }

        try {
            ClientDto oldClient = this.getClientById(clientDto.getId());
            String name = oldClient.getName();
            String surname = oldClient.getSurname();
            String email = oldClient.getEmail();
            String petName = oldClient.getPetName();
            String petProblem = oldClient.getPetProblem();

            if (clientDto.getName() != null) {
                name = clientDto.getName();
            }

            if (clientDto.getSurname() != null) {
                surname = clientDto.getSurname();
            }

            if (clientDto.getEmail() != null) {
                email = clientDto.getEmail();
            }

            if (clientDto.getPetName() != null) {
                petName = clientDto.getPetName();
            }

            if (clientDto.getPetProblem() != null) {
                petProblem = clientDto.getPetProblem();
            }

            var updatedClientDto = new ClientDto(oldClient.getId(),
                    name,
                    surname,
                    email,
                    petName,
                    petProblem);

            ClientInfo updatedClientInfo =
                    clientMapper.toModelFromDto(updatedClientDto);

            var updatedAppointmentEntity =
                    clientRepository.save(clientMapper.toEntityFromModel((Client) updatedClientInfo));
            return clientMapper.toDtoFromModel(clientMapper.toModelFromEntity(updatedAppointmentEntity));
        } catch (Exception e) {
            throw new PetDoctorNotFoundException(clientDto.getId().toString());
        }
    }

    @Override
    public void deleteClientById(Long id) {
        if (id == null) {
            throw new PetDoctorNullException("id is null!");
        }
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PetDoctorNotFoundException(id.toString());
        }
    }
}

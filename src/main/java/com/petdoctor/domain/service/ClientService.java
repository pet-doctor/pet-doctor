package com.petdoctor.domain.service;

import com.petdoctor.domain.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> findAllClient();

    ClientDto getClientById(Long id);

    ClientDto saveClient(ClientDto clientDto);

    ClientDto updateClient(ClientDto clientDto);

    void deleteClientById(Long id);
}

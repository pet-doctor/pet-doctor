package com.petdoctor.domain.service;

import com.petdoctor.domain.dto.ClientDto;

import java.util.List;

public interface ClientService {
    /**
     * @return List of DTO of all Clients
     */
    List<ClientDto> findAllClient();

    /**
     * @param id Client ID
     * @return Immutable DTO of Client
     */
    ClientDto getClientById(Long id);

    /**
     * @param clientDto Immutable DTO of Client
     * @return Saved to Database DTO of Client
     */
    ClientDto saveClient(ClientDto clientDto);

    /**
     * @param clientDto Immutable DTO of Client
     * @return Updated to Database DTO of Client
     */
    ClientDto updateClient(ClientDto clientDto);

    /**
     * @param id Client ID
     */
    void deleteClientById(Long id);
}

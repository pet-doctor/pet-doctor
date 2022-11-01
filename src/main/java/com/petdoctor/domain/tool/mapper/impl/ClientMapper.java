package com.petdoctor.domain.tool.mapper.impl;

import com.petdoctor.data.entity.ClientEntity;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.model.client.Client;
import com.petdoctor.domain.tool.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper extends AbstractMapper<ClientEntity, Client, ClientDto> {
    ClientMapper(Class<ClientEntity> entityClass, Class<Client> modelClass, Class<ClientDto> dtoClass) {
        super(entityClass, modelClass, dtoClass);
    }

    @Override
    protected void mapSpecificFieldsToModelFromEntity(ClientEntity source, Client destination) {

    }

    @Override
    protected void mapSpecificFieldsToEntityFromModel(Client source, ClientEntity destination) {

    }

    @Override
    protected void mapSpecificFieldsToDtoFromModel(Client source, ClientDto destination) {

    }

    @Override
    protected void mapSpecificFieldsToModelFromDto(ClientDto source, Client destination) {

    }


}

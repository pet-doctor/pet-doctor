package com.petdoctor.domain.tool.mapper.impl;

import com.petdoctor.data.entity.ClientEntity;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.model.client.Client;
import com.petdoctor.domain.tool.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("singleton")
public class ClientMapper extends AbstractMapper<ClientEntity, Client, ClientDto> {

    @Autowired
    public ClientMapper(ModelMapper mapper) {
        super(ClientEntity.class, Client.class, ClientDto.class);
        this.mapper = mapper;
    }

    /**
     * configures Clients' mapper after constructing it
     */
    @PostConstruct
    public void setupMapper() {

        mapper.createTypeMap(ClientEntity.class, Client.class)
                .setPostConverter(toModelFromEntityConverter());
        mapper.createTypeMap(Client.class, ClientEntity.class)
                .setPostConverter(toEntityFromModelConverter());
        mapper.createTypeMap(Client.class, ClientDto.class)
                .setPostConverter(toDtoFromModelConverter());
        mapper.createTypeMap(ClientDto.class, Client.class)
                .setPostConverter(toModelFromDtoConverter());
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

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
    private ModelMapper modelMapper;

    ClientMapper(Class<ClientEntity> entityClass, Class<Client> modelClass, Class<ClientDto> dtoClass, ModelMapper modelMapper) {
        super(entityClass, modelClass, dtoClass);
        this.modelMapper = modelMapper;
    }

    /**
     * configures Clients' mapper after constructing it
     */
    @PostConstruct
    public void setupMapper() {

        modelMapper.createTypeMap(ClientEntity.class, Client.class)
                .setPostConverter(toModelFromEntityConverter());
        modelMapper.createTypeMap(Client.class, ClientEntity.class)
                .setPostConverter(toEntityFromModelConverter());
        modelMapper.createTypeMap(Client.class, ClientDto.class)
                .setPostConverter(toDtoFromModelConverter());
        modelMapper.createTypeMap(ClientDto.class, Client.class)
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

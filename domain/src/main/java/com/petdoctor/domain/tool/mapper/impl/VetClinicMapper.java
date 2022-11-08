package com.petdoctor.domain.tool.mapper.impl;

import com.petdoctor.data.entity.ClientEntity;
import com.petdoctor.data.entity.DoctorEntity;
import com.petdoctor.data.entity.VetClinicEntity;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.dto.VetClinicDto;
import com.petdoctor.domain.model.client.Client;
import com.petdoctor.domain.model.doctor.Doctor;
import com.petdoctor.domain.model.vet.clinic.VetClinic;
import com.petdoctor.domain.tool.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
public class VetClinicMapper extends AbstractMapper<VetClinicEntity, VetClinic, VetClinicDto> {


    public VetClinicMapper(ModelMapper modelMapper) {
        super(VetClinicEntity.class, VetClinic.class, VetClinicDto.class);
        this.mapper = modelMapper;
    }

    @PostConstruct
    public void setupMapping() {
        mapper.createTypeMap(VetClinicEntity.class, VetClinic.class)
                .setPostConverter(toModelFromEntityConverter());
        mapper.createTypeMap(VetClinic.class, VetClinicEntity.class)
                .setPostConverter(toEntityFromModelConverter());
        mapper.createTypeMap(VetClinic.class, VetClinicDto.class)
                .setPostConverter(toDtoFromModelConverter());
        mapper.createTypeMap(VetClinicDto.class, VetClinic.class)
                .setPostConverter(toModelFromDtoConverter());
    }


    @Override
    protected void mapSpecificFieldsToModelFromEntity(VetClinicEntity source, VetClinic destination) {

        destination.setClients(source.getClientEntities()
                .stream().collect(Collectors.toMap(ClientEntity::getId,
                        clientEntity -> mapper.map(clientEntity, Client.class))));
        destination.setDoctors(source.getDoctorEntities()
                .stream().collect(Collectors.toMap(DoctorEntity::getId,
                        doctorEntity -> mapper.map(doctorEntity, Doctor.class))));
    }

    @Override
    protected void mapSpecificFieldsToEntityFromModel(VetClinic source, VetClinicEntity destination) {

        destination.setClientEntities(source.getClients()
                .values().stream().map(client -> mapper.map(client, ClientEntity.class))
                .toList());
        destination.setDoctorEntities(source.getDoctors()
                .values().stream().map(doctor -> mapper.map(doctor, DoctorEntity.class))
                .toList());
    }

    @Override
    protected void mapSpecificFieldsToDtoFromModel(VetClinic source, VetClinicDto destination) {

        // TODO: we are unable to change VetClinicDto special fields
    }

    @Override
    protected void mapSpecificFieldsToModelFromDto(VetClinicDto source, VetClinic destination) {

        destination.setClients(source.getClients()
                .stream().collect(Collectors.toMap(ClientDto::getId,
                        clientDto -> mapper.map(clientDto, Client.class))));
        destination.setDoctors(source.getDoctors()
                .stream().collect(Collectors.toMap(DoctorDto::getId,
                        doctorDto -> mapper.map(doctorDto, Doctor.class))));
    }
}

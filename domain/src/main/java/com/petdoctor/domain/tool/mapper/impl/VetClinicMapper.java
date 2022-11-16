package com.petdoctor.domain.tool.mapper.impl;

import com.petdoctor.data.entity.ClientEntity;
import com.petdoctor.data.entity.DoctorEntity;
import com.petdoctor.data.entity.VetClinicEntity;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.dto.VetClinicDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.appointment.AppointmentInterface;
import com.petdoctor.domain.model.client.Client;
import com.petdoctor.domain.model.client.ClientInterface;
import com.petdoctor.domain.model.doctor.Doctor;
import com.petdoctor.domain.model.doctor.DoctorInterface;
import com.petdoctor.domain.model.vet.clinic.VetClinic;
import com.petdoctor.domain.tool.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
public class VetClinicMapper extends AbstractMapper<VetClinicEntity, VetClinic, VetClinicDto> {

    @Autowired
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

        if (source.getClientEntities() != null) {
            HashMap<Long, ClientInterface> hashMap = new HashMap<>();
            source.getClientEntities().forEach(client -> hashMap.put(client.getId(), mapper.map(client, Client.class)));
            destination.setClients(hashMap);
        }
        if (source.getDoctorEntities() != null) {
            HashMap<Long, DoctorInterface> hashMap = new HashMap<>();
            source.getClientEntities().forEach(doctor -> hashMap.put(doctor.getId(), mapper.map(doctor, Doctor.class)));
            destination.setDoctors(hashMap);
        }
    }

    @Override
    protected void mapSpecificFieldsToEntityFromModel(VetClinic source, VetClinicEntity destination) {

        if (source.getClients() != null)
            destination.setClientEntities(source.getClients()
                    .values().stream().map(client -> mapper.map(client, ClientEntity.class))
                    .toList());
        if (source.getDoctors() != null)
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

        if (source.getClients() != null) {
            HashMap<Long, ClientInterface> hashMap = new HashMap<>();
            source.getClients().forEach(client -> hashMap.put(client.getId(), mapper.map(client, Client.class)));
            destination.setClients(hashMap);
        }
        if (source.getDoctors() != null) {
            HashMap<Long, DoctorInterface> hashMap = new HashMap<>();
            source.getDoctors().forEach(doctor -> hashMap.put(doctor.getId(), mapper.map(doctor, Doctor.class)));
            destination.setDoctors(hashMap);
        }
    }
}

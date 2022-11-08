package com.petdoctor.domain.tool.mapper.impl;

import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.data.entity.ClientEntity;
import com.petdoctor.data.entity.DoctorEntity;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.client.Client;
import com.petdoctor.domain.model.doctor.Doctor;
import com.petdoctor.domain.model.vet.clinic.VetClinic;
import com.petdoctor.domain.tool.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("singleton")
public class AppointmentMapper extends AbstractMapper<AppointmentEntity, Appointment, AppointmentDto> {

    @Autowired
    public AppointmentMapper(ModelMapper modelMapper) {
        super(AppointmentEntity.class, Appointment.class, AppointmentDto.class);
        this.mapper = modelMapper;
    }

    /**
     * configures Appointments' mapper after constructing it
     */
    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(AppointmentEntity.class, Appointment.class)
                .setPostConverter(toModelFromEntityConverter());
        mapper.createTypeMap(Appointment.class, AppointmentEntity.class)
                .setPostConverter(toEntityFromModelConverter());
        mapper.createTypeMap(Appointment.class, AppointmentDto.class)
                .setPostConverter(toDtoFromModelConverter());
        mapper.createTypeMap(AppointmentDto.class, Appointment.class)
                .setPostConverter(toModelFromDtoConverter());
    }

    @Override
    protected void mapSpecificFieldsToModelFromEntity(AppointmentEntity source, Appointment destination) {

        if (source.getClientEntity() != null)
            destination.setClient(mapper.map(source.getClientEntity(), Client.class));
        if (source.getDoctorEntity() != null)
            destination.setDoctor(mapper.map(source.getDoctorEntity(), Doctor.class));
    }

    @Override
    protected void mapSpecificFieldsToEntityFromModel(Appointment source, AppointmentEntity destination) {

        if (source.getClient() != null)
            destination.setClientEntity(mapper.map(source.getClient(), ClientEntity.class));
        if (source.getDoctor() != null)
            destination.setDoctorEntity(mapper.map(source.getDoctor(), DoctorEntity.class));
    }

    @Override
    protected void mapSpecificFieldsToDtoFromModel(Appointment source, AppointmentDto destination) {

        // TODO: we are unable to change AppointmentDto special fields
    }

    @Override
    protected void mapSpecificFieldsToModelFromDto(AppointmentDto source, Appointment destination) {

        // TODO: rename methods (getClient != getDoctorDto for Oleg)
        if (source.getClient() != null)
            destination.setClient(mapper.map(source.getClient(), Client.class));
        if (source.getDoctorDto() != null)
            destination.setDoctor(mapper.map(source.getDoctorDto(), Doctor.class));
        if (source.getVetClinicDto() != null)
            destination.setVetClinic(mapper.map(source.getVetClinicDto(), VetClinic.class));
    }

}

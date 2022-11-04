package com.petdoctor.domain.tool.mapper.impl;

import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.data.entity.ClientEntity;
import com.petdoctor.data.entity.DoctorEntity;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.client.Client;
import com.petdoctor.domain.model.doctor.Doctor;
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
    private ModelMapper modelMapper;

    AppointmentMapper(Class<AppointmentEntity> entityClass, Class<Appointment> modelClass, Class<AppointmentDto> dtoClass, ModelMapper modelMapper) {
        super(entityClass, modelClass, dtoClass);
        this.modelMapper = modelMapper;
    }

    /**
     * configures Appointments' mapper after constructing it
     */
    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(AppointmentEntity.class, Appointment.class)
                .setPostConverter(toModelFromEntityConverter());
        modelMapper.createTypeMap(Appointment.class, AppointmentEntity.class)
                .setPostConverter(toEntityFromModelConverter());
        modelMapper.createTypeMap(Appointment.class, AppointmentDto.class)
                .setPostConverter(toDtoFromModelConverter());
        modelMapper.createTypeMap(AppointmentDto.class, Appointment.class)
                .setPostConverter(toModelFromDtoConverter());
    }

    @Override
    protected void mapSpecificFieldsToModelFromEntity(AppointmentEntity source, Appointment destination) {

        destination.setClient(modelMapper.map(source.getClientEntity(), Client.class));
        destination.setDoctor(modelMapper.map(source.getDoctorEntity(), Doctor.class));
    }

    @Override
    protected void mapSpecificFieldsToEntityFromModel(Appointment source, AppointmentEntity destination) {

        destination.setClientEntity(modelMapper.map(source.getClient(), ClientEntity.class));
        destination.setDoctorEntity(modelMapper.map(source.getDoctor(), DoctorEntity.class));
    }

    @Override
    protected void mapSpecificFieldsToDtoFromModel(Appointment source, AppointmentDto destination) {

        // TODO: we are unable to change AppointmentDto special fields
    }

    @Override
    protected void mapSpecificFieldsToModelFromDto(AppointmentDto source, Appointment destination) {

        destination.setClient(modelMapper.map(source.getClient(), Client.class));
        destination.setDoctor(modelMapper.map(source.getDoctorDto(), Doctor.class));
    }

}

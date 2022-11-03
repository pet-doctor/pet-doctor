package com.petdoctor.domain.tool.mapper.impl;

import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.tool.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper extends AbstractMapper<AppointmentEntity, Appointment, AppointmentDto> {

    AppointmentMapper(Class<AppointmentEntity> entityClass, Class<Appointment> modelClass, Class<AppointmentDto> dtoClass) {
        super(entityClass, modelClass, dtoClass);
    }

    @Override
    protected void mapSpecificFieldsToModelFromEntity(AppointmentEntity source, Appointment destination) {

    }

    @Override
    protected void mapSpecificFieldsToEntityFromModel(Appointment source, AppointmentEntity destination) {

    }

    @Override
    protected void mapSpecificFieldsToDtoFromModel(Appointment source, AppointmentDto destination) {

    }

    @Override
    protected void mapSpecificFieldsToModelFromDto(AppointmentDto source, Appointment destination) {

    }

}

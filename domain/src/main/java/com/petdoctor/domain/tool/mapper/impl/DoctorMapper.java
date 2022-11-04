package com.petdoctor.domain.tool.mapper.impl;

import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.data.entity.DoctorEntity;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.dto.lite.AppointmentLiteDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.doctor.Doctor;
import com.petdoctor.domain.tool.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class DoctorMapper extends AbstractMapper<DoctorEntity, Doctor, DoctorDto> {


    DoctorMapper(Class<DoctorEntity> entityClass, Class<Doctor> modelClass, Class<DoctorDto> dtoClass) {
        super(entityClass, modelClass, dtoClass);
    }

    @Override
    protected void mapSpecificFieldsToModelFromEntity(DoctorEntity source, Doctor destination) {

    }

    @Override
    protected void mapSpecificFieldsToEntityFromModel(Doctor source, DoctorEntity destination) {

        destination.setAppointmentEntities(source.getAppointments()
                .values().stream().map(appointmentInfo -> modelMapper.map(appointmentInfo, AppointmentEntity.class))
                .toList());
    }

    @Override
    protected void mapSpecificFieldsToDtoFromModel(Doctor source, DoctorDto destination) {

        // TODO: we are unable to change DoctorDto special fields
    }

    @Override
    protected void mapSpecificFieldsToModelFromDto(DoctorDto source, Doctor destination) {


        // TODO: should we create some more converters for lite dtos???
        destination.setAppointments(source.getAppointments()
                .stream()
                .collect(Collectors.toMap(AppointmentLiteDto::getId,
                        elem -> modelMapper.map(elem, Appointment.class))));
    }


}

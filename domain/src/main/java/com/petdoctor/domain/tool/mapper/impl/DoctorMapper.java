package com.petdoctor.domain.tool.mapper.impl;

import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.data.entity.DoctorEntity;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.doctor.Doctor;
import com.petdoctor.domain.tool.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
@Scope("singleton")
public class DoctorMapper extends AbstractMapper<DoctorEntity, Doctor, DoctorDto> {

    @Autowired
    public DoctorMapper(ModelMapper modelMapper) {
        super(DoctorEntity.class, Doctor.class, DoctorDto.class);
        this.mapper = modelMapper;
    }

    /**
     * configures Doctors' mapper after constructing it
     */
    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(DoctorEntity.class, Doctor.class)
                .setPostConverter(toModelFromEntityConverter());
        mapper.createTypeMap(Doctor.class, DoctorEntity.class)
                .setPostConverter(toEntityFromModelConverter());
        mapper.createTypeMap(Doctor.class, DoctorDto.class)
                .setPostConverter(toDtoFromModelConverter());
        mapper.createTypeMap(DoctorDto.class, Doctor.class)
                .setPostConverter(toModelFromDtoConverter());
    }

    @Override
    protected void mapSpecificFieldsToModelFromEntity(DoctorEntity source, Doctor destination) {

        if (source.getAppointmentEntities() != null)
            destination.setAppointments(source.getAppointmentEntities()
                    .stream()
                    .collect(Collectors.toMap(AppointmentEntity::getId,
                            elem -> mapper.map(elem, Appointment.class))));
    }

    @Override
    protected void mapSpecificFieldsToEntityFromModel(Doctor source, DoctorEntity destination) {

        if (source.getAppointments() != null)
            destination.setAppointmentEntities(source.getAppointments()
                    .values().stream().map(appointmentInfo -> mapper.map(appointmentInfo, AppointmentEntity.class))
                    .toList());
    }

    @Override
    protected void mapSpecificFieldsToDtoFromModel(Doctor source, DoctorDto destination) {

        // TODO: we are unable to change DoctorDto special fields
    }

    @Override
    protected void mapSpecificFieldsToModelFromDto(DoctorDto source, Doctor destination) {

        // TODO: should we create some more converters for lite dtos???
        if (source.getAppointments() != null)
            destination.setAppointments(source.getAppointments()
                    .stream()
                    .collect(Collectors.toMap(AppointmentDto::getId, elem -> mapper.map(elem, Appointment.class))));
    }
}

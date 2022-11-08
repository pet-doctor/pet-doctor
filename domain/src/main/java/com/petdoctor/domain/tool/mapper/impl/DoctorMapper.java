package com.petdoctor.domain.tool.mapper.impl;

import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.data.entity.DoctorEntity;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.dto.lite.AppointmentLiteDto;
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
    private ModelMapper modelMapper;

    DoctorMapper(Class<DoctorEntity> entityClass, Class<Doctor> modelClass, Class<DoctorDto> dtoClass, ModelMapper modelMapper) {
        super(entityClass, modelClass, dtoClass);
        this.modelMapper = modelMapper;
    }

    /**
     * configures Doctors' mapper after constructing it
     */
    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(DoctorEntity.class, Doctor.class)
                .setPostConverter(toModelFromEntityConverter());
        modelMapper.createTypeMap(Doctor.class, DoctorEntity.class)
                .setPostConverter(toEntityFromModelConverter());
        modelMapper.createTypeMap(Doctor.class, DoctorDto.class)
                .setPostConverter(toDtoFromModelConverter());
        modelMapper.createTypeMap(DoctorDto.class, Doctor.class)
                .setPostConverter(toModelFromDtoConverter());
    }

    @Override
    protected void mapSpecificFieldsToModelFromEntity(DoctorEntity source, Doctor destination) {

        destination.setAppointments(source.getAppointmentEntities()
                .stream()
                .collect(Collectors.toMap(AppointmentEntity::getId,
                        elem -> modelMapper.map(elem, Appointment.class))));
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

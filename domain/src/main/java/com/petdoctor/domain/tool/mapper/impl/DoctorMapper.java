package com.petdoctor.domain.tool.mapper.impl;

import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.data.entity.DoctorEntity;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.appointment.AppointmentInterface;
import com.petdoctor.domain.model.doctor.Doctor;
import com.petdoctor.domain.tool.mapper.AbstractMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

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
        if (source.getAppointmentEntities() != null) {
            HashMap<Long, AppointmentInterface> hashMap = new HashMap<>();
            source.getAppointmentEntities().forEach(a -> hashMap.put(a.getId(), mapper.map(a, Appointment.class)));
            destination.setAppointments(hashMap);
        }
//        if (source.getAppointmentEntities() != null)
//            destination.setAppointments(source.getAppointmentEntities()
//                    .stream()
//                    .collect(Collectors.toMap(AppointmentEntity::getId, elem -> mapper.map(elem, Appointment.class))));
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
        // TODO: genius idea but i dont know how do it normally
        source.getAppointments().values().forEach(a -> destination.getAppointments().add(mapper.map(a, AppointmentDto.class)));
    }

    @Override
    protected void mapSpecificFieldsToModelFromDto(DoctorDto source, Doctor destination) {
        HashMap<Long, AppointmentInterface> hashMap = new HashMap<>();
        source.getAppointments().forEach(a -> hashMap.put(a.getId(), mapper.map(a, Appointment.class)));
        destination.setAppointments(hashMap);
//        if (source.getAppointments() != null)
//            destination.setAppointments(source.getAppointments()
//                    .stream()
//                    .collect(Collectors.toMap(AppointmentDto::getId, elem -> mapper.map(elem, Appointment.class))));

//        if (source.getAppointments() == null)
//            destination.setAppointments(source.getAppointments()
//                    .stream()
//                    .collect(Collectors.toMap(AppointmentDto::getId, elem -> mapper.map(elem, Appointment.class))));
    }
}

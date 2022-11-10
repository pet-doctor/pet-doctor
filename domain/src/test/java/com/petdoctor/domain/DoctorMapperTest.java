package com.petdoctor.domain;

import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.data.entity.AppointmentState;
import com.petdoctor.data.entity.DoctorEntity;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.dto.VetClinicDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.appointment.AppointmentInterface;
import com.petdoctor.domain.model.doctor.Doctor;
import com.petdoctor.domain.tool.mapper.Mapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DoctorMapperTest {

    @Autowired
    private Mapper<DoctorEntity, Doctor, DoctorDto> doctorMapper;

    @Test
    void mapEntityToModel() {
        DoctorEntity doctorEntity = new DoctorEntity(1L,
                "Alex",
                "Bosov",
                "doc@gmail.com",
                1,
                List.of(new AppointmentEntity(1L, LocalDate.now(), AppointmentState.OPEN),
                        new AppointmentEntity(2L, LocalDate.now(), AppointmentState.CLOSED)), null);

        Doctor doctor = doctorMapper.toModelFromEntity(doctorEntity);

        assertEquals(doctor.getId(), doctorEntity.getId());
        assertEquals(doctor.getName(), doctorEntity.getName());
        assertEquals(doctor.getSurname(), doctorEntity.getSurname());
        doctorEntity.getAppointmentEntities().forEach(a -> assertTrue(doctor.getAppointments().containsKey(a.getId())));
    }

    @Test
    void mapModelToEntity() {
        HashMap<Long, AppointmentInterface> map = new HashMap<>();
        map.put(1L, new Appointment(1L, LocalDate.now(), AppointmentState.OPEN, null, null, null));
        map.put(2L, new Appointment(2L, LocalDate.now(), AppointmentState.CLOSED, null, null, null));

        Doctor doctor = new Doctor(1L,
                "Alex",
                "Bosov",
                "doc@gmail.com",
                1,
                map);

        DoctorEntity doctorEntity = doctorMapper.toEntityFromModel(doctor);

        assertEquals(doctorEntity.getId(), doctor.getId());
        assertEquals(doctorEntity.getName(), doctor.getName());
        assertEquals(doctorEntity.getSurname(), doctor.getSurname());
        doctorEntity.getAppointmentEntities().forEach(a -> assertTrue(doctor.getAppointments().containsKey(a.getId())));
    }

    @Test
    void mapDtoToModel() {
        ArrayList<AppointmentDto> appointmentDtos = new ArrayList<>();
        appointmentDtos.add(new AppointmentDto(1L,
                        LocalDate.now(),
                        AppointmentState.OPEN,
                        Mockito.any(ClientDto.class),
                        Mockito.any(DoctorDto.class),
                        Mockito.any(VetClinicDto.class)));
        appointmentDtos.add(new AppointmentDto(2L,
                        LocalDate.now(),
                        AppointmentState.CLOSED,
                        Mockito.any(ClientDto.class),
                        Mockito.any(DoctorDto.class),
                        Mockito.any(VetClinicDto.class)));


        DoctorDto doctorDto = new DoctorDto(1L,
                "Alex",
                "Bosov",
                "doc@gmail.com",
                1,
                appointmentDtos);

        Doctor doctor = doctorMapper.toModelFromDto(doctorDto);

        assertEquals(doctor.getId(), doctorDto.getId());
        assertEquals(doctor.getName(), doctorDto.getName());
        assertEquals(doctor.getSurname(), doctorDto.getSurname());
        doctorDto.getAppointments().forEach(a -> assertTrue(doctor.getAppointments().containsKey(a.getId())));
    }

    @Test
    void mapModelToDto() {
        HashMap<Long, AppointmentInterface> map = new HashMap<>();
        map.put(1L, new Appointment(1L, LocalDate.now(), AppointmentState.OPEN, null, null, null));
        map.put(2L, new Appointment(2L, LocalDate.now(), AppointmentState.CLOSED, null, null, null));

        Doctor doctor = new Doctor(1L,
                "Alex",
                "Bosov",
                "doc@gmail.com",
                1,
                map);

        DoctorDto doctorDto = doctorMapper.toDtoFromModel(doctor);

        assertEquals(doctor.getId(), doctorDto.getId());
        assertEquals(doctor.getName(), doctorDto.getName());
        assertEquals(doctor.getSurname(), doctorDto.getSurname());
        doctorDto.getAppointments().forEach(a -> assertTrue(doctor.getAppointments().containsKey(a.getId())));
    }
}
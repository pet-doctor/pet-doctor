package com.petdoctor.domain;

import com.petdoctor.data.entity.*;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.dto.VetClinicDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.client.Client;
import com.petdoctor.domain.model.doctor.Doctor;
import com.petdoctor.domain.model.vet.clinic.VetClinic;
import com.petdoctor.domain.tool.mapper.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AppointmentMapperTest {
    @Autowired
    private Mapper<AppointmentEntity, Appointment, AppointmentDto> appointmentMapper;

    @Test
    void mapEntityToModel() {

        var doctorEntity = new DoctorEntity(1L,
                "Alex",
                "Bosov",
                "doc@gmail.com",
                1,
                null,
                null);

        var clientEntity = new ClientEntity(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit",
                null);

        var appointmentEntity = new AppointmentEntity(1L,
                LocalDate.now(),
                AppointmentState.OPEN,
                clientEntity,
                doctorEntity);

        Appointment appointment = appointmentMapper.toModelFromEntity(appointmentEntity);

        assertEquals(appointment.getId(), appointmentEntity.getId());
        assertEquals(appointment.getStartTime(), appointmentEntity.getStartTime());
        assertEquals(appointment.getAppointmentState(), appointmentEntity.getAppointmentState());
        assertEquals(appointment.getClient().getId(), clientEntity.getId());
        assertEquals(appointment.getDoctor().getId(), doctorEntity.getId());
    }

    @Test
    void mapModelToEntity() {
        var vetClinic = new VetClinic(1L,
                "Kronversy",
                "vetClinic@yandex.ru",
                null,
                null);

        var doctor = new Doctor(1L,
                "Alex",
                "Bosov",
                "doc@gmail.com",
                1,
                new HashMap<>()); // TODO: if null, then error with map)))

        var client = new Client(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit");

        var appointment = new Appointment(1L,
                LocalDate.now(),
                AppointmentState.OPEN,
                client,
                doctor,
                vetClinic);

        AppointmentEntity appointmentEntity = appointmentMapper.toEntityFromModel(appointment);

        assertEquals(appointment.getId(), appointmentEntity.getId());
        assertEquals(appointment.getStartTime(), appointmentEntity.getStartTime());
        assertEquals(appointment.getAppointmentState(), appointmentEntity.getAppointmentState());
        assertEquals(appointment.getClient().getId(), client.getId());
        assertEquals(appointment.getDoctor().getId(), doctor.getId());
    }

    @Test
    void mapDtoToModel() {
        var vetClinicDto = new VetClinicDto(1L,
                "Kronversy",
                "vetClinic@yandex.ru",
                null,
                null);

        var doctorDto = new DoctorDto(1L,
                "Alex",
                "Bosov",
                "doc@gmail.com",
                1,
                new ArrayList<>()); // TODO: if null, then error with map)))

        var clientDto = new ClientDto(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit");

        var appointmentDto = new AppointmentDto(1L,
                LocalDate.now(),
                AppointmentState.OPEN,
                clientDto,
                doctorDto,
                vetClinicDto);

        Appointment appointment = appointmentMapper.toModelFromDto(appointmentDto);

        assertEquals(appointment.getId(), appointmentDto.getId());
        assertEquals(appointment.getStartTime(), appointmentDto.getStartTime());
        assertEquals(appointment.getAppointmentState(), appointmentDto.getAppointmentState());
        assertEquals(appointment.getClient().getId(), appointmentDto.getId());
        assertEquals(appointment.getDoctor().getId(), appointmentDto.getId());
    }

    @Test
    void mapModelToDto() {
        var vetClinic = new VetClinic(1L,
                "Kronversy",
                "vetClinic@yandex.ru",
                null,
                null);

        var doctor = new Doctor(1L,
                "Alex",
                "Bosov",
                "doc@gmail.com",
                1,
                new HashMap<>()); // TODO: if null, then error with map)))

        var client = new Client(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit");

        var appointment = new Appointment(1L,
                LocalDate.now(),
                AppointmentState.OPEN,
                client,
                doctor,
                vetClinic);

        AppointmentDto appointmentDto = appointmentMapper.toDtoFromModel(appointment);

        assertEquals(appointment.getId(), appointmentDto.getId());
        assertEquals(appointment.getStartTime(), appointmentDto.getStartTime());
        assertEquals(appointment.getAppointmentState(), appointmentDto.getAppointmentState());
        assertEquals(appointment.getClient().getId(), appointmentDto.getId());
        assertEquals(appointment.getDoctor().getId(), appointmentDto.getId());
    }
}

package com.petdoctor.domain.service;

import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.data.entity.AppointmentState;
import com.petdoctor.data.entity.ClientEntity;
import com.petdoctor.data.entity.DoctorEntity;
import com.petdoctor.data.repository.AppointmentRepository;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.dto.VetClinicDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.service.impl.AppointmentServiceImpl;
import com.petdoctor.domain.tool.mapper.Mapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AppointmentServiceTest {

    @MockBean
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Test
    public void saveAppointment() {

        var doctorEntity = new DoctorEntity(1L,
                "Alex",
                "Bosov",
                "doc@gmail.com",
                1,
                new ArrayList<>(),
                null);

        var clientEntity = new ClientEntity(1L,
                "Oleg",
                "Podik",
                "lol@gmail.com",
                "sobaka",
                "bolit",
                new ArrayList<>());

        var appointmentEntity = new AppointmentEntity(1L,
                LocalDate.now(),
                AppointmentState.OPEN,
                clientEntity,
                doctorEntity);

        Mockito.when(appointmentRepository.save(Mockito.any(AppointmentEntity.class))).thenReturn(appointmentEntity);

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

        AppointmentDto savedAppointmentDto = appointmentService.saveAppointment(appointmentDto);
        assertEquals(savedAppointmentDto.getId(), appointmentDto.getId());
    }

    @Test
    public void findAllAppointment() {
        var appointmentEntity1 = new AppointmentEntity(1L,
                LocalDate.now(),
                AppointmentState.OPEN,
                new ClientEntity(),
                new DoctorEntity());

        var appointmentEntity2 = new AppointmentEntity(2L,
                LocalDate.now(),
                AppointmentState.CLOSED,
                new ClientEntity(),
                new DoctorEntity());

        var appointmentEntity3 = new AppointmentEntity(3L,
                LocalDate.now(),
                AppointmentState.CANCELED,
                new ClientEntity(),
                new DoctorEntity());


        Mockito.when(appointmentRepository
                .findAll()).thenReturn(List.of(appointmentEntity1, appointmentEntity2, appointmentEntity3));

        List<AppointmentDto> appointmentDtos = appointmentService.findAllAppointment();
        assertEquals(appointmentDtos.get(0).getId(), appointmentEntity1.getId());
        assertEquals(appointmentDtos.get(1).getId(), appointmentEntity2.getId());
        assertEquals(appointmentDtos.get(2).getId(), appointmentEntity3.getId());
    }

    @Test
    public void getAppointmentById() {
        var appointmentEntity = new AppointmentEntity(1L,
                LocalDate.now(),
                AppointmentState.OPEN,
                new ClientEntity(),
                new DoctorEntity());

        Mockito.when(appointmentRepository.getReferenceById(1L)).thenReturn(appointmentEntity);
        AppointmentDto appointmentDto = appointmentService.getAppointmentById(1L);

        assertEquals(appointmentDto.getId(), appointmentEntity.getId());
        assertEquals(appointmentDto.getStartTime(), appointmentEntity.getStartTime());
        assertEquals(appointmentDto.getAppointmentState(), appointmentEntity.getAppointmentState());
    }

    @Test
    public void updateAppointment() {
        var appointmentEntity = new AppointmentEntity(1L,
                LocalDate.now(),
                AppointmentState.OPEN,
                new ClientEntity(),
                new DoctorEntity());

        Mockito.when(appointmentRepository.getReferenceById(1L)).thenReturn(appointmentEntity);
        LocalDate localDate = LocalDate.of(2011, 1, 12);

        var repositoryReturnedEntity = new AppointmentEntity(1L,
                localDate,
                AppointmentState.OPEN,
                null,
                null);

        Mockito.when(appointmentRepository.save(Mockito.any(AppointmentEntity.class))).thenReturn(repositoryReturnedEntity);

        var newAppointmentDto = new AppointmentDto(1L,
                localDate,
                null,
                null,
                null,
                null);

        AppointmentDto updatedAppointmentDto = appointmentService.updateAppointment(newAppointmentDto);
        assertEquals(appointmentEntity.getId(), updatedAppointmentDto.getId());
        assertEquals(appointmentEntity.getAppointmentState(), updatedAppointmentDto.getAppointmentState());
        assertEquals(localDate, updatedAppointmentDto.getStartTime());
    }

    @Test
    public void deleteAppointmentById() {
        appointmentService.deleteAppointmentById(1L);
        Mockito.verify(appointmentRepository, Mockito.times(1)).deleteById(1L);
    }
}

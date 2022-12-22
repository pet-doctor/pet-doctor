package com.petdoctor.domain.service;

import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.data.entity.AppointmentState;
import com.petdoctor.data.entity.DoctorEntity;
import com.petdoctor.data.repository.DoctorRepository;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.service.impl.DoctorServiceImpl;
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
public class DoctorServiceTest {
    @MockBean
    DoctorRepository doctorRepository;

    @Autowired
    DoctorServiceImpl doctorService;

    @Test
    public void saveDoctor() {
        var doctorEntity = new DoctorEntity(1L,
                "Alex",
                "Bosoc",
                "genius@gmail.com",
                2,
                new ArrayList<>(),
                null);
        Mockito.when(doctorRepository.save(Mockito.any(DoctorEntity.class))).thenReturn(doctorEntity);

        var expectedDoctorId = 1L;
        var expectedDoctorName = "Alex";
        var expectedDoctorSurname = "Bosoc";
        var expectedDoctorEmail = "genius@gmail.com";


        var doctorDto = new DoctorDto(1L,
                "Alex",
                "Bosoc",
                "genius@gmail.com",
                2,
                new ArrayList<>());

        DoctorDto savedDoctorDto = doctorService.saveDoctor(doctorDto);

        assertEquals(expectedDoctorId, savedDoctorDto.getId());
        assertEquals(expectedDoctorName, savedDoctorDto.getName());
        assertEquals(expectedDoctorSurname, savedDoctorDto.getSurname());
        assertEquals(expectedDoctorEmail, savedDoctorDto.getEmail());
    }

    @Test
    public void findAllDoctor() {
        var doctorEntity1 = new DoctorEntity(1L,
                "Alex",
                "Bosoc",
                "genius@gmail.com",
                2,
                new ArrayList<>(),
                null);
        var doctorEntity2 = new DoctorEntity(2L,
                "Oled",
                "Amoled",
                "bagguy@gmail.com",
                5,
                new ArrayList<>(),
                null);
        var doctorEntity3 = new DoctorEntity(3L,
                "Rostislave",
                "Chiller",
                "normalperson@gmail.com",
                3,
                new ArrayList<>(),
                null);
        Mockito.when(doctorRepository.findAll()).thenReturn(List.of(doctorEntity1, doctorEntity2, doctorEntity3));

        var expectedDoctorId1 = 1L;
        var expectedDoctorName1 = "Alex";
        var expectedDoctorSurname1 = "Bosoc";
        var expectedDoctorEmail1 = "genius@gmail.com";

        var expectedDoctorId2 = 2L;
        var expectedDoctorName2 = "Oled";
        var expectedDoctorSurname2 = "Amoled";
        var expectedDoctorEmail2 = "bagguy@gmail.com";

        var expectedDoctorId3 = 3L;
        var expectedDoctorName3 = "Rostislave";
        var expectedDoctorSurname3 = "Chiller";
        var expectedDoctorEmail3 = "normalperson@gmail.com";

        List<DoctorDto> doctorDtos = doctorService.findAllDoctor();

        assertEquals(expectedDoctorId1, doctorDtos.get(0).getId());
        assertEquals(expectedDoctorName1, doctorDtos.get(0).getName());
        assertEquals(expectedDoctorSurname1, doctorDtos.get(0).getSurname());
        assertEquals(expectedDoctorEmail1, doctorDtos.get(0).getEmail());

        assertEquals(expectedDoctorId2, doctorDtos.get(1).getId());
        assertEquals(expectedDoctorName2, doctorDtos.get(1).getName());
        assertEquals(expectedDoctorSurname2, doctorDtos.get(1).getSurname());
        assertEquals(expectedDoctorEmail2, doctorDtos.get(1).getEmail());

        assertEquals(expectedDoctorId3, doctorDtos.get(2).getId());
        assertEquals(expectedDoctorName3, doctorDtos.get(2).getName());
        assertEquals(expectedDoctorSurname3, doctorDtos.get(2).getSurname());
        assertEquals(expectedDoctorEmail3, doctorDtos.get(2).getEmail());
    }

    @Test
    public void getDoctorById() {
        var doctorEntity = new DoctorEntity(1L,
                "Alex",
                "Bosoc",
                "genius@gmail.com",
                2,
                new ArrayList<>(),
                null);
        Mockito.when(doctorRepository.getReferenceById(1L)).thenReturn(doctorEntity);

        var expectedDoctorId = 1L;
        var expectedDoctorName = "Alex";
        var expectedDoctorSurname = "Bosoc";
        var expectedDoctorEmail = "genius@gmail.com";

        DoctorDto doctorDto = doctorService.getDoctorById(1L);

        assertEquals(expectedDoctorId, doctorDto.getId());
        assertEquals(expectedDoctorName, doctorDto.getName());
        assertEquals(expectedDoctorSurname, doctorDto.getSurname());
        assertEquals(expectedDoctorEmail, doctorDto.getEmail());
    }

    @Test
    public void updateDoctor() {
        var doctorEntity = new DoctorEntity(1L,
                "Alex",
                "Bosoc",
                "genius@gmail.com",
                2,
                new ArrayList<>(),
                null);

        Mockito.when(doctorRepository.getReferenceById(1L)).thenReturn(doctorEntity);
        var expectedDoctor = new DoctorEntity(1L,
                "Oled",
                "Display",
                "genius@gmail.com",
                4, new ArrayList<>(),
                null);

        Mockito.when(doctorRepository.save(Mockito.any(DoctorEntity.class))).thenReturn(expectedDoctor);

        var expectedDoctorId = 1L;
        var expectedDoctorName = "Oled";
        var expectedDoctorSurname = "Display";
        var expectedDoctorEmail = "genius@gmail.com";
        var expectedDoctorDoctorOffice = 4;

        var newDoctorDto = new DoctorDto(1L,
                "Oled",
                "Display",
                null,
                4,
                new ArrayList<>());

        DoctorDto doctorDto = doctorService.updateDoctor(newDoctorDto);

        assertEquals(expectedDoctorId, doctorDto.getId());
        assertEquals(expectedDoctorName, doctorDto.getName());
        assertEquals(expectedDoctorSurname, doctorDto.getSurname());
        assertEquals(expectedDoctorEmail, doctorDto.getEmail());
        assertEquals(expectedDoctorDoctorOffice, doctorDto.getDoctorOffice());
    }

    @Test
    public void deleteDoctorById() {
        doctorService.deleteDoctorById(1L);
        Mockito.verify(doctorRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void bookAppointment() {
        var appointmentEntity = new AppointmentEntity(1L,
                LocalDate.now(),
                AppointmentState.OPEN,
                null,
                null);

        var doctorEntity = new DoctorEntity(1L,
                "Alex",
                "Bosoc",
                "genius@gmail.com",
                2,
                List.of(appointmentEntity),
                null);

        Mockito.when(doctorRepository.getReferenceById(1L)).thenReturn(doctorEntity);
        DoctorDto doctorDto = doctorService.getDoctorById(1L);

        AppointmentState expectedAppointmentState = AppointmentState.TAKEN;

        var appointmentDto = new AppointmentDto(1L,
                LocalDate.now(),
                AppointmentState.OPEN,
                null,
                doctorDto,
                null);

        AppointmentDto bookedAppointmentDto = doctorService.bookAppointment(appointmentDto);

        assertEquals(expectedAppointmentState, bookedAppointmentDto.getAppointmentState());
    }

    @Test
    public void closeAppointment() {
        var appointmentEntity = new AppointmentEntity(1L,
                LocalDate.now(),
                AppointmentState.OPEN,
                null,
                null);

        var doctorEntity = new DoctorEntity(1L,
                "Alex",
                "Bosoc",
                "genius@gmail.com",
                2,
                List.of(appointmentEntity),
                null);

        Mockito.when(doctorRepository.getReferenceById(1L)).thenReturn(doctorEntity);
        DoctorDto doctorDto = doctorService.getDoctorById(1L);

        AppointmentState expectedAppointmentState = AppointmentState.CLOSED;

        var appointmentDto = new AppointmentDto(1L,
                LocalDate.now(),
                AppointmentState.CLOSED,
                null,
                doctorDto,
                null);

        AppointmentDto bookedAppointmentDto = doctorService.closeAppointment(appointmentDto);

        assertEquals(expectedAppointmentState, bookedAppointmentDto.getAppointmentState());
    }
}
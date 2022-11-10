package com.petdoctor.domain;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AppointmentServiceTest {

    @MockBean
    private AppointmentRepository appointmentRepository;

    @Autowired
    private Mapper<AppointmentEntity, Appointment, AppointmentDto> appointmentMapper;

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
}

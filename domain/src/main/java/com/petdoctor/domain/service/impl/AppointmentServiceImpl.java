package com.petdoctor.domain.service.impl;

import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.data.entity.AppointmentState;
import com.petdoctor.data.repository.AppointmentRepository;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.dto.VetClinicDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.service.AppointmentService;
import com.petdoctor.domain.tool.exception.PetDoctorNotFoundException;
import com.petdoctor.domain.tool.exception.PetDoctorNullException;
import com.petdoctor.domain.tool.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final Mapper<AppointmentEntity, Appointment, AppointmentDto> appointmentMapper;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  Mapper<AppointmentEntity, Appointment, AppointmentDto> appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public List<AppointmentDto> findAllAppointment() {
        List<AppointmentInfo> appointmentInfos = appointmentRepository
                .findAll()
                .stream()
                .map(appointmentEntity -> (AppointmentInfo) appointmentMapper.toModelFromEntity(appointmentEntity))
                .toList();

        return appointmentInfos
                .stream()
                .map(a -> appointmentMapper.toDtoFromModel((Appointment) a))
                .toList();
    }

    @Override
    public AppointmentDto getAppointmentById(Long id) {
        if (id == null) {
            throw new PetDoctorNullException("id is null!");
        }

        try {
            AppointmentInfo appointmentInfo = appointmentMapper.toModelFromEntity(appointmentRepository.getReferenceById(id));
            return appointmentMapper.toDtoFromModel((Appointment) appointmentInfo);
        } catch (EntityNotFoundException e) {
            throw new PetDoctorNotFoundException(id.toString());
        }
    }

    @Override
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        if (appointmentDto == null) {
            throw new PetDoctorNullException("the appointmentDto is null!");
        }

        AppointmentInfo appointmentInfo = appointmentMapper.toModelFromDto(appointmentDto);
        var appointmentEntity =
                appointmentRepository.save(appointmentMapper.toEntityFromModel((Appointment) appointmentInfo));
        return appointmentMapper.toDtoFromModel(appointmentMapper.toModelFromEntity(appointmentEntity));
    }

    @Override
    public AppointmentDto updateAppointment(AppointmentDto appointmentDto) {
        if (appointmentDto == null) {
            throw new PetDoctorNullException("the appointmentDto is null!");
        }

        try {
            AppointmentDto oldAppointment = this.getAppointmentById(appointmentDto.getId());
            LocalDate startTime = oldAppointment.getStartTime();
            AppointmentState appointmentState = oldAppointment.getAppointmentState();
            ClientDto clientDto = oldAppointment.getClientDto();
            DoctorDto doctorDto = oldAppointment.getDoctorDto();
            VetClinicDto vetClinicDto = oldAppointment.getVetClinicDto();

            if (appointmentDto.getStartTime() != null) {
                startTime = appointmentDto.getStartTime();
            }

            if (appointmentDto.getAppointmentState() != null) {
                appointmentState = appointmentDto.getAppointmentState();
            }

            if (appointmentDto.getClientDto() != null) {
                clientDto = appointmentDto.getClientDto();
            }

            if (appointmentDto.getDoctorDto() != null) {
                doctorDto = appointmentDto.getDoctorDto();
            }

            if (appointmentDto.getVetClinicDto() != null) {
                vetClinicDto = appointmentDto.getVetClinicDto();
            }
            var updatedAppointmentDto =
                    new AppointmentDto(oldAppointment.getId(),
                            startTime,
                            appointmentState,
                            clientDto,
                            doctorDto,
                            vetClinicDto);

            AppointmentInfo updatedAppointmentInfo =
                    appointmentMapper.toModelFromDto(updatedAppointmentDto);

            var updatedAppointmentEntity =
                    appointmentRepository.save(appointmentMapper.toEntityFromModel((Appointment) updatedAppointmentInfo));
            return appointmentMapper.toDtoFromModel(appointmentMapper.toModelFromEntity(updatedAppointmentEntity));
        } catch (Exception e) {
            throw new PetDoctorNotFoundException(appointmentDto.getId().toString());
        }
    }

    @Override
    public void deleteAppointmentById(Long id) {
        if (id == null) {
            throw new PetDoctorNullException("id is null!");
        }
        try {
            appointmentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PetDoctorNotFoundException(id.toString());
        }
    }
}
package com.petdoctor.domain.service.impl;

import com.petdoctor.data.repository.AppointmentRepository;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.data.entity.AppointmentEntity;
import com.petdoctor.domain.dto.ClientDto;
import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.dto.VetClinicDto;
import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.appointment.AppointmentState;
import com.petdoctor.domain.service.AppointmentService;
import com.petdoctor.domain.tool.exception.PetDoctorNotFoundException;
import com.petdoctor.domain.tool.exception.PetDoctorNullException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;
    private ModelMapper modelMapper;
    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AppointmentDto> findAllAppointment() {
        List<AppointmentInfo> appointmentInfos = appointmentRepository
                .findAll()
                .stream()
                .map(a -> modelMapper.map(a, AppointmentInfo.class))
                .toList();

        return appointmentInfos
                .stream()
                .map(a -> modelMapper.map(a, AppointmentDto.class))
                .toList();
    }

    @Override
    public AppointmentDto getAppointmentById(Long id) {
        if (id == null) {
            throw new PetDoctorNullException("id is null!");
        }

        try {
            AppointmentInfo appointmentInfo =
                    modelMapper.map(appointmentRepository.getReferenceById(id), AppointmentInfo.class);
            return modelMapper.map(appointmentInfo, AppointmentDto.class);
        } catch (EntityNotFoundException e) {
            throw new PetDoctorNotFoundException(id.toString());
        }
    }

    @Override
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        if (appointmentDto == null) {
            throw new PetDoctorNullException("the appointmentDto is null!");
        }

        AppointmentInfo appointmentInfo =
                modelMapper.map(appointmentDto, AppointmentInfo.class);

        var appointmentEntity =
                appointmentRepository.save(modelMapper.map(appointmentInfo, AppointmentEntity.class));
        return modelMapper.map(modelMapper.map(appointmentEntity, AppointmentInfo.class), AppointmentDto.class);
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
            ClientDto clientDto = oldAppointment.getClient();
            DoctorDto doctorDto = oldAppointment.getDoctorDto();
            VetClinicDto vetClinicDto = oldAppointment.getVetClinicDto();

            if (appointmentDto.getStartTime() != null) {
                startTime = appointmentDto.getStartTime();
            }

            if (appointmentDto.getAppointmentState() != null) {
                appointmentState = appointmentDto.getAppointmentState();
            }

            if (appointmentDto.getClient() != null) {
                clientDto = appointmentDto.getClient();
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
                    modelMapper.map(updatedAppointmentDto, AppointmentInfo.class);

            var updatedAppointmentEntity =
                    appointmentRepository.save(modelMapper.map(updatedAppointmentInfo, AppointmentEntity.class));
            return modelMapper.map(modelMapper.map(updatedAppointmentEntity, AppointmentInfo.class), AppointmentDto.class);
        } catch (Exception e) {
            throw new PetDoctorNotFoundException(appointmentDto.getId().toString());
        }
    }

    @Override
    public void deleteAppointmentById(Long id) {
        if (id == null) {
            throw new PetDoctorNullException("id is null!");
        }

        appointmentRepository.deleteById(id);
    }
}
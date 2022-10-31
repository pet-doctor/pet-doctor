package com.petdoctor.domain.service.impl;

import com.petdoctor.data.repository.AppointmentRepository;
import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.entity.AppointmentEntity;
import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        AppointmentInfo appointmentInfo =
                modelMapper.map(appointmentRepository.getReferenceById(id), AppointmentInfo.class);

        return modelMapper.map(appointmentInfo, AppointmentDto.class);
    }

    @Override
    public AppointmentDto saveAppointment(AppointmentDto appointmentDto) {
        AppointmentInfo appointmentInfo =
                modelMapper.map(appointmentDto, AppointmentInfo.class);
        appointmentRepository.save(modelMapper.map(appointmentInfo, AppointmentEntity.class));
        return appointmentDto;
    }

    @Override
    public AppointmentDto updateAppointment(AppointmentDto appointmentDto) {
        return null;
    }

    @Override
    public void deleteAppointmentById(Long id) {
        appointmentRepository.deleteById(id);
    }
}

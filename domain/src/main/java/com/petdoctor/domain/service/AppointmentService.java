package com.petdoctor.domain.service;


import com.petdoctor.domain.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    List<AppointmentDto> findAllAppointment();

    AppointmentDto getAppointmentById(Long id);

    AppointmentDto saveAppointment(AppointmentDto appointmentDto);

    AppointmentDto updateAppointment(AppointmentDto appointmentDto);

    void deleteAppointmentById(Long id);
}

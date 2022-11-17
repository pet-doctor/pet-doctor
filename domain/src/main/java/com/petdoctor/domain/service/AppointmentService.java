package com.petdoctor.domain.service;


import com.petdoctor.domain.dto.AppointmentDto;

import java.util.List;

public interface AppointmentService {
    /**
     * @return List of DTO of all Appointments
     */
    List<AppointmentDto> findAllAppointment();

    /**
     * @param id Appointment ID
     * @return Immutable DTO of Appointment
     */
    AppointmentDto getAppointmentById(Long id);

    /**
     * @param appointmentDto Immutable DTO of Appointment
     * @return Saved to Database DTO of Appointment
     */
    AppointmentDto saveAppointment(AppointmentDto appointmentDto);

    /**
     * @param appointmentDto Immutable DTO of Appointment
     * @return Updated to Database DTO of Appointment
     */
    AppointmentDto updateAppointment(AppointmentDto appointmentDto);

    /**
     * @param id Appointment ID
     */
    void deleteAppointmentById(Long id);
}

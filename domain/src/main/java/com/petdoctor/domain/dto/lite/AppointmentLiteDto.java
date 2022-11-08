package com.petdoctor.domain.dto.lite;

import com.petdoctor.data.entity.AppointmentState;

import java.time.LocalDate;

public class AppointmentLiteDto {
    final private Long id;
    final private LocalDate startTime;
    final private AppointmentState appointmentState;

    public AppointmentLiteDto(Long id,
                              LocalDate startTime,
                              AppointmentState appointmentState) {
        this.id = id;
        this.startTime = startTime;
        this.appointmentState = appointmentState;

    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public AppointmentState getAppointmentState() {
        return appointmentState;
    }
}

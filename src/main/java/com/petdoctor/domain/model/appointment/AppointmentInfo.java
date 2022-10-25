package com.petdoctor.domain.model.appointment;

import java.time.LocalDate;

public interface AppointmentInfo {
    public Long getId();
    public LocalDate getStartTime();
    public AppointmentState getAppointmentState();
}
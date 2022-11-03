package com.petdoctor.domain.model.appointment;

import com.petdoctor.domain.model.client.ClientInfo;
import com.petdoctor.domain.model.doctor.DoctorInfo;
import com.petdoctor.domain.model.vet.clinic.VetClinicInfo;

import java.time.LocalDate;

public class Appointment implements AppointmentInterface {

    private Long id;
    private LocalDate startTime;
    private AppointmentState appointmentState;
    private ClientInfo client;
    private DoctorInfo doctor;
    private VetClinicInfo vetClinic;

    public Appointment() {
    }

    /**
     * @param id (Long) - unique identifier in the database
     * @param startTime (LocalDate) - time when the appointment will be getting started
     * @param appointmentState (enum) - current state of the appointment (OPEN, TAKEN, CANCELED, CLOSED)
     * @param client (ClientInfo) - information about client
     * @param doctor (DoctorInfo) - information about doctor
     * @param vetClinic (VetClinicInfo) - information about vet clinic
     */
    public Appointment(Long id, LocalDate startTime, AppointmentState appointmentState,
                       ClientInfo client, DoctorInfo doctor, VetClinicInfo vetClinic) {
        this.id = id;
        this.startTime = startTime;
        this.appointmentState = appointmentState;
        this.client = client;
        this.doctor = doctor;
        this.vetClinic = vetClinic;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public LocalDate getStartTime() {
        return this.startTime;
    }

    @Override
    public AppointmentState getAppointmentState() {
        return this.appointmentState;
    }

    @Override
    public ClientInfo getClient() {
        return this.client;
    }

    @Override
    public DoctorInfo getDoctor() {
        return this.doctor;
    }

    @Override
    public VetClinicInfo getVetClinic() {
        return this.vetClinic;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    @Override
    public void setAppointmentState(AppointmentState appointmentState) {
        this.appointmentState = appointmentState;
    }

    @Override
    public void setClient(ClientInfo client) {
        this.client = client;
    }

    @Override
    public void setDoctor(DoctorInfo doctor) {
        this.doctor = doctor;
    }

    @Override
    public void setVetClinic(VetClinicInfo vetClinic) {
        this.vetClinic = vetClinic;
    }
}

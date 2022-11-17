package com.petdoctor.domain.model.vet.clinic;

import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.client.ClientInfo;
import com.petdoctor.domain.model.client.ClientInterface;
import com.petdoctor.domain.model.doctor.DoctorInfo;
import com.petdoctor.domain.model.doctor.DoctorInterface;
import com.petdoctor.domain.tool.exception.PetDoctorNullException;
import com.petdoctor.domain.tool.exception.PetDoctorValidationException;

import java.util.HashMap;
import java.util.Map;

public class VetClinic implements VetClinicInterface {
    private Long id;
    private String address;
    private String email;
    private HashMap<Long, DoctorInterface> doctors;
    private HashMap<Long, ClientInterface> clients;

    public VetClinic() {
    }

    /**
     * @param id      (Long) - unique identifier in the database
     * @param address (String) - address of vet clinic
     * @param email   (String) - email of vet clinic
     * @param doctors (Map Long, DoctorInterface) - information about doctors, that are mapped with their ids'
     * @param clients (Map Long, ClientInterface) - information about clients, that are mapped with their ids'
     */
    public VetClinic(Long id, String address, String email, HashMap<Long, DoctorInterface> doctors, HashMap<Long, ClientInterface> clients) {
        this.id = id;
        this.address = address;
        this.email = email;
        this.doctors = doctors;
        this.clients = clients;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public Map<Long, DoctorInfo> getDoctors() {
        return Map.copyOf(this.doctors);
    }

    @Override
    public Map<Long, ClientInfo> getClients() {
        return Map.copyOf(this.clients);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setDoctors(HashMap<Long, DoctorInterface> doctors) {
        this.doctors = doctors;
    }

    @Override
    public void setClients(HashMap<Long, ClientInterface> clients) {
        this.clients = clients;
    }

    /**
     * @param appointmentInfo (AppointmentInfo) - information about appointment
     * @return (AppointmentInfo) after appointment is closed
     */
    @Override
    public AppointmentInfo closeAppointment(AppointmentInfo appointmentInfo) {
        if (appointmentInfo == null) {
            throw new PetDoctorNullException("AppointmentInfo is null!");
        }

        if (!(appointmentInfo.getDoctor() instanceof DoctorInterface)) {
            throw new PetDoctorValidationException("Incorrect type of AppointmentInfo was taken");
        }

        DoctorInterface doctor = doctors.get(appointmentInfo.getDoctor().getId());

        if (doctor == null) {
            throw new PetDoctorNullException("Related doctor isn't exist");
        }

        return doctor.closeAppointment(appointmentInfo);
    }

    /**
     * @param appointmentInfo (AppointmentInfo) - information about appointment
     * @return (AppointmentInfo) after appointment is booked
     */
    @Override
    public AppointmentInfo bookAppointment(AppointmentInfo appointmentInfo) {
        if (appointmentInfo == null) {
            throw new PetDoctorNullException("AppointmentInfo is null!");
        }

        if (!(appointmentInfo.getDoctor() instanceof DoctorInterface)) {
            throw new PetDoctorValidationException("Incorrect type of AppointmentInfo was taken");
        }

        DoctorInterface doctor = doctors.get(appointmentInfo.getDoctor().getId());

        if (doctor == null) {
            throw new PetDoctorNullException("Related doctor isn't exist");
        }

        return doctor.bookAppointment(appointmentInfo);
    }
}

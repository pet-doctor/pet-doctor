package com.petdoctor.domain.model.doctor;

import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.appointment.AppointmentInterface;
import com.petdoctor.data.entity.AppointmentState;
import com.petdoctor.domain.tool.exception.PetDoctorValidationException;

import java.util.HashMap;
import java.util.Map;

public class Doctor implements DoctorInterface {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private Integer doctorOffice;
    private HashMap<Long, AppointmentInterface> appointments = new HashMap<>();

    public Doctor() {
    }

    /**
     * @param id (Long) - unique identifier in the database
     * @param name (String) - name of the doctor
     * @param surname (String) - surname of the doctor
     * @param email (String) - email of the doctor
     * @param doctorOffice (Integer) - number of the doctor's office
     * @param appointments (Map Long, AppointmentInfo) - appointments that are related to current doctor
     */
    public Doctor(Long id, String name, String surname, String email, Integer doctorOffice, HashMap<Long, AppointmentInterface> appointments) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.doctorOffice = doctorOffice;
        this.appointments = appointments;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public Integer getDoctorOffice() {
        return this.doctorOffice;
    }

    @Override
    public HashMap<Long, AppointmentInfo> getAppointments() {
        return new HashMap<>(this.appointments);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setDoctorOffice(Integer doctorOffice) {
        this.doctorOffice = doctorOffice;
    }

    @Override
    public void setAppointments(HashMap<Long, AppointmentInterface> appointments) {
        this.appointments = appointments;
    }

    /**
     * @param appointmentInfo (AppointmentInfo) - abstraction of the Appointment that is only able to get fields
     * @return AppointmentInfo, after our appointment has been added by doctor
     */
    @Override
    public AppointmentInfo addAppointment(AppointmentInfo appointmentInfo) {

        if (!(appointmentInfo instanceof AppointmentInterface)) {
            throw new PetDoctorValidationException("Incorrect type of Appointment was taken");
        }

        if (appointments.containsKey(appointmentInfo.getId())) {
            throw new PetDoctorValidationException("Appointment has already added");
        }

        appointments.put(appointmentInfo.getId(), (AppointmentInterface) appointmentInfo);
        return appointmentInfo;
    }

    /**
     * @param appointmentInfo (AppointmentInfo) - abstraction of the Appointment that is only able to get fields
     * @return AppointmentInfo, after our appointment has been registered by doctor
     */
    @Override
    public AppointmentInfo bookAppointment(AppointmentInfo appointmentInfo) {

        if (!(appointmentInfo instanceof AppointmentInterface)) {
            throw new PetDoctorValidationException("Incorrect type of Appointment was taken");
        }

        if (!appointments.containsKey(appointmentInfo.getId())) {
            throw new PetDoctorValidationException("Appointments doesn't contains the appointment");
        }

        ((AppointmentInterface) appointmentInfo).setAppointmentState(AppointmentState.TAKEN);
        appointments.get(appointmentInfo.getId()).setAppointmentState(AppointmentState.TAKEN);
        return appointmentInfo;
    }

    /**
     * @param appointmentInfo (AppointmentInfo) - abstraction of the Appointment that is only able to get fields
     * @return AppointmentInfo, after our appointment has been closed by doctor
     */
    @Override
    public AppointmentInfo closeAppointment(AppointmentInfo appointmentInfo) {

        if (!(appointmentInfo instanceof AppointmentInterface)) {
            throw new PetDoctorValidationException("Incorrect type of Appointment was taken");
        }

        if (!appointments.containsKey(appointmentInfo.getId())) {
            throw new PetDoctorValidationException("Appointments doesn't contains the appointment");
        }

        ((AppointmentInterface) appointmentInfo).setAppointmentState(AppointmentState.CLOSED);
        appointments.get(appointmentInfo.getId()).setAppointmentState(AppointmentState.CLOSED);
        return appointmentInfo;
    }

    /**
     * @param appointmentInfo (AppointmentInfo) - abstraction of the Appointment that is only able to get fields
     */
    @Override
    public void deleteAppointment(AppointmentInfo appointmentInfo) {

        if (!(appointmentInfo instanceof AppointmentInterface)) {
            throw new PetDoctorValidationException("Incorrect type of Appointment was taken");
        }

        if (!appointments.containsKey(appointmentInfo.getId())) {
            throw new PetDoctorValidationException("Appointments doesn't contains the appointment");
        }

        appointments.remove(appointmentInfo.getId());
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
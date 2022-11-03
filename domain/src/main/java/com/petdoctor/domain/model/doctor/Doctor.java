package com.petdoctor.domain.model.doctor;

import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.appointment.AppointmentInterface;
import com.petdoctor.domain.model.appointment.AppointmentState;
import com.petdoctor.domain.tool.exception.PetDoctorValidationException;
import com.petdoctor.domain.tool.exception.PetDoctorValidationException;

import java.util.Map;

public class Doctor implements DoctorInterface {

    private Long id;
    private String name;
    private String email;
    private Integer doctorOffice;
    private Map<Long, AppointmentInterface> appointments;

    public Doctor() {
    }

    public Doctor(Long id, String name, String email, Integer doctorOffice, Map<Long, AppointmentInterface> appointments) {
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
    public Map<Long, AppointmentInfo> getAppointments() {
        return Map.copyOf(this.appointments);
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
    public void setAppointments(Map<Long, AppointmentInterface> appointments) {
        this.appointments = appointments;
    }

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
}
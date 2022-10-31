package com.petdoctor.domain.service.impl;

import com.petdoctor.domain.dto.VetClinicDto;
import com.petdoctor.domain.model.appointment.Appointment;
import com.petdoctor.domain.model.appointment.AppointmentInfo;
import com.petdoctor.domain.model.doctor.DoctorInfo;
import com.petdoctor.domain.model.vet.clinic.VetClinicInfo;
import com.petdoctor.domain.model.vet.clinic.VetClinicInterface;
import com.petdoctor.domain.service.DoctorService;
import com.petdoctor.domain.service.VetClinicService;
import com.petdoctor.domain.tool.exception.VetClinicNullException;
import com.petdoctor.domain.tool.exception.VetClinicValidationException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class VetClinicServiceImpl implements VetClinicService {

    private DoctorService doctorService;
//    TODO: implement repositories
//    private VetClinicRepository vetClinicRepository;

    public AppointmentInfo bookAppointment(AppointmentInfo appointment) {

        if (appointment == null) {
            throw new VetClinicNullException("Appointment is null! :(");
        }

        if (!(appointment.getVetClinic() instanceof VetClinicInterface vetClinic)) {
            throw new VetClinicValidationException("Incorrect type of vetClinic was taken");
        }

//        TODO: add mapping with @Entities

        return vetClinic.bookAppointment(appointment);
    }

    @Override
    public VetClinicDto getVetClinicById(Long id) {
        return null;
    }

    @Override
    public VetClinicDto saveVetClinic(VetClinicDto vetClinicDto) {
        return null;
    }

    @Override
    public VetClinicDto updateVetClinic(VetClinicDto vetClinicDto) {
        return null;
    }

    @Override
    public void deleteVetClinicById(Long id) {

    }
}

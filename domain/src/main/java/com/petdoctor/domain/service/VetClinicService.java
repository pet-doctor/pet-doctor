package com.petdoctor.domain.service;

import com.petdoctor.domain.dto.AppointmentDto;
import com.petdoctor.domain.dto.VetClinicDto;

import java.util.List;

public interface VetClinicService {
    /**
     * @return List of VetClinic
     */
    List<VetClinicDto> findAllVetClinic();


    /**
     * @param id VetClinic ID
     * @return Immutable DTO of VetClinic
     */
    VetClinicDto getVetClinicById(Long id);

    /**
     * @param vetClinicDto Immutable DTO of VetClinic
     * @return Saved to Database DTO of VetClinic
     */
    VetClinicDto saveVetClinic(VetClinicDto vetClinicDto);

    /**
     * @param vetClinicDto Immutable DTO of VetClinic
     * @return Updated to Database DTO of VetClinic
     */
    VetClinicDto updateVetClinic(VetClinicDto vetClinicDto);

    /**
     * @param id VetClinic ID
     */
    void deleteVetClinicById(Long id);

    AppointmentDto bookAppointment(AppointmentDto appointmentDto);

    AppointmentDto closeAppointment(AppointmentDto appointmentDto);
}

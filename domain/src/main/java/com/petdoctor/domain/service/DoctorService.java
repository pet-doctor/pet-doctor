package com.petdoctor.domain.service;

import com.petdoctor.domain.dto.DoctorDto;

public interface DoctorService {

    /**
     * @param id Doctor ID
     * @return Immutable DTO of Doctor
     */
    DoctorDto getDoctorById(Long id);

    /**
     * @param doctorDto Immutable DTO of Doctor
     * @return Saved to Database DTO of Doctor
     */
    DoctorDto saveDoctor(DoctorDto doctorDto);

    /**
     * @param doctorDto Immutable DTO of Doctor
     * @return Updated to Database DTO of Doctor
     */
    DoctorDto updateDoctor(DoctorDto doctorDto);

    /**
     * @param id Doctor ID
     */
    void deleteDoctorById(Long id);
}

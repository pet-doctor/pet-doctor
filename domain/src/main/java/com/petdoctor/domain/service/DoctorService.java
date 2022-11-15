package com.petdoctor.domain.service;

import com.petdoctor.domain.dto.DoctorDto;

import java.util.List;

public interface DoctorService {

    /**
     * @return List<DoctorDto>
     */
    List<DoctorDto> findAllDoctor();

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

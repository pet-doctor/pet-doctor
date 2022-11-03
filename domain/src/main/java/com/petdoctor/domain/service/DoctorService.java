package com.petdoctor.domain.service;

import com.petdoctor.domain.dto.DoctorDto;

public interface DoctorService {

    DoctorDto getDoctorById(Long id);

    DoctorDto saveDoctor(DoctorDto doctorDto);

    DoctorDto updateDoctor(DoctorDto doctorDto);

    void deleteDoctorById(Long id);
}

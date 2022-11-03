package com.petdoctor.domain.service;

import com.petdoctor.domain.dto.VetClinicDto;

public interface VetClinicService {
    VetClinicDto getVetClinicById(Long id);

    VetClinicDto saveVetClinic(VetClinicDto vetClinicDto);

    VetClinicDto updateVetClinic(VetClinicDto vetClinicDto);

    void deleteVetClinicById(Long id);
}

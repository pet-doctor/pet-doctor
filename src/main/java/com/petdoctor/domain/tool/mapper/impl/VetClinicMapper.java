package com.petdoctor.domain.tool.mapper.impl;

import com.petdoctor.data.entity.VetClinicEntity;
import com.petdoctor.domain.dto.VetClinicDto;
import com.petdoctor.domain.model.vet.clinic.VetClinic;
import com.petdoctor.domain.tool.mapper.AbstractMapper;
import org.springframework.stereotype.Component;

@Component
public class VetClinicMapper extends AbstractMapper<VetClinicEntity, VetClinic, VetClinicDto> {
    VetClinicMapper(Class<VetClinicEntity> entityClass, Class<VetClinic> modelClass, Class<VetClinicDto> dtoClass) {
        super(entityClass, modelClass, dtoClass);
    }

    @Override
    protected void mapSpecificFieldsToModelFromEntity(VetClinicEntity source, VetClinic destination) {

    }

    @Override
    protected void mapSpecificFieldsToEntityFromModel(VetClinic source, VetClinicEntity destination) {

    }

    @Override
    protected void mapSpecificFieldsToDtoFromModel(VetClinic source, VetClinicDto destination) {

    }

    @Override
    protected void mapSpecificFieldsToModelFromDto(VetClinicDto source, VetClinic destination) {

    }

}

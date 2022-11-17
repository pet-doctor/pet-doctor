package com.petdoctor.domain.tool.mapper;

public interface Mapper<E, M, D> {
    E toEntityFromModel(M model);

    M toModelFromEntity(E entity);

    M toModelFromDto(D dto);

    D toDtoFromModel(M model);
}

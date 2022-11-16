package com.petdoctor.domain.tool.mapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;


public abstract class AbstractMapper<E, M, D> implements Mapper<E, M, D> {
    @Autowired
    protected ModelMapper mapper;

    private final Class<E> entityClass;
    private final Class<M> modelClass;
    private final Class<D> dtoClass;

    public AbstractMapper(Class<E> entityClass, Class<M> modelClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.modelClass = modelClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntityFromModel(M model) {
        return Objects.isNull(model)
                ? null
                : mapper.map(model, entityClass);
    }

    @Override
    public M toModelFromEntity(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, modelClass);
    }

    @Override
    public M toModelFromDto(D dto) {
        return Objects.isNull(dto)
                ? null
                : mapper.map(dto, modelClass);
    }

    @Override
    public D toDtoFromModel(M model) {
        return Objects.isNull(model)
                ? null
                : mapper.map(model, dtoClass);
    }

    protected Converter<E, M> toModelFromEntityConverter() {
        return context -> {
            E source = context.getSource();
            M destination = context.getDestination();
            mapSpecificFieldsToModelFromEntity(source, destination);
            return context.getDestination();
        };
    }

    protected Converter<M, E> toEntityFromModelConverter() {
        return context -> {
            M source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFieldsToEntityFromModel(source, destination);
            return context.getDestination();
        };
    }

    protected Converter<M, D> toDtoFromModelConverter() {
        return context -> {
            M source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFieldsToDtoFromModel(source, destination);
            return context.getDestination();
        };
    }

    protected Converter<D, M> toModelFromDtoConverter() {
        return context -> {
            D source = context.getSource();
            M destination = context.getDestination();
            mapSpecificFieldsToModelFromDto(source, destination);
            return context.getDestination();
        };
    }

    protected abstract void mapSpecificFieldsToModelFromEntity(E source, M destination);

    protected abstract void mapSpecificFieldsToEntityFromModel(M source, E destination);

    protected abstract void mapSpecificFieldsToDtoFromModel(M source, D destination);

    protected abstract void mapSpecificFieldsToModelFromDto(D source, M destination);
}

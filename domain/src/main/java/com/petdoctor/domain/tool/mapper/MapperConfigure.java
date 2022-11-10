package com.petdoctor.domain.tool.mapper;

import com.petdoctor.domain.dto.DoctorDto;
import com.petdoctor.domain.model.doctor.Doctor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MapperConfigure {

    @Bean
    @Scope("singleton")
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT) // TODO: read about strategies
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setPreferNestedProperties(false)
                .setAmbiguityIgnored(true);

    return modelMapper;
    }
}

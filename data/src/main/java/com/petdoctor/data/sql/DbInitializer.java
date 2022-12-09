package com.petdoctor.data.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//@Component
//public class DbInitializer {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public DataSourceInitializer dataSourceInitializer() {
//
//        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//        resourceDatabasePopulator.setContinueOnError(false);
//        resourceDatabasePopulator.setIgnoreFailedDrops(false);
//
//        resourceDatabasePopulator.addScripts(
//                new ClassPathResource("InitVetClinicsTable.sql"),
//                new ClassPathResource("InitDoctorsTable.sql"),
//                new ClassPathResource("InitClientsTable.sql"),
//                new ClassPathResource("InitAppointmentsTable.sql"));
//
//        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//        dataSourceInitializer.setDataSource(dataSource);
//        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
//
//        return dataSourceInitializer;
//    }
//}

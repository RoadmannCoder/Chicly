package com.chickly.DataAccessLayer.Util;

import com.chickly.DTO.SubProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ObjectMapperConfig {
    public static ObjectMapper configureObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Create a module and add the custom key deserializer
        SimpleModule module = new SimpleModule();
        module.addKeyDeserializer(SubProductDTO.class, new SubProductDTOKeyDeserializer());

        // Register the module with the ObjectMapper
        mapper.registerModule(module);

        return mapper;
    }
}

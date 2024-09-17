package com.chickly.DataAccessLayer.Util;

import com.chickly.DTO.SubProductDTO;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;

public class SubProductDTOKeyDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String key, DeserializationContext deserializationContext) throws IOException {
        try {
            int id = Integer.parseInt(key);  // Assume key is just the id as String
            SubProductDTO subProductDTO = new SubProductDTO();
            subProductDTO.setId(id);
            return subProductDTO;
        } catch (NumberFormatException e) {
            throw new IOException("Invalid SubProductDTO key: " + key, e);
        }
    }
}

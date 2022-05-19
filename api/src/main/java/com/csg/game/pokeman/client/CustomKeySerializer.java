package com.csg.game.pokeman.client;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Map;

@Log4j2
public class CustomKeySerializer extends JsonSerializer<Object> {
    private Map<String, String> keyMap;

    public CustomKeySerializer(Map<String, String> keyMap) {
        this.keyMap = keyMap;
    }

    @Override
    public void serialize(Object s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String key = ((String)s).toUpperCase();
        jsonGenerator.writeFieldName(key);
    }
}

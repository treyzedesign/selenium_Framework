package org.example.pom.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.pom.objects.BillingAddress;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {
    public static <T> T deserializeJson(String filename, Class<T> T) throws IOException {
        InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(filename);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(is, T);
    }
}

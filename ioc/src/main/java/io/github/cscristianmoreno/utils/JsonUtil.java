package io.github.cscristianmoreno.utils;

import java.lang.reflect.Field;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import io.github.cscristianmoreno.factory.SingletonFactory;

public abstract class JsonUtil {

    private static final ObjectMapper objectMapper = SingletonFactory.get(ObjectMapper.class);
    
    public static <T> T stringToObject(Class<?> clazz, String body) throws Exception {
        /** Create a instance class without parameters constructor */
        T instance = (T) clazz.getConstructor().newInstance();

        /** Get all fields */
        Field[] fields = clazz.getDeclaredFields(); 

        /** Read and convert string json in JsonNode class */
        JsonNode jsonNode = objectMapper.readValue(body, JsonNode.class);

        /** Loop all fields */
        for (Field field: fields) {
            /** Get field name */
            String fieldName = field.getName();

            /** Get json value from json node */
            JsonNode jsonValue = jsonNode.get(fieldName);

            /** If json value is null, continue */
            if (jsonValue == null) {
                continue;
            }

            /** Convert json value string in field type for set it value */
            T value = (T) objectMapper.treeToValue(jsonValue, field.getType());

            field.setAccessible(true);
            field.set(instance, value);
        }

        return instance;
    }
}

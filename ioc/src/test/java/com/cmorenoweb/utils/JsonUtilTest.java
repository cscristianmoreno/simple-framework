package com.cmorenoweb.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cmorenoweb.IOCInitializer;
import com.cmorenoweb.entity.MyEntity;
import com.cmorenoweb.factory.SingletonFactory;

public class JsonUtilTest {
    
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        SingletonFactory.register(ObjectMapper.class);
        objectMapper = SingletonFactory.get(ObjectMapper.class);
    }
    
    @Test
    void testStringToObject() throws Exception {
        Map<String, Object> entity = new HashMap<String, Object>();
        entity.put("id", 1);
        entity.put("name", "asd");

        String json = objectMapper.writeValueAsString(entity);

        Object result = JsonUtil.stringToObject(MyEntity.class, json);

        assertNotNull(result);
        assertTrue(result.getClass().isAssignableFrom(MyEntity.class));
    }
}

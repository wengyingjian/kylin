package com.wengyingjian.kylin.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static ObjectMapper JSON = new ObjectMapper();

    public static String getJsonFromObject(Object obj) {
        try {
            return JSON.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getObjectFromJson(String json, Class<T> valueType) {
        try {
            return JSON.readValue(json, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T getObjectFromJson(String json, TypeReference<T> valueTupeRef) {
        try {
            return JSON.readValue(json, valueTupeRef);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

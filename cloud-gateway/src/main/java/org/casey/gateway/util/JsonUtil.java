package org.casey.gateway.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JsonUtil
 * @Author Fu Kai
 * @Description Json工具类
 * @Date 2021/1/11 11:44
 */

@Slf4j
public final class JsonUtil {

    /**
     * 默认日期时间格式
     */
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认日期格式
     */
    public static final String DATE = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    public static final String TIME = "HH:mm:ss";

    /**
     * 实例
     */
    private static volatile ObjectMapper mapper;

    private JsonUtil() {
    }

    public static ObjectMapper instance() {
        if (null == mapper) {
            synchronized (JsonUtil.class) {
                if (null == mapper) {
                    mapper = new ObjectMapper();
                    JavaTimeModule javaTimeModule = new JavaTimeModule();
                    javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME)));
                    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE)));
                    javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME)));
                    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME)));
                    javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE)));
                    javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME)));
                    mapper.registerModule(new ParameterNamesModule())
                            .registerModule(new Jdk8Module())
                            .registerModule(javaTimeModule);
                }
            }
        }
        return mapper;
    }

    public static ObjectWriter writer() {
        return instance().writer();
    }

    public static ObjectReader reader() {
        return instance().reader();
    }

    /**
     * Serialize Object -> jsonString
     */
    public static String serialize(Object object) {
        String jsonString;
        try {
            jsonString = instance().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("serialize JsonProcessingException", e);
        }
        return jsonString;
    }

    /**
     * Deserialize jsonString -> Object
     */
    public static <T> T deserialize(String jsonString, Class<T> type) {
        T jsonObject;
        try {
            jsonObject = instance().readValue(jsonString, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("deserialize JsonProcessingException", e);
        }
        return jsonObject;
    }

    /**
     * Deserialize jsonListString -> ObjectList
     */
    public static <T> List<T> deserialize2List(String jsonString, Class<T> parameterClass) {
        List<T> objectList;
        CollectionType listType = instance().getTypeFactory().constructCollectionType(ArrayList.class, parameterClass);
        try {
            objectList = instance().readValue(jsonString, listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("deserialize2List JsonProcessingException", e);
        }
        return objectList;
    }

    /**
     * Deserialize jsonMapString -> ObjectMap
     */
    public static <K, V> Map<K, V> deserialize2Map(String jsonString, Class<K> keyClass, Class<V> valueClass) {
        Map<K, V> objectMap;
        JavaType javaType = instance().getTypeFactory().constructParametricType(HashMap.class, keyClass, valueClass);
        try {
            objectMap = instance().readValue(jsonString, javaType);
        } catch (IOException e) {
            throw new RuntimeException("deserialize2Map IOException", e);
        }
        return objectMap;
    }

    /**
     * jsonString -> jsonNode
     */
    public static JsonNode toJsonNode(String jsonString){
        JsonNode jsonNode;
        try {
            jsonNode = instance().readTree(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("toJsonNode JsonProcessingException", e);
        }
        return jsonNode;
    }

    /**
     * ObjectNode
     */
    public static ObjectNode objectNode(){
        return instance().createObjectNode();
    }

    /**
     * ArrayNode
     */
    public static ArrayNode arrayNode(){
        return instance().createArrayNode();
    }

}
package org.casey.cloud.h.common.json;


import com.fasterxml.jackson.core.JsonParser;
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
public class JsonUtil {

    enum ObjectMapperSingletonEnum {
        /**
         * 单实例
         */
        INSTANCE;
        private final ObjectMapper objectMapper;

        ObjectMapperSingletonEnum() {
            String date = "yyyy-MM-dd";
            String time = "HH:mm:ss";
            String dateTime = "yyyy-MM-dd HH:mm:ss";
            objectMapper = new ObjectMapper();
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTime)));
            javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(date)));
            javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(time)));
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTime)));
            javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(date)));
            javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(time)));
            objectMapper
                    .registerModule(new ParameterNamesModule())
                    .registerModule(new Jdk8Module())
                    .registerModule(javaTimeModule)
                    // 允许解析不带引号的JSON(非标准JSON)
                    .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
                    // 允许单引号
                    .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        }

        public ObjectMapper instance() {
            return objectMapper;
        }
    }

    private JsonUtil() {
    }

    public static ObjectMapper instance() {
        return ObjectMapperSingletonEnum.INSTANCE.instance();
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
     * Deserialize jsonObject -> ObjectList
     */
    public static <T> List<T> deserialize2List(Object jsonObject, Class<T> parameterClass) {
        List<T> objectList;
        CollectionType listType = instance().getTypeFactory().constructCollectionType(ArrayList.class, parameterClass);
        try {
            objectList = instance().readValue(instance().writeValueAsString(jsonObject), listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("deserialize2List JsonProcessingException", e);
        }
        return objectList;
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
    public static JsonNode toJsonNode(String jsonString) {
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
    public static ObjectNode objectNode() {
        return instance().createObjectNode();
    }

    /**
     * ArrayNode
     */
    public static ArrayNode arrayNode() {
        return instance().createArrayNode();
    }

}
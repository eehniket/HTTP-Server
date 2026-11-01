package com.eehniketsjunk.httpserver.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Json {
    private static ObjectMapper myObjectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static JsonNode parse(String jsonsrc) throws JsonProcessingException {
        return myObjectMapper.readTree(jsonsrc);
    }

    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return myObjectMapper.treeToValue(node, clazz);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }

    public static JsonNode toJson(Object obj){
        return myObjectMapper.valueToTree(obj);
    }

    public static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectwriter = myObjectMapper.writer();
        if(pretty){
            objectwriter = objectwriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectwriter.writeValueAsString(o);
    }
}

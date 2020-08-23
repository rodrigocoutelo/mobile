package com.example.recoin.DB;

import androidx.room.TypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converters {

    private static ObjectMapper objectMapper;


    @TypeConverter
    public static String listToJson(List<String> value) {
        objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{'Error':'Convert error'}";
        }
    }

    @TypeConverter
    public static ArrayList<String> jsonToList(String value) {
        objectMapper = new ObjectMapper();
        String[] arr = new String[0];
        try {
            arr = objectMapper.readValue(value, String[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(Arrays.asList(arr));
    }

}

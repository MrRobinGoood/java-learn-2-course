package ru.nshi.task2;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import ru.nshi.task1.ExampleA;
import ru.nshi.task1.ExampleB;
import ru.nshi.task1.Sorting;

public class JsonConverter {

    public String jsonConvert(String path) throws IOException {
        Sorting bubbleSort = new ExampleA();
        Sorting insertionSort = new ExampleB();
        String json = "";
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(path);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Responce responce = objectMapper.readValue(file, Responce.class);
        if (responce.getAlgorithm().equalsIgnoreCase("bubble")) {
            try {
                Result result = new Result();
                long time = System.currentTimeMillis();
                int[] sortArray1 = bubbleSort.sort(responce.getValues());
                result.setTime(System.currentTimeMillis() - time);
                result.setValues(sortArray1);
                json = objectMapper.writeValueAsString(result);
            } catch (NullPointerException e) {
                JsonException ex = new JsonException();
                ex.setJsonException("Array is null");
                json = objectMapper.writeValueAsString(ex);
            }
        } else if (responce.getAlgorithm().equalsIgnoreCase("choice")) {
            try {
                Result result = new Result();
                long time = System.currentTimeMillis();
                int[] sortArray2 = insertionSort.sort(responce.getValues());
                result.setTime(System.currentTimeMillis() - time);
                result.setValues(sortArray2);
                json = objectMapper.writeValueAsString(result);
            } catch (NullPointerException e) {
                JsonException ex = new JsonException();
                ex.setJsonException("Array is null");
                json = objectMapper.writeValueAsString(ex);
            }
        } else {
            System.out.println("Unknown key responce");
        }
        return json;
    }

    public void jsonWrite(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("json-processing/src/main/resources/output.json"), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

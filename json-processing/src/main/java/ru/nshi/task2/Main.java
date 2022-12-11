package ru.nshi.task2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JsonConverter jsonConverter = new JsonConverter();
        String path = "json-processing/src/main/resources/input.json";
        jsonConverter.jsonWrite(jsonConverter.jsonSort(path));
    }

}
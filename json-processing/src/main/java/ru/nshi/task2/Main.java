package ru.nshi.task2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "json-processing/src/main/resources/input.json";
        JsonConverter.jsonWrite(JsonConverter.jsonSort(path));
    }

}
package com;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskStorage {
    private static final String FILE_PATH = "tasks.json";

    // Сохраняет задачи в файл
    public static void saveTasks(Map<String, List<TaskData>> columns) {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(columns, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Загружает задачи из файла
    public static Map<String, List<TaskData>> loadTasks() {
        File file = new File(FILE_PATH);


        if (!file.exists()) {
            createNewFile();
            return new HashMap<>();
        }


        if (file.length() == 0) {
            return new HashMap<>();
        }

        Gson gson = new Gson();
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type type = new TypeToken<Map<String, List<TaskData>>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }


    private static void createNewFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                saveTasks(new HashMap<>());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
    }
}

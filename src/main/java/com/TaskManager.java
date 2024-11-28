package com;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private static TaskManager instance; // Единственный экземпляр
    private List<Task> tasks; // Список задач

    // Приватный конструктор для Singleton
    private TaskManager() {
        tasks = new ArrayList<>();
    }

    // Метод для получения экземпляра класса (Singleton)
    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    // Метод для добавления задачи
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Метод для получения всех задач
    public List<Task> getTasks() {
        return tasks;
    }

    // Метод для удаления задачи (по индексу)
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }

    // Метод для обновления задачи
    public void updateTask(int index, Task updatedTask) {
        if (index >= 0 && index < tasks.size()) {
            tasks.set(index, updatedTask);
        }
    }
}

package com;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskBoard {
    private final List<TaskColumn> columnsList;

    public TaskBoard() {
        columnsList = new ArrayList<>();
        columnsList.add(new TaskColumn("Backlog", this));
        columnsList.add(new TaskColumn("To Do", this));
        columnsList.add(new TaskColumn("In Progress", this));
        columnsList.add(new TaskColumn("Done", this));

        loadTasks(); // Загружаем задачи при запуске
    }

    public List<TaskColumn> getColumns() {
        return columnsList;
    }

    // Метод для получения отображения всех колонок
    public HBox getView() {
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(30, 10, 10, 30));// Расстояние между колонками
        for (TaskColumn column : columnsList) {
            hbox.getChildren().add(column); // Добавляем каждую колонку в HBox
        }
        return hbox;
    }

    // Сохранение задач
    public void saveTasks() {
        Map<String, List<TaskData>> columnData = new HashMap<>();
        for (TaskColumn column : columnsList) {
            List<TaskData> tasks = new ArrayList<>();
            for (Task task : column.getTaskList().getItems()) {
                tasks.add(new TaskData(task.getTitle(), task.getDescription(), task.getColor(), task.getDueDate()));
            }
            columnData.put(column.getTitle(), tasks);
        }
        TaskStorage.saveTasks(columnData); // Сохранение в JSON
    }

    // Загрузка задач
    private void loadTasks() {
        Map<String, List<TaskData>> columnData = TaskStorage.loadTasks();
        for (TaskColumn column : columnsList) {
            List<TaskData> tasks = columnData.getOrDefault(column.getTitle(), new ArrayList<>());
            for (TaskData data : tasks) {
                // Используем публичные поля напрямую, так как мы не используем геттеры
                Task task = new Task(data.title, data.description, data.color, data.dueDate, this);
                column.getTaskList().getItems().add(task);
            }
        }
    }
}

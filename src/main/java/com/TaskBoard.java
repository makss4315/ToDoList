package com;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Scale;

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
    public BorderPane getView() {
        // Основное содержимое
        HBox hbox = new HBox(15); // Расстояние между колонками
        hbox.setPadding(new Insets(20));
        hbox.setStyle("-fx-background-color: #ffffff;");

        for (TaskColumn column : columnsList) {
            hbox.getChildren().add(column); // Добавляем каждую колонку в HBox
        }

        // Заголовок приложения
        Label title = new Label("To-Do List Application");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        title.setTextFill(Color.web("#333333"));
        title.setPadding(new Insets(10));

        // Общий контейнер
        BorderPane root = new BorderPane();
        root.setTop(title);
        root.setCenter(hbox);
        BorderPane.setMargin(title, new Insets(10));

        return root;
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
                Task task = new Task(data.title, data.description, data.color, data.dueDate, this);
                column.getTaskList().getItems().add(task);
            }
        }
    }
}

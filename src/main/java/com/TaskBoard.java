package com;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

        loadTasks();
    }

    public List<TaskColumn> getColumns() {
        return columnsList;
    }

    // Метод для получения отображения всех колонок
    public BorderPane getView() {
        // Основное содержимое
        HBox hbox = new HBox(15);
        hbox.setPadding(new Insets(20));
        hbox.setStyle("-fx-background-color: linear-gradient(to bottom, #e8e8e8, #f5f5f5); -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-padding: 20px;");

        for (TaskColumn column : columnsList) {
            VBox columnContainer = new VBox(10);
            columnContainer.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dddddd; -fx-border-radius: 8px; -fx-padding: 15px; -fx-effect: dropshadow(one-pass-box, rgba(0, 0, 0, 0.15), 12, 0, 0, 6);");
            columnContainer.getChildren().add(column);
            hbox.getChildren().add(columnContainer);
        }

        BorderPane root = new BorderPane();
        root.setCenter(hbox);
        root.setStyle("-fx-background-color: #f4f4f4;");

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
        TaskStorage.saveTasks(columnData);
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
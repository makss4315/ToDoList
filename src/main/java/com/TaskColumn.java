package com;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class TaskColumn extends VBox {
    private final ListView<Task> taskList;
    private final String title;

    public TaskColumn(String title, TaskBoard taskBoard) {
        super(10);
        this.title = title;
        taskList = new ListView<>();

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Button addTaskButton = new Button("+ Add new card");
        addTaskButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5px 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        addTaskButton.setOnMouseEntered(e -> addTaskButton.setStyle("-fx-background-color: #45A049; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5px 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
        addTaskButton.setOnMouseExited(e -> addTaskButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5px 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));

        addTaskButton.setOnAction(e -> new TaskForm(task -> taskList.getItems().add(task), taskBoard));

        this.getChildren().addAll(titleLabel, taskList, addTaskButton);
    }

    public String getTitle() {
        return title;
    }

    public ListView<Task> getTaskList() {
        return taskList;
    }
}

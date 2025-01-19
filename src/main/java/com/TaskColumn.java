// TaskColumn.java (Updated)
package com;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TaskColumn extends VBox {
    private final ListView<Task> taskList;
    private final String title;

    public TaskColumn(String title, TaskBoard taskBoard) {
        super(10);
        this.title = title;
        taskList = new ListView<>();
        taskList.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 8px; -fx-border-color: #dddddd; -fx-padding: 5px;");

        Button addTaskButton = new Button("+ Add new card");
        addTaskButton.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        addTaskButton.setStyle(
                "-fx-background-color: linear-gradient(#42a742, #2e8b2e); " +
                        "-fx-text-fill: white; " +
                        "-fx-background-radius: 5; " +
                        "-fx-padding: 5 10;"
        );
        addTaskButton.setOnMouseEntered(e -> addTaskButton.setStyle(
                "-fx-background-color: linear-gradient(#3a9e3a, #267326); -fx-text-fill: white; -fx-background-radius: 5; -fx-padding: 5 10;"
        ));
        addTaskButton.setOnMouseExited(e -> addTaskButton.setStyle(
                "-fx-background-color: linear-gradient(#42a742, #2e8b2e); -fx-text-fill: white; -fx-background-radius: 5; -fx-padding: 5 10;"
        ));

        addTaskButton.setOnAction(e -> new TaskForm(task -> taskList.getItems().add(task), taskBoard));

        javafx.scene.control.Label titleLabel = new javafx.scene.control.Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        titleLabel.setStyle("-fx-text-fill: #333333;");

        this.setStyle(
                "-fx-background-color: #f4f4f4; " +
                        "-fx-border-color: #cccccc; " +
                        "-fx-border-radius: 8px; " +
                        "-fx-padding: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 8, 0, 0, 4);"
        );

        this.getChildren().addAll(titleLabel, taskList, addTaskButton);
    }

    public String getTitle() {
        return title;
    }

    public ListView<Task> getTaskList() {
        return taskList;
    }
}

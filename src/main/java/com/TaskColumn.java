package com;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TaskColumn extends VBox {
    private final ListView<Task> taskList;
    private final String title;

    public TaskColumn(String title, TaskBoard taskBoard) {
        super(10);
        this.title = title;
        taskList = new ListView<>();

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #444444; -fx-padding: 5px 0;");

        Button addTaskButton = new Button("+ Add new card");
        addTaskButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #4CAF50, #3E8E41); " +
                        "-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 12px; " +
                        "-fx-border-radius: 6px; -fx-background-radius: 6px; -fx-cursor: hand;"
        );
        addTaskButton.setOnMouseEntered(e -> addTaskButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #45A049, #357D36); " +
                        "-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 12px; " +
                        "-fx-border-radius: 6px; -fx-background-radius: 6px; -fx-cursor: hand;"
        ));
        addTaskButton.setOnMouseExited(e -> addTaskButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #4CAF50, #3E8E41); " +
                        "-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 12px; " +
                        "-fx-border-radius: 6px; -fx-background-radius: 6px; -fx-cursor: hand;"
        ));

        addTaskButton.setOnAction(e -> new TaskForm(task -> {
            taskList.getItems().add(task);
        }, taskBoard));

        this.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-width: 1px; " +
                "-fx-border-radius: 8px; -fx-padding: 15px;");

        this.getChildren().addAll(titleLabel, taskList, addTaskButton);
        styleTaskList(taskBoard);
    }

    public String getTitle() {
        return title;
    }

    public ListView<Task> getTaskList() {
        return taskList;
    }

    private void styleTaskList(TaskBoard taskBoard) {
        taskList.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);

                if (empty || task == null) {
                    setGraphic(null);
                } else {
                    HBox taskBox = new HBox(10);
                    taskBox.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #dddddd; -fx-border-radius: 6px; -fx-padding: 10px; -fx-effect: dropshadow(one-pass-box, rgba(0, 0, 0, 0.1), 5, 0, 0, 2);");

                    Rectangle colorIndicator = new Rectangle(10, 10, Color.valueOf(task.getColor().toLowerCase()));
                    Text titleText = new Text(task.getTitle());
                    titleText.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

                    Text dueDateText = new Text(" (Due: " + task.getDueDate() + ")");
                    dueDateText.setStyle("-fx-font-size: 12px; -fx-text-fill: #888888;");

                    taskBox.getChildren().addAll(colorIndicator, titleText, dueDateText);

                    taskBox.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2) {
                            new TaskDetailsForm(
                                    task,
                                    taskBoard.getColumns(),
                                    task::removeTaskFromCurrentColumn,
                                    () -> {}
                            );
                        }
                    });

                    setGraphic(taskBox);
                }
            }
        });
    }
}

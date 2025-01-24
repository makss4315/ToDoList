package com;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TaskColumn extends VBox {
    private final ListView<Task> taskList;
    private final String title;

    public TaskColumn(String title, TaskBoard taskBoard) {
        super(10);
        this.title = title;
        taskList = new ListView<>();

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #FFA500; -fx-padding: 5px 0;");

        Button addTaskButton = new Button("+ Add new card");
        addTaskButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #FF4500, #FF8C00); " +
                        "-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 16px; " +
                        "-fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;"
        );
        addTaskButton.setOnMouseEntered(e -> addTaskButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #FF8C00, #FF6347); " +
                        "-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 16px; " +
                        "-fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;"
        ));
        addTaskButton.setOnMouseExited(e -> addTaskButton.setStyle(
                "-fx-background-color: linear-gradient(to right, #FF4500, #FF8C00); " +
                        "-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 16px; " +
                        "-fx-border-radius: 12px; -fx-background-radius: 12px; -fx-cursor: hand;"
        ));

        addTaskButton.setOnAction(e -> new TaskForm(task -> {
            taskList.getItems().add(task);
        }, taskBoard));

        // Устанавливаем стиль для всей колонки (VBox)
        this.setStyle("-fx-background-color: #1E1E1E; -fx-border-color: #FFA500; -fx-border-width: 2px; " +
                "-fx-border-radius: 12px; -fx-padding: 15px;");

        // Устанавливаем стиль для списка задач (ListView)
        taskList.setStyle("-fx-background-color: #1E1E1E; -fx-control-inner-background: #1E1E1E; -fx-border-color: transparent;");

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
                    taskBox.setStyle("-fx-background-color: #5e5e5e; -fx-border-color: #FFA500; -fx-border-radius: 12px; " +
                            "-fx-padding: 12px; -fx-effect: dropshadow(one-pass-box, rgba(0, 0, 0, 0.2), 10, 0, 0, 4);");

                    Rectangle colorIndicator = new Rectangle(12, 12, Color.valueOf(task.getColor().toLowerCase()));
                    colorIndicator.setArcWidth(6);
                    colorIndicator.setArcHeight(6);

                    Text titleText = new Text(task.getTitle());
                    titleText.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #FFA500;"); // Orange text color

                    Text dueDateText = new Text(" (Due: " + task.getDueDate() + ")");
                    dueDateText.setStyle("-fx-font-size: 14px; -fx-text-fill: #BBBBBB;");

                    taskBox.getChildren().addAll(colorIndicator, titleText, dueDateText);

                    // Добавляем эффект увеличения при наведении
                    taskBox.setOnMouseEntered(event -> {
                        taskBox.setStyle("-fx-background-color: #6e6e6e; -fx-border-color: #FFA500; -fx-border-radius: 12px; " +
                                "-fx-padding: 12px; -fx-effect: dropshadow(one-pass-box, rgba(0, 0, 0, 0.4), 15, 0, 0, 6);");

                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), taskBox);
                        scaleTransition.setFromX(1.0);
                        scaleTransition.setFromY(1.0);
                        scaleTransition.setToX(1.07);
                        scaleTransition.setToY(1.07);
                        scaleTransition.play();
                    });

                    taskBox.setOnMouseExited(event -> {
                        taskBox.setStyle("-fx-background-color: #5e5e5e; -fx-border-color: #FFA500; -fx-border-radius: 12px; " +
                                "-fx-padding: 12px; -fx-effect: dropshadow(one-pass-box, rgba(0, 0, 0, 0.2), 10, 0, 0, 4);");

                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), taskBox);
                        scaleTransition.setFromX(1.07);
                        scaleTransition.setFromY(1.07);
                        scaleTransition.setToX(1.0);
                        scaleTransition.setToY(1.0);
                        scaleTransition.play();
                    });

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

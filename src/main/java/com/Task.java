package com;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Task extends HBox {
    private final String title;
    private final String description;
    private final String color;
    private final String dueDate;
    private final TaskBoard taskBoard;

    public Task(String title, String description, String color, String dueDate, TaskBoard taskBoard) {
        super(10);
        this.title = title;
        this.description = description;
        this.color = color;
        this.dueDate = dueDate;
        this.taskBoard = taskBoard;

        Rectangle colorBox = new Rectangle(10, 10, Color.valueOf(color.toLowerCase()));
        Label taskInfo = new Label(title + " (Due: " + dueDate + ")");
        this.getChildren().addAll(colorBox, taskInfo);

        this.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                new TaskDetailsForm(
                        this,
                        taskBoard.getColumns(),
                        () -> removeTaskFromCurrentColumn(),
                        () -> taskBoard.updateView()
                );
            }
        });
    }

    private void removeTaskFromCurrentColumn() {
        TaskColumn currentColumn = getCurrentColumn();
        if (currentColumn != null) {
            currentColumn.getTaskList().getItems().remove(this);
        }
    }

    private TaskColumn getCurrentColumn() {
        for (TaskColumn column : taskBoard.getColumns()) {
            if (column.getTaskList().getItems().contains(this)) {
                return column;
            }
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getColor() {
        return color;
    }

    public String getDueDate() {
        return dueDate;
    }
}

package com;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Task extends HBox {
    private String title;
    private String description;
    private String color;
    private String dueDate;
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
                        this::removeTaskFromCurrentColumn,
                        () -> {}
                );
            }
        });
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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
}

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

    public Task(String title, String description, String color, String dueDate) {
        super(10);
        this.title = title;
        this.description = description;
        this.color = color;
        this.dueDate = dueDate;

        // Color indicator
        Rectangle colorBox = new Rectangle(10, 10, Color.valueOf(color.toLowerCase()));

        // Task info
        Label taskInfo = new Label(title + " (Due: " + dueDate + ")");
        this.getChildren().addAll(colorBox, taskInfo);

        // Add double-click action
        this.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                new TaskDetailsForm(this, () -> this.setManaged(false));
            }
        });
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

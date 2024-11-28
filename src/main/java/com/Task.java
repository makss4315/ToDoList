package com;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Task extends HBox {
    public Task(String title, String description, String color, String dueDate) {
        super(10);

        // Color indicator
        Rectangle colorBox = new Rectangle(10, 10, Color.valueOf(color.toLowerCase()));

        // Task info
        Label taskInfo = new Label(title + " (Due: " + dueDate + ")");
        this.getChildren().addAll(colorBox, taskInfo);
    }
}

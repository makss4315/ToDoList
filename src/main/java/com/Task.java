package com;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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

        // Цветовая метка задачи
        Rectangle colorBox = new Rectangle(10, 10, Color.valueOf(color.toLowerCase()));
        Label taskInfo = new Label(title + " (Due: " + dueDate + ")");
        this.getChildren().addAll(colorBox, taskInfo);

        // Стили задачи
        this.setStyle("-fx-background-color: #ECEFF1; -fx-background-radius: 8; -fx-padding: 10;");
        this.setOnMouseEntered(event -> applyHoverEffect());
        this.setOnMouseExited(event -> removeHoverEffect());
    }

    // Эффект при наведении мышки
    private void applyHoverEffect() {
        // Увеличение масштаба
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), this);
        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.05);
        scaleTransition.play();

        // Изменение фона
        this.setStyle("-fx-background-color: #CFD8DC; -fx-background-radius: 8; -fx-padding: 10; -fx-border-color: #B0BEC5; -fx-border-width: 2;");
    }

    // Убираем эффект при уходе мышки
    private void removeHoverEffect() {
        // Возвращаем масштаб
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), this);
        scaleTransition.setToX(1.0);
        scaleTransition.setToY(1.0);
        scaleTransition.play();

        // Возвращаем оригинальный стиль
        this.setStyle("-fx-background-color: #ECEFF1; -fx-background-radius: 8; -fx-padding: 10;");
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
}

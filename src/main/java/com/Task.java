package com;

import java.io.Serializable;

public class Task implements Serializable {
    private String title;
    private String description;
    private String color;
    private String dueDate;

    // Конструктор
    public Task(String title, String description, String color, String dueDate) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.dueDate = dueDate;
    }

    // Геттеры
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

    // Сеттеры (если потребуется изменять данные)
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

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}

package com;

public class TaskData {
    public String title;
    public String description;
    public String color;
    public String dueDate;

    // Конструктор для создания объекта задачи
    public TaskData(String title, String description, String color, String dueDate) {
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
}

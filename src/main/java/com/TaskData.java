package com;

import java.io.Serializable;

public class TaskData implements Serializable {
    private final String title;
    private final String description;
    private final String color;
    private final String dueDate;

    public TaskData(String title, String description, String color, String dueDate) {
        this.title = title;
        this.description = description;
        this.color = color;
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

package com;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class TaskBoard {
    private final HBox columns;

    public TaskBoard() {
        columns = new HBox(10);

        // Отступ слева
        Region spacer = new Region();
        spacer.setMinWidth(50);

        // Колонки доски задач
        columns.getChildren().add(spacer);
        columns.getChildren().add(new TaskColumn("Backlog"));
        columns.getChildren().add(new TaskColumn("To Do"));
        columns.getChildren().add(new TaskColumn("In Progress"));
        columns.getChildren().add(new TaskColumn("Done"));


        columns.setMinWidth(1100);
        columns.setMinHeight(550);
    }

    public HBox getView() {
        return columns;
    }
}

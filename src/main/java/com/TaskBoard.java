package com;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TaskBoard {
    private final HBox columns;

    public TaskBoard() {
        columns = new HBox(10);

        columns.getChildren().add(new TaskColumn("Backlog"));
        columns.getChildren().add(new TaskColumn("To Do"));
        columns.getChildren().add(new TaskColumn("In Progress"));
        columns.getChildren().add(new TaskColumn("Done"));
    }

    public HBox getView() {
        return columns;
    }
}

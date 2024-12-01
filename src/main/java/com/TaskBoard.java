package com;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;

public class TaskBoard {
    private final HBox columns;
    private final List<TaskColumn> columnsList;

    public TaskBoard() {
        columnsList = new ArrayList<>();
        columnsList.add(new TaskColumn("Backlog", this));
        columnsList.add(new TaskColumn("To Do", this));
        columnsList.add(new TaskColumn("In Progress", this));
        columnsList.add(new TaskColumn("Done", this));

        columns = new HBox(10);
        Region spacer = new Region();
        spacer.setMinWidth(50);
        columns.getChildren().add(spacer);
        columns.getChildren().addAll(columnsList);
    }

    public List<TaskColumn> getColumns() {
        return columnsList;
    }

    public HBox getView() {
        return columns;
    }

    public void updateView() {
        columns.requestLayout();
    }
}

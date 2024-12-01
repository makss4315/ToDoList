package com;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class TaskDetailsForm {
    public TaskDetailsForm(Task task, List<TaskColumn> columns, Runnable onTaskDeleted, Runnable onTaskMoved) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Task Details");

        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);

        layout.add(new Label("Title:"), 0, 0);
        layout.add(new Label(task.getTitle()), 1, 0);

        layout.add(new Label("Description:"), 0, 1);
        layout.add(new Label(task.getDescription()), 1, 1);

        layout.add(new Label("Color:"), 0, 2);
        layout.add(new Label(task.getColor()), 1, 2);

        layout.add(new Label("Due Date:"), 0, 3);
        layout.add(new Label(task.getDueDate()), 1, 3);

        ComboBox<String> columnPicker = new ComboBox<>();
        for (TaskColumn column : columns) {
            columnPicker.getItems().add(column.getTitle());
        }
        columnPicker.setValue(columns.get(0).getTitle());
        layout.add(new Label("Move to:"), 0, 4);
        layout.add(columnPicker, 1, 4);

        Button moveButton = new Button("Move Task");
        moveButton.setOnAction(e -> {
            String selectedColumn = columnPicker.getValue();
            for (TaskColumn column : columns) {
                if (column.getTitle().equals(selectedColumn)) {
                    TaskColumn currentColumn = getCurrentColumn(task, columns);
                    if (currentColumn != null) {
                        currentColumn.getTaskList().getItems().remove(task);
                    }
                    column.getTaskList().getItems().add(task);
                    onTaskMoved.run();
                    stage.close();
                    return;
                }
            }
        });

        Button deleteButton = new Button("Delete Task");
        deleteButton.setOnAction(e -> {
            TaskColumn currentColumn = getCurrentColumn(task, columns);
            if (currentColumn != null) {
                currentColumn.getTaskList().getItems().remove(task);
                onTaskDeleted.run();
            }
            stage.close();
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        layout.add(moveButton, 0, 5);
        layout.add(deleteButton, 1, 5);
        layout.add(closeButton, 2, 5);

        stage.setScene(new Scene(layout, 400, 250));
        stage.show();
    }

    private TaskColumn getCurrentColumn(Task task, List<TaskColumn> columns) {
        for (TaskColumn column : columns) {
            if (column.getTaskList().getItems().contains(task)) {
                return column;
            }
        }
        return null;
    }
}

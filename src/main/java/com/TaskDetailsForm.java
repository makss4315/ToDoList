package com;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
        layout.setVgap(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        // Title
        layout.add(new Label("Title:"), 0, 0);
        Label titleLabel = new Label(task.getTitle());
        titleLabel.setStyle("-fx-font-weight: bold;");
        layout.add(titleLabel, 1, 0);

        // Description
        layout.add(new Label("Description:"), 0, 1);
        Label descriptionLabel = new Label(task.getDescription());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(250); // Limit width for readability
        layout.add(descriptionLabel, 1, 1);

        // Color
        layout.add(new Label("Color:"), 0, 2);
        Label colorLabel = new Label(task.getColor());
        colorLabel.setStyle("-fx-background-color: " + task.getColor() + "; -fx-padding: 3px;");
        layout.add(colorLabel, 1, 2);

        // Due Date
        layout.add(new Label("Due Date:"), 0, 3);
        layout.add(new Label(task.getDueDate()), 1, 3);

        // Move to another column
        ComboBox<String> columnPicker = new ComboBox<>();
        for (TaskColumn column : columns) {
            columnPicker.getItems().add(column.getTitle());
        }
        columnPicker.setValue(columns.get(0).getTitle());
        layout.add(new Label("Move to:"), 0, 4);
        layout.add(columnPicker, 1, 4);

        // Buttons
        Button moveButton = new Button("Move Task");
        moveButton.setStyle("-fx-background-color: #0078D7; -fx-text-fill: white;");
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
        deleteButton.setStyle("-fx-background-color: #D32F2F; -fx-text-fill: white;");
        deleteButton.setOnAction(e -> {
            TaskColumn currentColumn = getCurrentColumn(task, columns);
            if (currentColumn != null) {
                currentColumn.getTaskList().getItems().remove(task);
                onTaskDeleted.run();
            }
            stage.close();
        });

        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #666666; -fx-text-fill: white;");
        closeButton.setOnAction(e -> stage.close());

        HBox buttonBox = new HBox(10, moveButton, deleteButton, closeButton);
        buttonBox.setAlignment(Pos.CENTER);
        layout.add(buttonBox, 0, 5, 2, 1);

        stage.setScene(new Scene(layout, 450, 300));
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

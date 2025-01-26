package com;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class TaskDetailsForm {
    public TaskDetailsForm(Task task, List<TaskColumn> columns, Runnable onTaskDeleted, Runnable onTaskMoved) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Task");

        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #1E1E1E;");

        Label titleLabel = new Label("Title:");
        titleLabel.setStyle("-fx-text-fill: #FFA500;");
        TextField titleField = new TextField(task.getTitle());
        titleField.setStyle("-fx-background-color: #333333; -fx-text-fill: #FFA500;");

        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setStyle("-fx-text-fill: #FFA500;");
        TextArea descriptionField = new TextArea(task.getDescription());
        descriptionField.setWrapText(true);
        descriptionField.setStyle("-fx-background-color: #333333; -fx-text-fill: #FFA500;");

        Label colorLabel = new Label("Color:");
        colorLabel.setStyle("-fx-text-fill: #FFA500;");
        ColorPicker colorPicker = new ColorPicker();
        try {
            colorPicker.setValue(Color.web(task.getColor()));
        } catch (IllegalArgumentException e) {
            colorPicker.setValue(Color.BLACK);
        }

        Label dueDateLabel = new Label("Due Date:");
        dueDateLabel.setStyle("-fx-text-fill: #FFA500;");
        DatePicker dueDatePicker = new DatePicker(java.time.LocalDate.parse(task.getDueDate()));

        layout.add(titleLabel, 0, 0);
        layout.add(titleField, 1, 0);
        layout.add(descriptionLabel, 0, 1);
        layout.add(descriptionField, 1, 1);
        layout.add(colorLabel, 0, 2);
        layout.add(colorPicker, 1, 2);
        layout.add(dueDateLabel, 0, 3);
        layout.add(dueDatePicker, 1, 3);

        Label moveToLabel = new Label("Move to:");
        moveToLabel.setStyle("-fx-text-fill: #FFA500;");
        ComboBox<String> columnPicker = new ComboBox<>();
        columnPicker.setStyle("-fx-background-color: #333333; -fx-text-fill: #FFA500;");
        for (TaskColumn column : columns) {
            columnPicker.getItems().add(column.getTitle());
        }
        columnPicker.setValue(columns.get(0).getTitle());
        layout.add(moveToLabel, 0, 4);
        layout.add(columnPicker, 1, 4);

        // Buttons
        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #FF4500; -fx-text-fill: white;");
        saveButton.setOnAction(e -> {
            task.setTitle(titleField.getText());
            task.setDescription(descriptionField.getText());
            task.setColor(colorPicker.getValue().toString());
            task.setDueDate(dueDatePicker.getValue().toString());
            stage.close();
        });

        Button moveButton = new Button("Move Task");
        moveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
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

        HBox buttonBox = new HBox(10, saveButton, moveButton, deleteButton, closeButton);
        buttonBox.setAlignment(Pos.CENTER);
        layout.add(buttonBox, 0, 5, 2, 1);

        stage.setScene(new Scene(layout, 450, 400));
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

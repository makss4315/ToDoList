package com;


import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TaskDetailsForm {
    public TaskDetailsForm(Task task, Runnable onTaskDeleted) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Task Details");

        GridPane layout = new GridPane();
        layout.setHgap(10);
        layout.setVgap(10);

        // Display task details
        layout.add(new Label("Title:"), 0, 0);
        layout.add(new Label(task.getTitle()), 1, 0);

        layout.add(new Label("Description:"), 0, 1);
        layout.add(new Label(task.getDescription()), 1, 1);

        layout.add(new Label("Color:"), 0, 2);
        layout.add(new Label(task.getColor()), 1, 2);

        layout.add(new Label("Due Date:"), 0, 3);
        layout.add(new Label(task.getDueDate()), 1, 3);

        // Delete button
        Button deleteButton = new Button("Delete Task");
        deleteButton.setOnAction(e -> {
            if (onTaskDeleted != null) {
                onTaskDeleted.run();
            }
            stage.close();
        });

        // Close button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        layout.add(deleteButton, 0, 4);
        layout.add(closeButton, 1, 4);

        stage.setScene(new Scene(layout, 400, 200));
        stage.show();
    }
}

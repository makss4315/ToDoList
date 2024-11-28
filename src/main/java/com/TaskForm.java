package com;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class TaskForm {
    public TaskForm(Consumer<Task> onTaskCreated) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add New Task");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setHgap(10);
        layout.setVgap(10);
        TextField titleInput = new TextField();
        titleInput.setPromptText("Title");

        TextArea descriptionInput = new TextArea();
        descriptionInput.setPromptText("Description");

        ComboBox<String> colorPicker = new ComboBox<>();
        colorPicker.getItems().addAll("Red", "Green", "Blue", "Yellow");
        colorPicker.setValue("Red");

        DatePicker dueDatePicker = new DatePicker();

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String title = titleInput.getText();
            String description = descriptionInput.getText();
            String color = colorPicker.getValue();
            String dueDate = dueDatePicker.getValue() != null ? dueDatePicker.getValue().toString() : "No Date";

            if (!title.isEmpty()) {
                Task task = new Task(title, description, color, dueDate);
                onTaskCreated.accept(task);
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Title is required!");
                alert.show();
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> stage.close());

        layout.add(new Label("Title:"), 0, 0);
        layout.add(titleInput, 1, 0);
        layout.add(new Label("Description:"), 0, 1);
        layout.add(descriptionInput, 1, 1);
        layout.add(new Label("Color:"), 0, 2);
        layout.add(colorPicker, 1, 2);
        layout.add(new Label("Due Date:"), 0, 3);
        layout.add(dueDatePicker, 1, 3);
        layout.add(saveButton, 0, 4);
        layout.add(cancelButton, 1, 4);

        stage.setScene(new Scene(layout, 580, 300));
        stage.show();
    }
}

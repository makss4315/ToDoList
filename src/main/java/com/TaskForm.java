package com;

import com.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.function.Consumer;

public class TaskForm {

    private final Consumer<Task> onTaskCreated;

    public TaskForm(Consumer<Task> onTaskCreated) {
        this.onTaskCreated = onTaskCreated;
    }

    public void display() {
        Stage stage = new Stage();
        stage.setTitle("Add New Task");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Title
        Label titleLabel = new Label("Title:");
        TextField titleInput = new TextField();
        titleInput.setPromptText("Enter task title");
        grid.add(titleLabel, 0, 0);
        grid.add(titleInput, 1, 0);

        // Description
        Label descriptionLabel = new Label("Description:");
        TextArea descriptionInput = new TextArea();
        descriptionInput.setPromptText("Enter task description");
        descriptionInput.setPrefRowCount(3);
        grid.add(descriptionLabel, 0, 1);
        grid.add(descriptionInput, 1, 1);

        // Color Picker
        Label colorLabel = new Label("Color:");
        ColorPicker colorPicker = new ColorPicker();
        grid.add(colorLabel, 0, 2);
        grid.add(colorPicker, 1, 2);

        // Due Date
        Label dueDateLabel = new Label("Due Date:");
        DatePicker dueDatePicker = new DatePicker();
        grid.add(dueDateLabel, 0, 3);
        grid.add(dueDatePicker, 1, 3);

        // Save Button
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            String title = titleInput.getText();
            String description = descriptionInput.getText();
            String color = colorPicker.getValue().toString(); // Convert color to string
            String dueDate = dueDatePicker.getValue() != null ? dueDatePicker.getValue().toString() : "No Date";

            if (!title.isEmpty()) {
                Task task = new Task(title, description, color, dueDate);
                onTaskCreated.accept(task); // Add the task to the list
                TaskManager.getInstance().addTask(task); // Save the task to TaskManager (persistent)
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Title is required!");
                alert.show();
            }
        });

        grid.add(saveButton, 1, 4);

        Scene scene = new Scene(grid, 400, 300);
        stage.setScene(scene);
        stage.show();
    }
}

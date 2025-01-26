package com;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class TaskForm {
    public TaskForm(Consumer<Task> onTaskCreated, TaskBoard taskBoard) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add New Task");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        layout.setHgap(15);
        layout.setVgap(15);
        layout.setStyle("-fx-background-color: #1E1E1E; -fx-border-color: #FFA500; -fx-border-width: 3px;");

        Label titleLabel = new Label("Title:");
        titleLabel.setStyle("-fx-text-fill: #FFA500;");

        TextField titleInput = new TextField();
        titleInput.setPromptText("Title");
        titleInput.setStyle("-fx-background-color: #333333; -fx-text-fill: #FFA500; -fx-background-radius: 10px;");

        Label descriptionLabel = new Label("Description:");
        descriptionLabel.setStyle("-fx-text-fill: #FFA500;");

        TextArea descriptionInput = new TextArea();
        descriptionInput.setPromptText("Description");
        descriptionInput.setStyle("-fx-control-inner-background: #555555; -fx-text-fill: #FFA500; -fx-background-radius: 10px; -fx-border-color: #FFA500; -fx-border-width: 1px;");

        Label colorLabel = new Label("Color:");
        colorLabel.setStyle("-fx-text-fill: #FFA500;");

        ComboBox<String> colorPicker = new ComboBox<>();
        colorPicker.getItems().addAll("Red", "Green", "Blue", "Yellow");
        colorPicker.setValue("Red");
        colorPicker.setStyle("-fx-background-color: #333333; -fx-text-fill: #FFA500; -fx-background-radius: 10px;");

        colorPicker.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String color, boolean empty) {
                super.updateItem(color, empty);
                if (empty || color == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(color);
                    Rectangle rect = new Rectangle(20, 20, Color.web(color.toLowerCase()));
                    rect.setArcWidth(10);
                    rect.setArcHeight(10);
                    setGraphic(rect);
                }
            }
        });

        colorPicker.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String color, boolean empty) {
                super.updateItem(color, empty);
                if (empty || color == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(color);
                    Rectangle rect = new Rectangle(20, 20, Color.web(color.toLowerCase()));
                    rect.setArcWidth(10);
                    rect.setArcHeight(10);
                    setGraphic(rect);
                }
            }
        });

        Label dueDateLabel = new Label("Due Date:");
        dueDateLabel.setStyle("-fx-text-fill: #FFA500;");

        DatePicker dueDatePicker = new DatePicker();
        dueDatePicker.setStyle("-fx-background-color: #333333; -fx-text-fill: #FFA500; -fx-background-radius: 10px;");
        dueDatePicker.getEditor().setStyle("-fx-background-color: #333333; -fx-text-fill: #FFA500; -fx-background-radius: 10px;");

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #FF4500; -fx-text-fill: white; -fx-background-radius: 10px;");
        saveButton.setOnAction(e -> {
            String title = titleInput.getText();
            String description = descriptionInput.getText();
            String color = colorPicker.getValue();
            String dueDate = dueDatePicker.getValue() != null ? dueDatePicker.getValue().toString() : "No Date";

            if (!title.isEmpty()) {
                Task task = new Task(title, description, color, dueDate, taskBoard);
                onTaskCreated.accept(task);
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Title is required!");
                alert.show();
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-background-color: #666666; -fx-text-fill: white; -fx-background-radius: 10px;");
        cancelButton.setOnAction(e -> stage.close());

        layout.add(titleLabel, 0, 0);
        layout.add(titleInput, 1, 0);
        layout.add(descriptionLabel, 0, 1);
        layout.add(descriptionInput, 1, 1);
        layout.add(colorLabel, 0, 2);
        layout.add(colorPicker, 1, 2);
        layout.add(dueDateLabel, 0, 3);
        layout.add(dueDatePicker, 1, 3);
        layout.add(saveButton, 0, 4);
        layout.add(cancelButton, 1, 4);

        stage.setScene(new Scene(layout, 600, 350));
        stage.show();
    }
}

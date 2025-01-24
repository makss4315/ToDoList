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

        // Настройка отображения элементов списка с закругленными квадратиками
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
                    rect.setArcWidth(10); // Закругление углов
                    rect.setArcHeight(10); // Закругление углов
                    setGraphic(rect);
                }
            }
        });

        // Отображение текущего выбранного элемента
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
                    rect.setArcWidth(10); // Закругление углов
                    rect.setArcHeight(10); // Закругление углов
                    setGraphic(rect);
                }
            }
        });

        DatePicker dueDatePicker = new DatePicker();

        Button saveButton = new Button("Save");
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

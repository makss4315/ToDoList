package com;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class TaskManager {
    private final VBox layout;
    private final ListView<Task> taskList;

    public TaskManager(TaskBoard taskBoard) {
        layout = new VBox(10);
        taskList = new ListView<>();

        Button addTaskButton = new Button("Add New Task");
        addTaskButton.setOnAction(e -> new TaskForm(task -> taskList.getItems().add(task), taskBoard));

        layout.getChildren().addAll(taskList, addTaskButton);
    }

    public VBox getView() {
        return layout;
    }
}

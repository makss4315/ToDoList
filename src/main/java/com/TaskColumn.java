package com;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class TaskColumn extends VBox {
    private final ListView<Task> taskList;

    public TaskColumn(String title) {
        super(10);

        taskList = new ListView<>();

        Button addTaskButton = new Button("+ Add new card");
        addTaskButton.setOnAction(e -> new TaskForm(task -> taskList.getItems().add(task)));

        this.getChildren().addAll(new javafx.scene.control.Label(title), taskList, addTaskButton);
    }

    public ListView<Task> getTaskList() {
        return taskList;
    }
}

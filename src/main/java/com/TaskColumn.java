package com;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class TaskColumn extends VBox {
    private final ListView<Task> taskList;
    private final String title;

    public TaskColumn(String title, TaskBoard taskBoard) {
        super(10);
        this.title = title;
        taskList = new ListView<>();

        Button addTaskButton = new Button("+ Add new card");
        addTaskButton.setOnAction(e -> new TaskForm(task -> taskList.getItems().add(task), taskBoard));

        this.getChildren().addAll(new javafx.scene.control.Label(title), taskList, addTaskButton);
    }

    public String getTitle() {
        return title;
    }

    public ListView<Task> getTaskList() {
        return taskList;
    }
}

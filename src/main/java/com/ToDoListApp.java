package com;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ToDoListApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        TaskBoard taskBoard = new TaskBoard();

        // Сохраняем задачи при выходе из приложения
        primaryStage.setOnCloseRequest(event -> taskBoard.saveTasks());

        root.setCenter(taskBoard.getView());

        Scene scene = new Scene(root, 1100, 550);
        primaryStage.setTitle("To-Do List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

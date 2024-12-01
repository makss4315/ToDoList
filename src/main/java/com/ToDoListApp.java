package com;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToDoListApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        TaskBoard taskBoard = new TaskBoard();

        // Создаём контейнер VBox для масштабирования
        VBox container = new VBox();
        container.getChildren().add(taskBoard.getView());
        VBox.setVgrow(taskBoard.getView(), Priority.ALWAYS);

        root.setCenter(container);


        Scene scene = new Scene(root, 1100, 550);
        scene.widthProperty().addListener((obs, oldWidth, newWidth) -> taskBoard.getView().setPrefWidth(newWidth.doubleValue() - 20));
        scene.heightProperty().addListener((obs, oldHeight, newHeight) -> taskBoard.getView().setPrefHeight(newHeight.doubleValue() - 20));

        primaryStage.setTitle("To-Do List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

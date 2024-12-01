package com;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class ToDoListApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        TaskBoard taskBoard = new TaskBoard();


        StackPane scalableContainer = new StackPane(taskBoard.getView());
        Scale scale = new Scale(1, 1);
        scalableContainer.getTransforms().add(scale);


        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(400);


        root.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double scaleX = newWidth.doubleValue() / 1100;
            scale.setX(Math.max(scaleX, 0.5));
        });

        root.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            double scaleY = newHeight.doubleValue() / 550;
            scale.setY(Math.max(scaleY, 0.5));
        });

        root.setCenter(scalableContainer);

        Scene scene = new Scene(root, 1100, 550);
        primaryStage.setTitle("To-Do List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

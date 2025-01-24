package com;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ToDoListApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TaskBoard taskBoard = new TaskBoard();


        Label titleLabel = new Label("To-Do List Application"); // Изменить цвет текста
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.web("#FF6600")); // Установить цвет текста
        titleLabel.setStyle("-fx-background-color: #2E2E2E; -fx-background-radius: 10; -fx-padding: 10 20 10 20;");
        titleLabel.setAlignment(Pos.CENTER);


        VBox taskContainer = new VBox(taskBoard.getView());
        taskContainer.setPadding(new Insets(20));
        taskContainer.setSpacing(15);
        taskContainer.setStyle("-fx-background-color: #2E2E2E; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #555555; -fx-border-width: 2;");
        taskContainer.setEffect(new DropShadow(5.0, Color.GRAY));

        VBox mainLayout = new VBox(20, titleLabel, taskContainer);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));

        BorderPane content = new BorderPane();
        content.setCenter(mainLayout);

        HBox wrapper = new HBox();
        wrapper.setPadding(new Insets(30));
        wrapper.setSpacing(30);
        wrapper.setStyle("-fx-background-color: linear-gradient(to bottom right, #555555, #4d4c4c);");
        wrapper.getChildren().add(content);
        wrapper.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().add(wrapper);
        root.setStyle("-fx-background-color: linear-gradient(to top left, #555555, #4d4c4c);");

        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);

        content.scaleXProperty().bind(Bindings.createDoubleBinding(
                () -> Math.min(scene.getWidth() / 1280, scene.getHeight() / 720),
                scene.widthProperty(), scene.heightProperty()
        ));
        content.scaleYProperty().bind(Bindings.createDoubleBinding(
                () -> Math.min(scene.getWidth() / 1280, scene.getHeight() / 720),
                scene.widthProperty(), scene.heightProperty()
        ));

        primaryStage.setOnCloseRequest(event -> taskBoard.saveTasks());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

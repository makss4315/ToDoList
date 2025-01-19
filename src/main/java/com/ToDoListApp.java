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

        Label titleLabel = new Label("To-Do List Application");
        titleLabel.setFont(Font.font("Roboto", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web("#37474F"));
        titleLabel.setStyle("-fx-background-color: #ECEFF1; -fx-background-radius: 15; -fx-padding: 15 25 15 25;");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setEffect(new DropShadow(5.0, Color.GRAY));

        VBox taskContainer = new VBox(taskBoard.getView());
        taskContainer.setPadding(new Insets(20));
        taskContainer.setSpacing(20);
        taskContainer.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 15; -fx-border-radius: 15; -fx-border-color: #B0BEC5; -fx-border-width: 3;");
        taskContainer.setEffect(new DropShadow(10.0, Color.GRAY));

        VBox mainLayout = new VBox(30, titleLabel, taskContainer);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(30));

        BorderPane content = new BorderPane();
        content.setCenter(mainLayout);

        HBox wrapper = new HBox();
        wrapper.setPadding(new Insets(40));
        wrapper.setSpacing(40);
        wrapper.setStyle("-fx-background-color: linear-gradient(to bottom right, #F5F5F5, #E8EAF6);");
        wrapper.getChildren().add(content);
        wrapper.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();
        root.getChildren().add(wrapper);
        root.setStyle("-fx-background-color: linear-gradient(to top left, #FFFFFF, #CFD8DC);");

        Scene scene = new Scene(root, 1280, 720);

        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);

        // Масштабирование контента
        content.scaleXProperty().bind(Bindings.createDoubleBinding(
                () -> Math.min(scene.getWidth() / 1280, scene.getHeight() / 720),
                scene.widthProperty(), scene.heightProperty()
        ));
        content.scaleYProperty().bind(Bindings.createDoubleBinding(
                () -> Math.min(scene.getWidth() / 1280, scene.getHeight() / 720),
                scene.widthProperty(), scene.heightProperty()
        ));

        // Действия при закрытии
        primaryStage.setOnCloseRequest(event -> taskBoard.saveTasks());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Enhanced To-Do List");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ToDoListApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TaskBoard taskBoard = new TaskBoard();

        // Улучшенный контейнер TaskBoard с тенями и рамками
        VBox taskContainer = new VBox(taskBoard.getView());
        taskContainer.setPadding(new Insets(20));
        taskContainer.setSpacing(15);
        taskContainer.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #B0BEC5; -fx-border-width: 2;");
        taskContainer.setEffect(new DropShadow(5.0, Color.GRAY));

        VBox mainLayout = new VBox(20, taskContainer);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));

        BorderPane content = new BorderPane();
        content.setCenter(mainLayout);

        // Обертка с улучшенным фоном
        HBox wrapper = new HBox();
        wrapper.setPadding(new Insets(30));
        wrapper.setSpacing(30);
        wrapper.setStyle("-fx-background-color: linear-gradient(to bottom right, #ECEFF1, #CFD8DC);");
        wrapper.getChildren().add(content);
        wrapper.setAlignment(Pos.CENTER);

        // Добавление мягкой тени и скругленных углов
        StackPane root = new StackPane();
        root.getChildren().add(wrapper);
        root.setStyle("-fx-background-color: linear-gradient(to top left, #FFFFFF, #ECEFF1);");

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

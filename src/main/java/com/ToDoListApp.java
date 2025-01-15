package com;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ToDoListApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TaskBoard taskBoard = new TaskBoard(); // Создаем TaskBoard

        // Создаем корневой контейнер
        BorderPane content = new BorderPane();
        content.setCenter(taskBoard.getView()); // Добавляем TaskBoard в центр

        // Создаем StackPane для выравнивания содержимого по центру
        StackPane root = new StackPane();
        root.getChildren().add(content);

        // Создаем сцену
        Scene scene = new Scene(root, 1280, 720);

        // Устанавливаем минимальные и максимальные размеры окна
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);
        primaryStage.setMaxWidth(1920);
        primaryStage.setMaxHeight(1080);

        // Пропорциональное масштабирование
        content.scaleXProperty().bind(Bindings.createDoubleBinding(
                () -> Math.min(scene.getWidth() / 1280, scene.getHeight() / 720),
                scene.widthProperty(), scene.heightProperty()
        ));
        content.scaleYProperty().bind(Bindings.createDoubleBinding(
                () -> Math.min(scene.getWidth() / 1280, scene.getHeight() / 720),
                scene.widthProperty(), scene.heightProperty()
        ));

        // Устанавливаем действия при закрытии приложения
        primaryStage.setOnCloseRequest(event -> taskBoard.saveTasks()); // Сохраняем задачи при выходе

        primaryStage.setTitle("To-Do List Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Запускаем JavaFX приложение
    }
}

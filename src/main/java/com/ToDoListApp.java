package com;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class ToDoListApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        TaskBoard taskBoard = new TaskBoard(); // Создаем TaskBoard

        // Масштабирование интерфейса
        Scale scale = new Scale(1, 1);
        root.getTransforms().add(scale);

        // Обновление масштаба при изменении размеров окна
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            double scaleValue = Math.min(newVal.doubleValue() / 1100, primaryStage.getHeight() / 550); // Учет пропорций
            scale.setX(scaleValue);
            scale.setY(scaleValue);
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            double scaleValue = Math.min(newVal.doubleValue() / 550, primaryStage.getWidth() / 1100); // Учет пропорций
            scale.setX(scaleValue);
            scale.setY(scaleValue);
        });

        primaryStage.setOnCloseRequest(event -> taskBoard.saveTasks()); // Сохранение задач при выходе

        root.setCenter(taskBoard.getView()); // Добавляем TaskBoard в интерфейс

        Scene scene = new Scene(root, 1100, 550);
        primaryStage.setTitle("To-Do List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Запуск JavaFX-приложения
    }
}

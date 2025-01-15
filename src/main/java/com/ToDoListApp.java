package com;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ToDoListApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TaskBoard taskBoard = new TaskBoard(); // Создаем TaskBoard

        // Создаем корневой контейнер
        BorderPane content = new BorderPane();
        content.setCenter(taskBoard.getView()); // Добавляем TaskBoard в центр

        // Обертка с отступами и выравниванием
        HBox wrapper = new HBox();
        wrapper.setPadding(new Insets(20)); // Отступы со всех сторон
        wrapper.setSpacing(20); // Расстояние между колонками
        wrapper.setStyle("-fx-background-color: #F4F4F4;"); // Для проверки фона (можно удалить)
        wrapper.getChildren().add(content);
        wrapper.setAlignment(javafx.geometry.Pos.CENTER); // Центрирование содержимого

        // Создаем StackPane для центрирования содержимого относительно окна
        StackPane root = new StackPane();
        root.getChildren().add(wrapper);

        // Создаем сцену
        Scene scene = new Scene(root, 1280, 720);

        // Устанавливаем минимальные размеры окна
        primaryStage.setMinWidth(1280);
        primaryStage.setMinHeight(720);

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

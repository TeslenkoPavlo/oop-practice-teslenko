package com.example.finaltask;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Основний клас, що запускає JavaFX-додаток.
 */
public class Main extends Application {

    /**
     * Метод start створює вікно програми, завантажує CSS та ініціалізує контролер.
     * @param primaryStage Головна сцена JavaFX
     */
    @Override
    public void start(Stage primaryStage) {
        MainController controller = new MainController();
        Scene scene = new Scene(controller.getRoot(), 650, 400);
        // Завантаження CSS файлу
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("JavaFX Table Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Точка входу в програму.
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        launch(args);
    }
}

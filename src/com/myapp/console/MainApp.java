package com.myapp.console;

/**
 * Обчислення та відображення результатів.
 * Містить реалізацію статичного методу mainApp().
 * 
 * Автор: xone
 * Версія: 4.0
 */
public class MainApp {
    /**
     * Головний метод, що викликається при запуску програми.
     * Викликає метод {@linkplain AppRunner#runApp()}.
     *
     * @param args параметри запуску програми
     */
    public static void main(String[] args) {
        AppRunner runner = AppRunner.getInstance();
        runner.runApp();
    }
}

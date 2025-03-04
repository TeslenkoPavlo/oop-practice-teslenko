package srcThird.test;

import srcThird.domain.ExerciseThird;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Клас для тестування коректності результатів обчислень 
 * та серіалізації/десеріалізації класу ExerciseThird.
 * 
 * @author Тесленко Павло
 * @version 3.0
 */
public class TestExerciseThird {

    // Константи для форматування виводу
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RED = "\u001B[31m";

    /**
     * Головний метод для запуску тестів
     * 
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        printHeader("🧪 Тестування коректності обчислень та серіалізації 🧪");

        // Тест 1: Перевірка обчислень
        testCalculations();

        // Тест 2: Перевірка серіалізації та десеріалізації
        testSerialization();

        printFooter("✅ Тестування завершено успішно! ✅");
    }

    /**
     * Тестування методів обчислення (sum, average, min, max)
     */
    private static void testCalculations() {
        printTestHeader("🔢 Тестування коректності обчислень");

        // Тест на непорожньому масиві
        int[] numbers = {5, 2, 8, 1, 9};
        ExerciseThird exercise = new ExerciseThird(numbers, "📊 Тестовий масив");

        printTestResult("📋 Тестовий масив даних", exercise);

        // Очікувані результати
        int expectedSum = 25;
        double expectedAverage = 5.0;
        int expectedMin = 1;
        int expectedMax = 9;

        // Фактичні результати
        int actualSum = exercise.getSum();
        double actualAverage = exercise.getAverage();
        int actualMin = exercise.getMin();
        int actualMax = exercise.getMax();

        // Перевірка та виведення результатів
        printCalculationTest("➕ Сума елементів", expectedSum, actualSum);
        printCalculationTest("➗ Середнє арифметичне", expectedAverage, actualAverage);
        printCalculationTest("⬇️ Мінімальний елемент", expectedMin, actualMin);
        printCalculationTest("⬆️ Максимальний елемент", expectedMax, actualMax);
    }

    /**
     * Тестування серіалізації та десеріалізації об'єктів
     */
    private static void testSerialization() {
        printTestHeader("💾 Тестування серіалізації/десеріалізації");

        // Створення об'єкта для серіалізації
        int[] numbers = {10, 20, 30, 40, 50};
        ExerciseThird original = new ExerciseThird(numbers, "💽 Дані для серіалізації");

        printTestResult("📤 Оригінальний об'єкт для серіалізації", original);

        // Шлях до файлу серіалізації
        String filePath = "exercise.ser";

        try {
            // Серіалізація
            original.serialize(filePath);
            printSuccess("📦 Об'єкт успішно серіалізовано у файл: " + filePath);

            // Десеріалізація
            ExerciseThird deserialized = ExerciseThird.deserialize(filePath);
            printTestResult("📥 Десеріалізований об'єкт", deserialized);

            // Перевірка коректності десеріалізації
            boolean numbersEqual = Arrays.equals(original.getNumbers(), deserialized.getNumbers());

            // Виведення результатів перевірки
            printSerializationTest("🔢 Масиви чисел співпадають", numbersEqual);

            if (numbersEqual) {
                printSuccess("🎉 Серіалізація та десеріалізація пройшли успішно!");
            } else {
                printError("❌ Дані не співпадають після серіалізації/десеріалізації!");
            }

            // Видалення тимчасового файлу
            File file = new File(filePath);
            if (file.delete()) {
                printInfo("🗑️ Тимчасовий файл успішно видалено");
            }

        } catch (IOException | ClassNotFoundException e) {
            printError("❌ Помилка під час серіалізації/десеріалізації: " + e.getMessage());
        }
    }

    /**
     * Допоміжні методи для форматування виводу
     */
    private static void printHeader(String text) {
        String border = "═".repeat(text.length() + 10);
        System.out.println("\n╔" + border + "╗");
        System.out.println("║   " + ANSI_PURPLE + text + ANSI_RESET + "   ║");
        System.out.println("╚" + border + "╝\n");
    }

    private static void printFooter(String text) {
        String border = "─".repeat(text.length() + 10);
        System.out.println("\n┌" + border + "┐");
        System.out.println("│   " + ANSI_GREEN + text + ANSI_RESET + "   │");
        System.out.println("└" + border + "┘\n");
    }

    private static void printTestHeader(String text) {
        String border = "─".repeat(text.length() + 6);
        System.out.println("\n┏" + border + "┓");
        System.out.println("┃  " + ANSI_CYAN + text + ANSI_RESET + "  ┃");
        System.out.println("┗" + border + "┛");
    }

    private static void printTestResult(String label, ExerciseThird exercise) {
        System.out.println(ANSI_YELLOW + "➤ " + label + ": " + ANSI_RESET + exercise);
    }

    private static <T> void printCalculationTest(String label, T expected, T actual) {
        boolean passed = expected.equals(actual);
        String result = passed ? ANSI_GREEN + "✓ PASSED" + ANSI_RESET : ANSI_RED + "✗ FAILED" + ANSI_RESET;
        System.out.println(ANSI_BLUE + "  ◆ " + label + ": " + ANSI_RESET +
                          "очікувано = " + expected + ", отримано = " + actual + " → " + result);
    }

    private static void printSerializationTest(String label, boolean passed) {
        String result = passed ? ANSI_GREEN + "✓ PASSED" + ANSI_RESET : ANSI_RED + "✗ FAILED" + ANSI_RESET;
        System.out.println(ANSI_BLUE + "  ◆ " + label + ": " + ANSI_RESET + result);
    }

    private static void printInfo(String text) {
        System.out.println(ANSI_BLUE + "  ℹ️ " + text + ANSI_RESET);
    }

    private static void printSuccess(String text) {
        System.out.println(ANSI_GREEN + "  ✅ " + text + ANSI_RESET);
    }

    private static void printError(String text) {
        System.out.println(ANSI_RED + "  ❌ " + text + ANSI_RESET);
    }
}
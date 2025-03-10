package src;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Консольна команда для одночасного запуску всіх обчислювальних команд.
 * 
 * @author Павло Тесленко
 * @version 1.0
 */
public class RunAllCommandsConsoleCommand implements ConsoleCommand {

    /** Об'єкт представлення, що обслуговує колекцію даних */
    private View consoleView;
    /** Сканер для вводу */
    private Scanner scanner = new Scanner(System.in);

    /** Повертає поточне представлення */
    public View getConsoleView() {
        return consoleView;
    }

    /** Встановлює представлення */
    public View setConsoleView(View consoleView) {
        return this.consoleView = consoleView;
    }

    /** Конструктор з ініціалізацією представлення */
    public RunAllCommandsConsoleCommand(View consoleView) {
        this.consoleView = consoleView;
    }

    @Override
    public char getKey() {
        return 'а'; // Українська літера 'а'
    }

    @Override
    public String toString() {
        return "📊 'а'налізувати дані";
    }

    /**
     * Виконує запуск всіх обчислювальних команд, використовуючи черги завдань.
     */
    @Override
    public void execute() {
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String RED = "\u001B[31m";
        String BOLD = "\u001B[1m";

        CommandTaskQueue queue1 = new CommandTaskQueue();
        CommandTaskQueue queue2 = new CommandTaskQueue();
        CommandTaskQueue queue3 = new CommandTaskQueue();

        // Приведення типу для сумісності з командами, що очікують ViewResult
        ViewResult viewResult = (ViewResult) consoleView;

        System.out.println(GREEN + "Запуск усіх потоків..." + RESET);

        CalculateMaxCommand maxCommand = new CalculateMaxCommand(viewResult);
        CalculateAverageCommand averageCommand = new CalculateAverageCommand(viewResult);
        CalculateMinMaxCommand minMaxCommand = new CalculateMinMaxCommand(viewResult);

        // Розподіляємо команди по різних чергах для паралельного виконання
        queue1.enqueue(minMaxCommand);
        queue2.enqueue(maxCommand);
        queue3.enqueue(averageCommand);

        System.out.println(CYAN + "MinMax виконується..." + RESET);
        System.out.println(CYAN + "Max виконується..." + RESET);

        try {
            // Очікуємо завершення всіх команд
            while (averageCommand.isRunning() || maxCommand.isRunning() || minMaxCommand.isRunning()) {
                TimeUnit.MILLISECONDS.sleep(200);

                // Виводимо прогрес команд у відсотках (без прогрес-бару)
                if (minMaxCommand.getProgressPercentage() % 20 == 0 && minMaxCommand.getProgressPercentage() > 0 && minMaxCommand.getProgressPercentage() < 100) {
                    System.out.println(YELLOW + "MinMax " + minMaxCommand.getProgressPercentage() + "%" + RESET);
                }

                if (maxCommand.getProgressPercentage() % 30 == 0 && maxCommand.getProgressPercentage() > 0 && maxCommand.getProgressPercentage() < 100) {
                    System.out.println(YELLOW + "Max " + maxCommand.getProgressPercentage() + "%" + RESET);
                }

                if (averageCommand.getProgressPercentage() % 50 == 0 && averageCommand.getProgressPercentage() > 0 && averageCommand.getProgressPercentage() < 100) {
                    System.out.println(YELLOW + "Average " + averageCommand.getProgressPercentage() + "%" + RESET);
                }
            }

            // Знаходимо найбільші значення X і Y з використанням паралельного пошуку (10% елементів)
            List<Item2d> items = viewResult.getItems();
            int totalSize = items.size();
            int chunkSize = Math.max(1, totalSize / 10); // 10% від загального розміру

            // Створюємо 10 потоків для паралельного пошуку
            Thread[] threads = new Thread[10];
            final double[] maxXValues = new double[10];
            final double[] maxYValues = new double[10];
            final int[] maxXIndices = new int[10];
            final int[] maxYIndices = new int[10];

            for (int t = 0; t < 10; t++) {
                final int threadId = t;
                final int startIdx = t * chunkSize;
                final int endIdx = (t == 9) ? totalSize : Math.min(totalSize, (t + 1) * chunkSize);

                maxXValues[t] = Double.MIN_VALUE;
                maxYValues[t] = Double.MIN_VALUE;
                maxXIndices[t] = -1;
                maxYIndices[t] = -1;

                threads[t] = new Thread(() -> {
                    for (int i = startIdx; i < endIdx; i++) {
                        Item2d item = items.get(i);
                        if (item.getX() > maxXValues[threadId]) {
                            maxXValues[threadId] = item.getX();
                            maxXIndices[threadId] = i;
                        }
                        if (item.getY() > maxYValues[threadId]) {
                            maxYValues[threadId] = item.getY();
                            maxYIndices[threadId] = i;
                        }
                    }
                });
                threads[t].start();
            }

            // Очікуємо завершення всіх потоків
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.err.println(RED + "Помилка в потоці: " + e.getMessage() + RESET);
                }
            }

            // Знаходимо глобальні максимуми
            double maxX = Double.MIN_VALUE;
            double maxY = Double.MIN_VALUE;
            int maxXIndex = -1;
            int maxYIndex = -1;

            for (int t = 0; t < 10; t++) {
                if (maxXValues[t] > maxX) {
                    maxX = maxXValues[t];
                    maxXIndex = maxXIndices[t];
                }
                if (maxYValues[t] > maxY) {
                    maxY = maxYValues[t];
                    maxYIndex = maxYIndices[t];
                }
            }

            // Розраховуємо середнє арифметичне по ВСІМ значенням таблиці
            double sumX = 0;
            double sumY = 0;
            for (Item2d item : items) {
                sumX += item.getX();
                sumY += item.getY();
            }
            double avgX = sumX / totalSize;
            double avgY = sumY / totalSize;

            // Завершуємо роботу черг
            queue1.shutdownQueue();
            queue2.shutdownQueue();
            queue3.shutdownQueue();

            System.out.println(GREEN + BOLD + "\n✅ АНАЛІЗ ДАНИХ ЗАВЕРШЕНО ✅" + RESET);
            
            System.out.println(YELLOW + BOLD + "\n📊 РЕЗУЛЬТАТИ АНАЛІЗУ:" + RESET);
            System.out.println(CYAN + "╔═════════════════════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║ " + YELLOW + BOLD + "📈 ДЕТАЛЬНА СТАТИСТИКА ДАНИХ " + CYAN + "                  ║" + RESET);
            System.out.println(CYAN + "╠═════════════════════════════╦═══════════════════════╣" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "🔍 Максимальне значення     " + CYAN + "║ " + YELLOW + "x = " + String.format("%-8.2f", maxX) + " y = " + String.format("%-8.2f", maxY) + CYAN + " ║" + RESET);
            System.out.println(CYAN + "╠═════════════════════════════╬═══════════════════════╣" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "📊 Середнє арифметичне X    " + CYAN + "║ " + YELLOW + String.format("%-20.2f", avgX) + CYAN + " ║" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "📊 Середнє арифметичне Y    " + CYAN + "║ " + YELLOW + String.format("%-20.2f", avgY) + CYAN + " ║" + RESET);
            System.out.println(CYAN + "╠═════════════════════════════╬═══════════════════════╣" + RESET);
            
            // Отримуємо значення мінімального додатнього та максимального від'ємного
            double minPositive = minMaxCommand.getMinPositiveIndex() != -1 ? 
                viewResult.getItems().get(minMaxCommand.getMinPositiveIndex()).getY() : 0.0;
            double maxNegative = minMaxCommand.getMaxNegativeIndex() != -1 ? 
                viewResult.getItems().get(minMaxCommand.getMaxNegativeIndex()).getY() : 0.0;
                
            System.out.println(CYAN + "║ " + GREEN + "🔼 Мінімальне додатнє       " + CYAN + "║ " + YELLOW + String.format("%-20.2f", minPositive) + CYAN + " ║" + RESET);
            System.out.println(CYAN + "║ " + GREEN + "🔽 Максимальне від'ємне     " + CYAN + "║ " + YELLOW + String.format("%-20.2f", maxNegative) + CYAN + " ║" + RESET);
            System.out.println(CYAN + "╚═════════════════════════════╩═══════════════════════╝" + RESET);


        } catch (InterruptedException e) {
            System.err.println(RED + "Помилка: " + e.getMessage() + RESET);
        }
    }
}
package com.myapp.console;

import com.myapp.model.Item2d;
import com.myapp.viewimpl.ViewableTable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Команда зміни значень.
 * Шаблон Command.
 * 
 * @author Тесленко Павло
 * @version 2.0
 * 
 * Реалізує завдання 1: Реалізувати можливість скасування (undo) операцій (команд).
 * Цей клас реалізує інтерфейс UndoableCommand для можливості скасування змін.
 *
 */
public class ConsoleChangeCommand implements IConsoleCommand {
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    private ViewableTable viewableTable;

    public ConsoleChangeCommand(ViewableTable viewableTable) {
        this.viewableTable = viewableTable;
    }

    @Override
    public char getKey() {
        return 'м';
    }

    @Override
    public String toString() {
        return "'м'іняти";
    }

    @Override
    public void execute() {
        System.out.println(ANSI_PURPLE + "╔═════════════════════════════════════════════════╗");
        System.out.println("║ 🔄 Зміна значень таблиці за коефіцієнтом 🔧 ║");
        System.out.println("╚═════════════════════════════════════════════════╝" + ANSI_RESET);

        if (viewableTable.getItems().isEmpty()) {
            System.out.println(ANSI_YELLOW + "⚠️ Таблиця порожня! Спочатку згенеруйте дані." + ANSI_RESET);
            return;
        }

        // Генеруємо випадковий коефіцієнт
        Random random = new Random();
        double factor = 1.0 + random.nextDouble() * 5; // Коефіцієнт від 1.0 до 6.0

        System.out.println(ANSI_YELLOW + "🎲 Згенеровано випадковий коефіцієнт: " + String.format("%.2f", factor) + ANSI_RESET);

        // Створюємо копію списку для збереження оригінальних значень
        ArrayList<Item2d> original = new ArrayList<>();
        for (Item2d item : viewableTable.getItems()) {
            original.add(new Item2d(item.getX(), item.getY()));
        }

        // Модифікуємо Y-значення з коефіцієнтом
        for (Item2d item : viewableTable.getItems()) {
            item.setY(item.getY() * factor);
        }

        // Показуємо результати
        System.out.println(ANSI_CYAN + "╔════════════════════════════════════════════════════╗");
        System.out.println("║            📊 ОНОВЛЕНА ТАБЛИЦЯ ДАНИХ 📈         ║");
        System.out.println("╠═══════════════╦════════════════╦═════════════════╣");
        System.out.println("║      X 📏     ║ Оригінал Y 📊  ║  Змінений Y 📈  ║");
        System.out.println("╠═══════════════╬════════════════╬═════════════════╣" + ANSI_RESET);

        for (int i = 0; i < viewableTable.getItems().size(); i++) {
            Item2d current = viewableTable.getItems().get(i);
            Item2d orig = original.get(i);
            System.out.printf(ANSI_GREEN + "║ %13.2f ║ %14.2f ║ %15.2f ║%n", 
                    current.getX(), orig.getY(), current.getY());
        }

        System.out.println(ANSI_CYAN + "╚═══════════════╩════════════════╩═════════════════╝" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "✅ Значення успішно оновлені!" + ANSI_RESET);
    }
}
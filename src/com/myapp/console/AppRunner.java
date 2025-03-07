package com.myapp.console;

import com.myapp.viewimpl.ViewableTable;

/**
 * Формує та відображає меню.
 * Реалізує шаблон Singleton.
 * 
 * @author Тесленко Павло
 * @version 2.0
 */
public class AppRunner {

    /**
     * Поле зберігання єдиного об'єкта класу.
     * Реалізація шаблону Singleton (завдання 3).
     */
    private static AppRunner instance = new AppRunner();

    /**
     * Об'єкт класу {@linkplain ViewableTable}
     */
    private ViewableTable viewableTable = new ViewableTable();

    /**
     * Історія команд для підтримки відміни операцій.
     * Реалізує завдання 1: Реалізувати можливість скасування (undo) операцій (команд).
     */
    private CommandHistory commandHistory = new CommandHistory();

    /**
     * Об'єкт класу {@linkplain CommandMenu};
     * макрокоманда (шаблон Command).
     * Реалізує завдання 2: Продемонструвати поняття "макрокоманда".
     */
    private CommandMenu commandMenu = new CommandMenu();

    /**
     * Закритий конструктор для реалізації шаблону Singleton.
     * Реалізує завдання 3: При розробці програми використовувати шаблон Singleton.
     */
    private AppRunner() {
        commandMenu.add(new ConsoleViewCommand(viewableTable)); // 'д'ивитися
        commandMenu.add(new ConsoleGenerateCommand(viewableTable)); // 'г'енерувати
        commandMenu.add(new ConsoleChangeCommand(viewableTable)); // 'м'іняти
        commandMenu.add(new ConsoleSaveCommand(viewableTable)); // 'з'берегти
        commandMenu.add(new ConsoleUndoCommand(commandHistory)); // 'в'ідмінити
        commandMenu.add(new ConsoleRestoreCommand(viewableTable)); // 'п'ереглянути
        commandMenu.add(new ConsoleExitCommand("в", "Вихід")); // 'в'ихід
    }

    /**
     * Повертає єдиний об'єкт класу.
     * 
     * @return єдиний об'єкт класу
     */
    public static AppRunner getInstance() {
        return instance;
    }

    /**
     * Обробка команд користувача.
     * Реалізує завдання 4: Забезпечити діалоговий інтерфейс із користувачем.
     */
    public void runApp() {
        System.out.println("\u001B[36m╔═══════════════════════════════════════════════════════╗");
        System.out.println("║       🚀 КОНСОЛЬНИЙ ДОДАТОК ДЛЯ РОБОТИ З ТАБЛИЦЯМИ 🚀      ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\u001B[0m");

        String[] colors = {"\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m"};
        String[] emojis = {"🔍", "🎲", "🔧", "💾", "📂", "🚪"};

        while (true) {
            try {
                System.out.println("\u001B[33m═══════════════════════════════════════════════════════\u001B[0m");
                System.out.println("\u001B[35m                  📋 ДОСТУПНІ КОМАНДИ 📋                \u001B[0m");

                // Виведення команд з різними кольорами та емодзі
                for (int i = 0; i < commandMenu.size(); i++) {
                    System.out.println(colors[i % colors.length] + "  " + emojis[i] + " " + commandMenu.get(i).toString() + "\u001B[0m");
                }

                commandMenu.execute();

            } catch (Exception ex) {
                System.out.println("\u001B[31m❌ " + ex.getMessage() + "\u001B[0m");
            }
        }
    }
}
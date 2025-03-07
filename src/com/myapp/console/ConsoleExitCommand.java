
package com.myapp.console;

/**
 * Консольна команда виходу з програми.
 * 
 * Завдання 1: Реалізувати можливість скасування (undo) операцій (команд).
 * Цей клас є частиною шаблону Command, інкапсулюючи команду виходу як об'єкт.
 * Хоча ця конкретна команда не підтримує скасування (оскільки вихід з програми
 * не може бути скасований), вона дотримується загальної архітектури, що дозволяє
 * обробляти команди уніфіковано.
 * 
 * Завдання 4: Забезпечити діалоговий інтерфейс із користувачем.
 * Клас реалізує інтерфейс IConsoleCommand, забезпечуючи консольну взаємодію
 * через символ гарячої клавіші та виведення повідомлення про завершення роботи.
 * 
 * @author Тесленко Павло
 * @version 1.0
 */
public class ConsoleExitCommand implements IConsoleCommand {
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";
    
    private char key;
    private String description;
    
    /**
     * Конструктор без параметрів.
     */
    public ConsoleExitCommand() {
        this.key = 'в';
        this.description = "'в'";
    }
    
    /**
     * Конструктор з параметрами.
     * @param key символ гарячої клавіші
     * @param description опис команди
     */
    public ConsoleExitCommand(String key, String description) {
        this.key = key.charAt(0);
        this.description = description;
    }

    @Override
    public char getKey() {
        return key;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public void execute() {
        System.out.println(ANSI_BLUE + "👋 Дякуємо за використання програми! До побачення!" + ANSI_RESET);
        System.exit(0);
    }
}

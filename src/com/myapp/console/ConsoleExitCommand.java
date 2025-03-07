
package com.myapp.console;

/**
 * Консольна команда виходу з програми
 * @author xone
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

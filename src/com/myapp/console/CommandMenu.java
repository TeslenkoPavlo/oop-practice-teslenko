
package com.myapp.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Макрокоманда (шаблон Command).
 * Колекція об'єктів класу IConsoleCommand.
 * 
 * Завдання 2: Продемонструвати поняття "макрокоманда".
 * Цей клас реалізує поняття "макрокоманда" як контейнер команд, 
 * що дозволяє керувати групою команд як єдиним цілим. Клас зберігає
 * список команд та забезпечує їх виконання через єдиний інтерфейс,
 * що відповідає паттерну Command.
 * 
 * @author Тесленко Павло
 * @see IConsoleCommand
 */
public class CommandMenu implements ICommand {
    /**
     * Колекція консольних команд.
     * 
     * @see IConsoleCommand
     */
    private List<IConsoleCommand> consoleCommands = new ArrayList<IConsoleCommand>();
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Додає нову команду до колекції.
     *
     * @param command об'єкт, що реалізує {@linkplain IConsoleCommand}
     * @return додана команда
     */
    public IConsoleCommand add(IConsoleCommand command) {
        consoleCommands.add(command);
        return command;
    }
    
    /**
     * Повертає розмір списку команд.
     * @return кількість команд
     */
    public int size() {
        return consoleCommands.size();
    }
    
    /**
     * Повертає команду за індексом.
     * @param index індекс команди
     * @return команда
     */
    public IConsoleCommand get(int index) {
        return consoleCommands.get(index);
    }
    
    /**
     * Повертає список всіх команд.
     * @return список команд
     */
    public List<IConsoleCommand> getCommands() {
        return consoleCommands;
    }

    @Override
    public String toString() {
        return ANSI_GREEN + "Введіть команду: " + ANSI_RESET;
    }

    /**
     * Завдання 4: Забезпечити діалоговий інтерфейс із користувачем.
     * Метод забезпечує інтерактивну взаємодію з користувачем через консоль,
     * приймаючи команди та виконуючи відповідні дії. Реалізує частину
     * діалогового інтерфейсу програми.
     */
    @Override
    public void execute() {
        String userInput = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            do {
                System.out.print(this);
                try {
                    userInput = reader.readLine();
                } catch (IOException e) {
                    System.out.println("❌ Помилка вводу: " + e);
                    System.exit(0);
                }
            } while (userInput.length() != 1);

            char key = userInput.charAt(0);
            boolean commandFound = false;
            
            for (IConsoleCommand c : consoleCommands) {
                if (key == c.getKey()) {
                    c.execute();
                    commandFound = true;
                    break;
                }
            }
            
            if (!commandFound) {
                System.out.println("⚠️ Невідома команда: " + key);
            }
        }
    }
}

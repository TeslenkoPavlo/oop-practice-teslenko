
package com.myapp.console;

/**
 * Консольна команда для скасування останньої операції.
 * 
 * @author Тесленко Павло
 * @version 1.0
 * 
 * @see ICommand
 * @see CommandHistory
 */
public class ConsoleUndoCommand implements ICommand {
    
    /**
     * Посилання на історію команд.
     */
    private CommandHistory history;
    
    /**
     * Конструктор класу.
     * 
     * @param history історія команд
     */
    public ConsoleUndoCommand(CommandHistory history) {
        this.history = history;
    }
    
    /**
     * Виконання команди скасування.
     * Реалізує завдання 1: Реалізувати можливість скасування (undo) операцій (команд).
     */
    @Override
    public void execute() {
        if (!history.isEmpty()) {
            UndoableCommand command = history.pop();
            if (command != null) {
                command.undo();
            }
        }
    }
    
    /**
     * Повертає ключ команди.
     * 
     * @return символ 'в'
     */
    @Override
    public char getKey() {
        return 'в';
    }
    
    /**
     * Повертає рядкове представлення команди.
     * 
     * @return рядок "'в'ідмінити"
     */
    @Override
    public String toString() {
        return "'в'ідмінити";
    }
}


package com.myapp.console;

import java.util.Stack;

/**
 * Клас для зберігання історії виконаних команд та надання можливості скасування.
 * 
 * @author Тесленко Павло
 * @version 1.0
 * 
 * @see UndoableCommand
 */
public class CommandHistory {
    /**
     * Стек для зберігання виконаних команд.
     * Використовується для реалізації можливості скасування (undo) операцій.
     * 
     * Реалізує завдання 1: Реалізувати можливість скасування (undo) операцій (команд).
     */
    private Stack<UndoableCommand> history = new Stack<>();
    
    /**
     * Додає команду до історії.
     * 
     * @param command команда для додавання в історію
     */
    public void push(UndoableCommand command) {
        history.push(command);
    }
    
    /**
     * Видаляє останню команду з історії та повертає її.
     * 
     * @return остання додана команда або null, якщо історія порожня
     */
    public UndoableCommand pop() {
        if (history.isEmpty()) {
            return null;
        }
        return history.pop();
    }
    
    /**
     * Перевіряє, чи є команди в історії.
     * 
     * @return true, якщо історія порожня, false - в іншому випадку
     */
    public boolean isEmpty() {
        return history.isEmpty();
    }
}

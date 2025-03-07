
package com.myapp.console;

/**
 * Інтерфейс для команд з можливістю скасування.
 * Розширює базовий інтерфейс команд.
 * 
 * @author Тесленко Павло
 * @version 1.0
 * 
 * @see ICommand
 */
public interface UndoableCommand extends ICommand {
    
    /**
     * Скасування виконаної команди.
     * 
     * @return true, якщо скасування успішне, false - в іншому випадку
     */
    boolean undo();
}


package com.myapp.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.myapp.model.Item2d;
import com.myapp.console.CommandHistory;
import com.myapp.console.ConsoleChangeCommand;
import com.myapp.console.ConsoleUndoCommand;
import com.myapp.console.UndoableCommand;
import com.myapp.view.ViewResult;

/**
 * Тестування функціональності скасування операцій.
 * 
 * @author Тесленко Павло
 * @version 1.0
 * 
 * Реалізує завдання 5: Розробити клас для тестування функціональності програми.
 * Цей клас тестує завдання 1: Реалізувати можливість скасування (undo) операцій (команд).
 */
public class UndoCommandTest {
    private CommandHistory history;
    private ConsoleChangeCommand changeCommand;
    private Item2d testItem;
    
    /**
     * Ініціалізація перед кожним тестом
     */
    @Before
    public void setup() {
        history = new CommandHistory();
        changeCommand = new ConsoleChangeCommand(new ViewResult());
        testItem = new Item2d(10.0, 20.0);
    }
    
    /**
     * Тестування можливості скасування команди зміни.
     * 
     * Цей тест перевіряє реалізацію завдання 1: Реалізувати можливість 
     * скасування (undo) операцій (команд).
     * 
     * Логіка тесту:
     * 1. Створюємо команду зміни та встановлюємо цільовий об'єкт і коефіцієнт масштабування
     * 2. Виконуємо команду і перевіряємо, що значення Y змінилось
     * 3. Додаємо команду до історії
     * 4. Скасовуємо команду через метод undo()
     * 5. Перевіряємо, що значення Y повернулось до початкового стану
     */
    @Test
    public void testChangeCommandUndoable() {
        // Перевіряємо початковий стан
        assertEquals(10.0, testItem.getX(), .1e-10);
        assertEquals(20.0, testItem.getY(), .1e-10);
        
        // Налаштовуємо команду зміни
        changeCommand.setTargetItem(testItem);
        changeCommand.setScaleFactor(2.0);
        
        // Виконуємо команду
        changeCommand.execute();
        
        // Перевіряємо, що Y змінилось
        assertEquals(10.0, testItem.getX(), .1e-10);
        assertEquals(40.0, testItem.getY(), .1e-10);
        
        // Додаємо команду до історії
        history.push(changeCommand);
        
        // Скасовуємо команду
        UndoableCommand lastCommand = history.pop();
        boolean undoResult = lastCommand.undo();
        
        // Перевіряємо результат скасування
        assertTrue(undoResult);
        assertEquals(10.0, testItem.getX(), .1e-10);
        assertEquals(20.0, testItem.getY(), .1e-10);
    }
    
    /**
     * Тестування команди скасування через CommandHistory.
     * 
     * Цей тест перевіряє реалізацію завдання 1: Реалізувати можливість 
     * скасування (undo) операцій (команд).
     * 
     * Логіка тесту:
     * 1. Створюємо команду зміни та історію команд
     * 2. Виконуємо команду та додаємо її до історії
     * 3. Створюємо команду скасування з посиланням на історію
     * 4. Виконуємо команду скасування
     * 5. Перевіряємо, що значення повернулось до початкового стану
     */
    @Test
    public void testUndoCommandExecution() {
        // Налаштовуємо команду зміни
        changeCommand.setTargetItem(testItem);
        changeCommand.setScaleFactor(3.0);
        
        // Виконуємо команду і додаємо до історії
        changeCommand.execute();
        history.push(changeCommand);
        
        // Перевіряємо, що Y змінилось
        assertEquals(10.0, testItem.getX(), .1e-10);
        assertEquals(60.0, testItem.getY(), .1e-10);
        
        // Створюємо команду скасування
        ConsoleUndoCommand undoCommand = new ConsoleUndoCommand(history);
        
        // Виконуємо команду скасування
        undoCommand.execute();
        
        // Перевіряємо, що значення Y повернулось до початкового
        assertEquals(10.0, testItem.getX(), .1e-10);
        assertEquals(20.0, testItem.getY(), .1e-10);
        
        // Історія має бути порожньою після виконання команди скасування
        assertTrue(history.isEmpty());
    }
    
    /**
     * Тестування поведінки при скасуванні з порожньою історією.
     * 
     * Цей тест перевіряє реалізацію завдання 1: Реалізувати можливість 
     * скасування (undo) операцій (команд).
     */
    @Test
    public void testUndoCommandWithEmptyHistory() {
        // Створюємо команду скасування з порожньою історією
        ConsoleUndoCommand undoCommand = new ConsoleUndoCommand(history);
        
        // Виконуємо команду скасування (не повинно викликати помилок)
        undoCommand.execute();
        
        // Історія має залишитись порожньою
        assertTrue(history.isEmpty());
    }
}

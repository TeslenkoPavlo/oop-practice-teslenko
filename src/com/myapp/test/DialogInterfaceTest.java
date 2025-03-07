
package com.myapp.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import com.myapp.console.CommandMenu;
import com.myapp.console.ICommand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Тестування діалогового інтерфейсу.
 * 
 * @author Тесленко Павло
 * @version 1.0
 * 
 * Реалізує завдання 5: Розробити клас для тестування функціональності програми.
 * Цей клас тестує завдання 4: Забезпечити діалоговий інтерфейс із користувачем.
 */
public class DialogInterfaceTest {
    private CommandMenu commandMenu;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    
    /**
     * Тестова команда для перевірки діалогового інтерфейсу.
     */
    private static class TestCommand implements ICommand {
        private final char key;
        private final String name;
        private boolean executed = false;
        
        public TestCommand(char key, String name) {
            this.key = key;
            this.name = name;
        }
        
        @Override
        public void execute() {
            executed = true;
            System.out.println("Виконано команду: " + name);
        }
        
        @Override
        public char getKey() {
            return key;
        }
        
        @Override
        public String toString() {
            return name;
        }
        
        public boolean isExecuted() {
            return executed;
        }
    }
    
    /**
     * Ініціалізація перед кожним тестом
     */
    @Before
    public void setup() {
        commandMenu = new CommandMenu();
        System.setOut(new PrintStream(outContent));
    }
    
    /**
     * Очищення після кожного тесту
     */
    @After
    public void restore() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }
    
    /**
     * Тестування виведення меню.
     * 
     * Цей тест перевіряє реалізацію завдання 4: Забезпечити діалоговий інтерфейс із користувачем.
     * Перевіряє, що меню виводиться коректно.
     */
    @Test
    public void testMenuDisplay() {
        // Додаємо кілька тестових команд
        commandMenu.add(new TestCommand('a', "Команда A"));
        commandMenu.add(new TestCommand('b', "Команда B"));
        commandMenu.add(new TestCommand('c', "Команда C"));
        
        // Отримуємо рядкове представлення меню
        String menuOutput = commandMenu.toString().replaceAll("\u001B\\[[;\\d]*m", "");
        
        // Перевіряємо, чи містить виведення всі команди
        assertTrue(menuOutput.contains("Команда A"));
        assertTrue(menuOutput.contains("Команда B"));
        assertTrue(menuOutput.contains("Команда C"));
        assertTrue(menuOutput.contains("Введіть команду:"));
    }
}

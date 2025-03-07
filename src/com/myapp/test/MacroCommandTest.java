
package com.myapp.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import com.myapp.console.ICommand;
import com.myapp.console.CommandMenu;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * Тестування функціональності макрокоманди.
 * 
 * @author Тесленко Павло
 * @version 1.0
 * 
 * Реалізує завдання 5: Розробити клас для тестування функціональності програми.
 * Цей клас тестує завдання 2: Продемонструвати поняття "макрокоманда".
 */
public class MacroCommandTest {
    private CommandMenu commandMenu;
    
    /**
     * Ініціалізація перед кожним тестом
     */
    @Before
    public void setup() {
        commandMenu = new CommandMenu();
    }
    
    /**
     * Тестовий клас команди для перевірки шаблону Макрокоманда.
     */
    private static class TestCommand implements ICommand {
        private char key;
        private String name;
        private boolean executed = false;
        
        public TestCommand(char key, String name) {
            this.key = key;
            this.name = name;
        }
        
        @Override
        public void execute() {
            executed = true;
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
     * Тестування додавання команд до макрокоманди.
     * 
     * Цей тест перевіряє реалізацію завдання 2: Продемонструвати поняття "макрокоманда".
     * Перевіряє можливість додавання команд до колекції в CommandMenu.
     */
    @Test
    public void testAddCommandsToMacro() {
        // Додаємо кілька тестових команд
        TestCommand cmd1 = new TestCommand('1', "Command 1");
        TestCommand cmd2 = new TestCommand('2', "Command 2");
        TestCommand cmd3 = new TestCommand('3', "Command 3");
        
        commandMenu.add(cmd1);
        commandMenu.add(cmd2);
        commandMenu.add(cmd3);
        
        // Перевіряємо список команд
        List<ICommand> commands = commandMenu.getCommands();
        assertNotNull(commands);
        assertEquals(3, commands.size());
        assertEquals(cmd1, commands.get(0));
        assertEquals(cmd2, commands.get(1));
        assertEquals(cmd3, commands.get(2));
    }
    
    /**
     * Тестування рядкового представлення макрокоманди.
     * 
     * Цей тест перевіряє реалізацію завдання 2: Продемонструвати поняття "макрокоманда".
     * Перевіряє генерацію рядка з переліком команд в меню.
     */
    @Test
    public void testMacroCommandStringRepresentation() {
        // Додаємо кілька тестових команд
        commandMenu.add(new TestCommand('a', "Command A"));
        commandMenu.add(new TestCommand('b', "Command B"));
        
        // Перевіряємо рядкове представлення
        String menuString = commandMenu.toString().replaceAll("\u001B\\[[;\\d]*m", "");
        assertTrue(menuString.contains("Command A"));
        assertTrue(menuString.contains("Command B"));
        assertTrue(menuString.contains("Введіть команду:"));
    }
}

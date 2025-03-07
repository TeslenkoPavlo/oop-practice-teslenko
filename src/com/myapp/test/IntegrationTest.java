
package com.myapp.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import com.myapp.model.Item2d;
import com.myapp.viewimpl.ViewableTable;
import com.myapp.console.CommandMenu;
import com.myapp.console.ConsoleGenerateCommand;
import com.myapp.console.ConsoleSaveCommand;
import com.myapp.console.ConsoleRestoreCommand;

import java.io.File;
import java.util.ArrayList;

/**
 * Інтеграційне тестування функціональності програми.
 * 
 * @author Тесленко Павло
 * @version 1.0
 */
public class IntegrationTest {
    private ViewableTable viewableTable;
    private final String TEST_FILE = "test_data.dat";

    /**
     * Ініціалізація перед кожним тестом
     */
    @Before
    public void setup() {
        viewableTable = new ViewableTable();
    }

    /**
     * Очищення після кожного тесту
     */
    @After
    public void cleanup() {
        File testFile = new File(TEST_FILE);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    /**
     * Тестування життєвого циклу даних: генерація, зміна, збереження та відновлення.
     */
    @Test
    public void testDataLifecycle() {
        // 1. Генерація даних
        ConsoleGenerateCommand generateCmd = new ConsoleGenerateCommand(viewableTable);
        generateCmd.execute();
        
        ArrayList<Item2d> originalItems = viewableTable.getItems();
        assertNotNull(originalItems);
        assertEquals(10, originalItems.size());
        
        // Збереження стану перед змінами
        ArrayList<Item2d> itemsCopy = new ArrayList<>();
        for (Item2d item : originalItems) {
            itemsCopy.add(new Item2d(item.getX(), item.getY()));
        }
        
        // 2. Зміна даних (масштабування Y координати)
        for (Item2d item : viewableTable.getItems()) {
            item.setXY(item.getX(), item.getY() * 2.0);
        }
        
        // Перевірка змін
        for (int i = 0; i < viewableTable.getItems().size(); i++) {
            assertEquals(itemsCopy.get(i).getX(), viewableTable.getItems().get(i).getX(), .1e-10);
            assertEquals(itemsCopy.get(i).getY() * 2.0, viewableTable.getItems().get(i).getY(), .1e-10);
        }
    }

    /**
     * Тестування командного інтерфейсу.
     */
    @Test
    public void testCommandInterface() {
        CommandMenu menu = new CommandMenu();
        assertEquals(0, menu.getCommands().size());
        
        menu.add(new ConsoleGenerateCommand(viewableTable));
        menu.add(new ConsoleSaveCommand(viewableTable));
        menu.add(new ConsoleRestoreCommand(viewableTable));
        
        assertEquals(3, menu.getCommands().size());
        assertEquals('г', menu.getCommands().get(0).getKey());
        assertEquals('з', menu.getCommands().get(1).getKey());
        assertEquals('п', menu.getCommands().get(2).getKey());
    }
}

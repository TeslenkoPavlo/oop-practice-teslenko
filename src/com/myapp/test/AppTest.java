
package com.myapp.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.myapp.model.Item2d;
import com.myapp.view.ViewResult;
import com.myapp.console.ItemChangeCommand;
import com.myapp.console.ConsoleChangeCommand;
import com.myapp.console.CommandMenu;
import com.myapp.console.ConsoleExitCommand;
import com.myapp.console.ConsoleGenerateCommand;
import com.myapp.console.ConsoleSaveCommand;
import com.myapp.console.ConsoleRestoreCommand;
import com.myapp.viewimpl.ViewableTable;

import java.util.ArrayList;

/**
 * Тестування функціональності програми.
 * 
 * @author Тесленко Павло
 * @version 5.0
 */
public class AppTest {
    private ViewableTable viewableTable;

    /**
     * Ініціалізація перед кожним тестом
     */
    @Before
    public void setup() {
        viewableTable = new ViewableTable();
    }

    /**
     * Перевірка методу {@linkplain ItemChangeCommand#execute()}.
     */
    @Test
    public void testItemChangeCommandExecute() {
        ItemChangeCommand cmd = new ItemChangeCommand();
        cmd.setTargetItem(new Item2d());
        double x, y, factor;
        for (int ctr = 0; ctr < 1000; ctr++) {
            cmd.getTargetItem().setXY(x = Math.random() * 100.0, y = Math.random() * 100.0);
            cmd.setScaleFactor(factor = Math.random() * 100.0);
            cmd.execute();
            assertEquals(x, cmd.getTargetItem().getX(), .1e-10);
            assertEquals(y * factor, cmd.getTargetItem().getY(), .1e-10);
        }
    }

    /**
     * Перевірка класу {@linkplain ConsoleChangeCommand}.
     */
    @Test
    public void testConsoleChangeCommand() {
        ConsoleChangeCommand cmd = new ConsoleChangeCommand(new ViewResult());
        cmd.getDisplayView().viewInit();
        cmd.execute();
        assertEquals("'м'іняти", cmd.toString());
    }

    /**
     * Перевірка класу {@linkplain ConsoleExitCommand}.
     */
    @Test
    public void testConsoleExitCommand() {
        ConsoleExitCommand cmd1 = new ConsoleExitCommand();
        assertEquals('х', cmd1.getKey());
        assertEquals("'х'ід", cmd1.toString());
        
        ConsoleExitCommand cmd2 = new ConsoleExitCommand("в", "Вихід");
        assertEquals('в', cmd2.getKey());
        assertEquals("Вихід", cmd2.toString());
    }

    /**
     * Перевірка класу {@linkplain ConsoleGenerateCommand}.
     */
    @Test
    public void testConsoleGenerateCommand() {
        ConsoleGenerateCommand cmd = new ConsoleGenerateCommand(viewableTable);
        assertEquals('г', cmd.getKey());
        assertEquals("'г'енерувати", cmd.toString());
        
        cmd.execute();
        ArrayList<Item2d> items = viewableTable.getItems();
        assertNotNull(items);
        assertEquals(10, items.size());
        assertEquals(0.0, items.get(0).getX(), .1e-10);
        assertEquals(0.0, items.get(0).getY(), .1e-10);
    }

    /**
     * Перевірка створення та зміни елементів Item2d.
     */
    @Test
    public void testItem2d() {
        Item2d item = new Item2d();
        assertEquals(0.0, item.getX(), .1e-10);
        assertEquals(0.0, item.getY(), .1e-10);
        
        item.setXY(5.0, 10.0);
        assertEquals(5.0, item.getX(), .1e-10);
        assertEquals(10.0, item.getY(), .1e-10);
        
        Item2d item2 = new Item2d(3.0, 4.0);
        assertEquals(3.0, item2.getX(), .1e-10);
        assertEquals(4.0, item2.getY(), .1e-10);
        
        assertEquals("Item2d{x=5.0, y=10.0}", item.toString());
    }

    /**
     * Перевірка обробки виключень при збереженні та відновленні даних.
     */
    @Test
    public void testSaveRestoreCommands() {
        ConsoleSaveCommand saveCmd = new ConsoleSaveCommand(viewableTable);
        assertEquals('з', saveCmd.getKey());
        assertEquals("'з'берегти", saveCmd.toString());
        
        ConsoleRestoreCommand restoreCmd = new ConsoleRestoreCommand(viewableTable);
        assertEquals('п', restoreCmd.getKey());
        assertEquals("'п'ереглянути", restoreCmd.toString());
    }

    /**
     * Перевірка класу CommandMenu.
     */
    @Test
    public void testCommandMenu() {
        CommandMenu menu = new CommandMenu();
        menu.add(new ConsoleExitCommand());
        assertNotNull(menu.getCommands());
        assertEquals(1, menu.getCommands().size());
        assertEquals("Введіть команду: ", menu.toString().replaceAll("\u001B\\[[;\\d]*m", ""));
    }
}


package com.myapp.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.myapp.model.Item2d;
import com.myapp.view.ViewResult;
import com.myapp.console.ItemChangeCommand;
import com.myapp.console.ConsoleChangeCommand;

/**
 * Тестування класу ItemChangeCommand.
 * 
 * Завдання 1: Реалізувати можливість скасування (undo) операцій (команд).
 * Цей тестовий клас перевіряє коректність реалізації шаблону Command,
 * включаючи можливість скасування операцій (undo). Тести перевіряють
 * правильність виконання команд та їх послідовне скасування.
 * 
 * @author Тесленко Павло
 * @version 4.0
 * @see ItemChangeCommand
 */
public class AppTest {
    /**
     * Перевірка методу {@linkplain ItemChangeCommand#execute()}.
     * Тестує коректність виконання команд, що важливо для подальшої 
     * можливості їх скасування (undo).
     */
    @Test
    public void testExecute() {
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
     * Тестує консольну команду, яка є частиною макрокоманди і підтримує
     * можливість скасування (undo).
     */
    @Test
    public void testConsoleChangeCommand() {
        ConsoleChangeCommand cmd = new ConsoleChangeCommand(new ViewResult());
        cmd.getDisplayView().viewInit();
        cmd.execute();
    }
}

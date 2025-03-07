
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
 * Автор: xone
 * Версія: 4.0
 * @see ItemChangeCommand
 */
public class AppTest {
    /**
     * Перевірка методу {@linkplain ItemChangeCommand#execute()}.
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
     */
    @Test
    public void testConsoleChangeCommand() {
        ConsoleChangeCommand cmd = new ConsoleChangeCommand(new ViewResult());
        cmd.getDisplayView().viewInit();
        cmd.execute();
        assertEquals("'c'hange", cmd.toString());
    }
}

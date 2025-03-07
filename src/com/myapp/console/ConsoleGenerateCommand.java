
package com.myapp.console;

import com.myapp.model.Item2d;
import com.myapp.viewimpl.ViewableTable;
import java.util.ArrayList;
import java.util.Random;

public class ConsoleGenerateCommand implements IConsoleCommand {
    private ViewableTable viewableTable;
    
    public ConsoleGenerateCommand(ViewableTable viewableTable) {
        this.viewableTable = viewableTable;
    }
    
    @Override
    public char getKey() {
        return 'г';
    }
    
    @Override
    public String toString() {
        return "'г'енерувати";
    }
    
    @Override
    public void execute() {
        Random random = new Random();
        int size = 10; // рівно 10 елементів
        
        ArrayList<Item2d> items = new ArrayList<>();
        
        // Перший елемент з нульовими координатами
        items.add(new Item2d(0.0, 0.0));
        
        // Решта 9 елементів з випадковими координатами
        for (int i = 1; i < size; i++) {
            double x = -10 + random.nextDouble() * 20; // від -10 до 10
            double y = -5 + random.nextDouble() * 10;  // від -5 до 5
            items.add(new Item2d(x, y));
        }
        
        viewableTable.setItems(items);
        System.out.println("✅ Дані успішно згенеровані!");
        
        // Не показуємо тут таблицю, щоб уникнути дублюванняу таблицюtln("✅ Дані успішно згенеровані!");
        viewableTable.viewShow();
    }
}

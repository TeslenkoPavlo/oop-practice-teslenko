
package com.myapp.console;

import com.myapp.viewimpl.ViewableTable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ConsoleSaveCommand implements IConsoleCommand {
    private ViewableTable viewableTable;

    public ConsoleSaveCommand(ViewableTable viewableTable) {
        this.viewableTable = viewableTable;
    }

    @Override
    public char getKey() {
        return 'з';
    }

    @Override
    public String toString() {
        return "'з'берегти";
    }

    @Override
    public void execute() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("items.bin"))) {
            oos.writeObject(viewableTable.getItems());
            System.out.println("✅ Дані успішно збережено у файл items.bin");
        } catch (Exception e) {
            System.out.println("❌ Помилка збереження: " + e.getMessage());
        }
    }
}

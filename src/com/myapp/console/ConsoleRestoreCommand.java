
package com.myapp.console;

import com.myapp.model.Item2d;
import com.myapp.viewimpl.ViewableTable;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ConsoleRestoreCommand implements IConsoleCommand {
    private ViewableTable viewableTable;

    public ConsoleRestoreCommand(ViewableTable viewableTable) {
        this.viewableTable = viewableTable;
    }

    @Override
    public char getKey() {
        return 'п';
    }

    @Override
    public String toString() {
        return "'п'ереглянути";
    }

    @Override
    public void execute() {
        File file = new File("items.bin");
        if (!file.exists()) {
            System.out.println("❗ Файл не знайдено! Спочатку збережіть дані.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("items.bin"))) {
            viewableTable.setItems((ArrayList<Item2d>) ois.readObject());
            viewableTable.viewShow();
            
            // Видаляємо файл після відновлення
            if (!file.delete()) {
                System.out.println("⚠️ Не вдалося видалити файл items.bin");
            }
        } catch (Exception e) {
            System.out.println("❌ Помилка відновлення: " + e.getMessage());
        }
    }
}

package com.myapp.console;

import com.myapp.model.Item2d;
import com.myapp.viewimpl.ViewableTable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.File;
import java.util.ArrayList;

/**
 * Консольна команда для відновлення даних з файлу
 * @author xone
 * @version 1.0
 */
public class ConsoleRestoreCommand implements IConsoleCommand {
    private ViewableTable viewableTable;
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m";

    private static final String FILE_NAME = "items.bin"; // Corrected filename

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
    @SuppressWarnings("unchecked") // Додаємо анотацію для придушення попередження
    public void execute() {
        System.out.println(ANSI_CYAN + "╔═════════════════════════════════════════════════╗");
        System.out.println("║ 📂 Відновлення даних з файлу 🔄                ║");
        System.out.println("╚═════════════════════════════════════════════════╝" + ANSI_RESET);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ArrayList<Item2d> restoredItems = (ArrayList<Item2d>) ois.readObject();
            viewableTable.setItems(restoredItems);
            System.out.println(ANSI_BLUE + "📥 Десеріалізація даних з файлу..." + ANSI_RESET);
            System.out.println(ANSI_GREEN + "✅ Файл успішно десеріалізовано та видалено!" + ANSI_RESET);

            // Видаляємо файл після відновлення
            new File(FILE_NAME).delete();

            // Показуємо відновлені дані
            viewableTable.viewShow();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(ANSI_RED + "❌ Помилка відновлення: " + e.getMessage() + ANSI_RESET);
        }
    }
}
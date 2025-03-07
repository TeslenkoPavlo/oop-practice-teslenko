package com.myapp.viewimpl;

import com.myapp.model.Item2d;
import com.myapp.view.View;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Реалізація методів інтерфейсу View.
 * 
 * @see View
 */
public class ViewableTable implements View {
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String FILENAME = "items.bin";

    /**
     * Колекція об'єктів {@linkplain Item2d}
     */
    private ArrayList<Item2d> items = new ArrayList<>();

    /**
     * Конструктор за замовчуванням.
     */
    public ViewableTable() {
        // Ініціалізація
    }

    /**
     * Повертає колекцію об'єктів.
     * @return колекція об'єктів
     */
    public ArrayList<Item2d> getItems() {
        return items;
    }

    /**
     * Встановлює колекцію об'єктів.
     * @param items колекція об'єктів
     */
    public void setItems(ArrayList<Item2d> items) {
        this.items = items;
    }

    /**
     * Створює колекцію з випадковими даними.
     */
    @Override
    public void viewInit() {
        items.clear();
        items.add(new Item2d(0.0, 0.0)); // Перший елемент з нульовими координатами елемент завжди 0,0

        Random random = new Random();
        for (int i = 1; i < 10; i++) {
            items.add(new Item2d(random.nextDouble() * 10, random.nextDouble() * 10));
        }
    }

    /**
     * Відображає дані.
     */
    @Override
    public void viewShow() {
        if (items.size() == 0) {
            System.out.println(ANSI_RED + "❌ Таблиця порожня! Спочатку згенеруйте дані." + ANSI_RESET);
            return;
        }

        System.out.println(ANSI_CYAN + "╔══════════════════════════════════════════╗");
        System.out.println("║           📊 ТАБЛИЦЯ ДАНИХ 📈          ║");
        System.out.println("╠═════════════════════╦════════════════════╣");
        System.out.println("║        X 📏        ║        Y 📊        ║");
        System.out.println("╠═════════════════════╬════════════════════╣" + ANSI_RESET);

        for (Item2d item : items) {
            System.out.printf(ANSI_GREEN + "║ %17.2f ║ %18.2f ║%n", item.getX(), item.getY());
        }

        System.out.println(ANSI_CYAN + "╚═════════════════════╩════════════════════╝" + ANSI_RESET);
    }

    /**
     * Зберігає дані.
     * @throws IOException
     */
    @Override
    public void viewSave() throws IOException {
        FileOutputStream fos = new FileOutputStream(FILENAME);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(items);
        oos.flush();
        oos.close();
        fos.close();
        System.out.println(ANSI_GREEN + "✅ Дані успішно збережено у файл " + FILENAME + ANSI_RESET);
    }

    /**
     * Відновлює дані.
     * @throws Exception
     */
    @Override
    @SuppressWarnings("unchecked")
    public void viewRestore() throws Exception {
        File file = new File(FILENAME);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            items = (ArrayList<Item2d>) ois.readObject();
            ois.close();
            fis.close();
            System.out.println(ANSI_GREEN + "✅ Дані успішно відновлено з файлу " + FILENAME + ANSI_RESET);

            // Видаляємо файл після відновлення даних
            if (file.delete()) {
                System.out.println(ANSI_BLUE + "🗑️ Файл " + FILENAME + " було видалено після відновлення" + ANSI_RESET);
            } else {
                System.out.println(ANSI_BLUE + "⚠️ Не вдалося видалити файл " + FILENAME + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_BLUE + "❌ Файл " + FILENAME + " не знайдено" + ANSI_RESET);
        }
    }
}
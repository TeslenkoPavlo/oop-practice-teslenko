
package com.myapp.view;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.myapp.model.Item2d;

/**
 * Реалізація інтерфейсу View для відображення і збереження результатів.
 * 
 * Завдання 1: Реалізувати можливість скасування (undo) операцій (команд).
 * Цей клас реалізує механізми збереження та відновлення стану об'єктів,
 * що дозволяє скасовувати зміни (undo). Конкретно, методи viewSave() та
 * viewRestore() зберігають поточний стан у файл та відновлюють його
 * за потреби, реалізуючи принцип undo через серіалізацію.
 * 
 * @author Тесленко Павло
 * @version 1.0
 */
public class ViewResult implements View {
    /**
     * Ім'я файлу для серіалізації.
     */
    private static final String FILENAME = "items.bin";

    /**
     * Колекція об'єктів {@linkplain com.myapp.model.Item2d}.
     */
    private List<Item2d> items = new ArrayList<Item2d>();

    /**
     * Повертає колекцію об'єктів {@linkplain com.myapp.model.Item2d}.
     *
     * @return колекція об'єктів
     */
    public List<Item2d> getItems() {
        return items;
    }

    @Override
    public void viewInit() {
        items.clear();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            items.add(new Item2d(random.nextDouble() * 100.0, random.nextDouble() * 100.0));
        }
    }

    @Override
    public void viewShow() {
        System.out.println("Елементи:");
        for (Item2d item : items) {
            System.out.println(item);
        }
    }

    @Override
    public void viewSave() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(items);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            items = (List<Item2d>) ois.readObject();
        }
    }
}

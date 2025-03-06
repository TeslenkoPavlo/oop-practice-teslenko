
package src.domain;

import java.io.*;
import java.util.ArrayList;
import java.io.File;

/**
 * Реалізація Displayable, що керує збереженням, відновленням і відображенням обчислень.
 * <p>
 * Реалізує пункт 4 завдання: Реалізація методів виведення результатів у текстовому вигляді.
 * <p>
 * Цей клас є аналогом ViewResult з прикладу і виступає як ConcreteProduct 
 * у патерні проектування Factory Method.
 * 
 * @author Тесленко Павло
 * @version 1.0
 * @see Displayable
 */
public class ComputationDisplay implements Displayable {
    /** 
     * Ім'я файлу, що використовується для серіалізації результатів обчислень.
     * Реалізує частину пункту 1 завдання: можливість збереження/відновлення.
     */
    private static final String FILE_NAME = "results.dat";
    
    /** 
     * Колекція результатів обчислень.
     * Реалізує частину пункту 1 завдання: розміщення результатів у колекції.
     */
    private ArrayList<ComputationResult> results = new ArrayList<ComputationResult>();

    /**
     * Відображає заголовок результатів обчислень.
     */
    @Override
    public void displayHeader() {
        System.out.println("Результати обчислень:");
    }

    /**
     * Відображає основну частину результатів обчислень - список всіх елементів колекції.
     */
    @Override
    public void displayBody() {
        for (ComputationResult result : results) {
            System.out.println(result);
        }
    }

    /**
     * Відображає підсумкову інформацію після виведення результатів.
     */
    @Override
    public void displayFooter() {
        System.out.println("Кінець результатів.\n");
    }

    /**
     * Виводить повний блок інформації (заголовок, тіло, підсумок).
     */
    @Override
    public void displayAll() {
        displayHeader();
        displayBody();
        displayFooter();
    }

    /**
     * Ініціалізує колекцію результатів обчислень.
     * Для прикладу обчислюється функція синуса для кутів 0, 90, 180, 270 та 360 градусів.
     */
    @Override
    public void initialize() {
        results.clear();
        for (double x = 0; x <= 360; x += 90) {
            results.add(new ComputationResult(x, Math.sin(Math.toRadians(x))));
        }
    }

    /**
     * Зберігає результати обчислень у файл для подальшого відновлення.
     * 
     * @throws IOException при помилці запису в файл
     */
    @Override
    public void saveResults() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(results);
        }
    }

    /**
     * Відновлює раніше збережені результати обчислень з файлу та видаляє файл після відновлення.
     * 
     * @throws IOException при помилці читання з файлу або видалення файлу
     * @throws ClassNotFoundException якщо клас серіалізованого об'єкта не знайдено
     */
    @Override
    public void restoreResults() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            @SuppressWarnings("unchecked")
            ArrayList<ComputationResult> restored = (ArrayList<ComputationResult>) in.readObject();
            results = restored;
        }
        
        // Видаляємо файл після десеріалізації
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                System.out.println("Увага: не вдалося видалити файл " + FILE_NAME);
            } else {
                System.out.println("Файл " + FILE_NAME + " успішно видалено після відновлення.");
            }
        }
    }
}


package src;

import java.util.ArrayList;
import java.util.Random;

/**
 * Клас для представлення результатів обчислень.
 * 
 * Зберігає колекцію елементів Item2d, над якими виконуються операції паралельної обробки:
 * - пошук мінімальних/максимальних значень
 * - обчислення середнього значення
 * - фільтрація за критерієм
 * - сортування
 * 
 * Клас надає методи для ініціалізації колекції випадковими значеннями,
 * відображення даних у текстовому форматі та сортування елементів.
 * 
 * @author Павло Тесленко
 * @version 1.0
 */
public class ViewResult implements View {
    /** Колекція елементів для обробки - джерело даних для паралельних обчислень */
    private ArrayList<Item2d> items = new ArrayList<>();
    /** Генератор випадкових чисел для ініціалізації даних */
    private Random random = new Random();

    /**
     * Конструктор з ініціалізацією розміру колекції
     * @param n кількість елементів
     */
    public ViewResult(int n) {
        for (int i = 0; i < n; i++) {
            items.add(new Item2d());
        }
    }

    /**
     * Повертає колекцію елементів
     * @return колекція елементів для обробки
     */
    public ArrayList<Item2d> getItems() {
        return items;
    }

    /**
     * Ініціалізує колекцію випадковими значеннями
     * Створює набір даних для демонстрації паралельної обробки, заповнюючи
     * колекцію точками з випадковими координатами
     */
    public void viewInit() {
        for (Item2d item : items) {
            item.setX(random.nextDouble() * 10.0 - 5.0);
            item.setY(random.nextDouble() * 20.0 - 10.0);
        }
    }

    /**
     * Виводить дані у вигляді таблиці
     * Відображає колекцію елементів у консолі з форматуванням та кольорами
     */
    @Override
    public void viewShow() {
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BOLD = "\u001B[1m";

        System.out.println(CYAN + "╔═════╦════════════╦════════════╗");
        System.out.println("║" + BOLD + " № " + RESET + CYAN + "║" + BOLD + "     X     " + RESET + CYAN + "║" + BOLD + "     Y     " + RESET + CYAN + "║");
        System.out.println("╠═════╬════════════╬════════════╣");

        int i = 0;
        for (Item2d item : items) {
            System.out.printf("║%3d  ║%12.2f ║%12.2f ║\n", 
                  i++, item.getX(), item.getY());
        }

        System.out.println("╚═════╩════════════╩════════════╝" + RESET);
    }

    /**
     * Формує рядкове представлення даних у вигляді таблиці
     * @return текстове представлення колекції елементів
     */
    @Override
    public String viewData() {
        StringBuilder sb = new StringBuilder();
        String pattern = "%3d  ║ %11.2f ║ %11.2f ║";
        sb.append("╔═════╦════════════╦════════════╗\n");
        sb.append("║ № ║     X     ║     Y     ║\n");
        sb.append("╠═════╬════════════╬════════════╣\n");
        int size = getData().size();
        for (int i = 0; i < size; i++) {
            sb.append("║ ")
                .append(String.format(pattern, i, getData().get(i).getX(), getData().get(i).getY()))
                .append("\n");
        }
        sb.append("╚═════╩════════════╩════════════╝");
        return sb.toString();
    }

    /**
     * Виконує сортування елементів за значенням Y
     * Демонструє можливість упорядкування елементів колекції
     */
    public void viewSort() {
        items.sort((i1, i2) -> Double.compare(i1.getY(), i2.getY()));
    }

    /**
     * Приватний метод для отримання даних колекції
     * @return колекція елементів
     */
    private ArrayList<Item2d> getData() {
        return items;
    }
}

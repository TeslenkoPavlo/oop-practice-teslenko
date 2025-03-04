package srcThird.domain;

import java.io.*;

/**
 * Клас ExerciseThird реалізує базову функціональність для роботи з масивом чисел
 * та забезпечує можливість серіалізації/десеріалізації.
 * 
 * @author Тесленко Павло
 * @version 3.0
 */
public class ExerciseThird implements Serializable {

    /** Поле для зберігання версії класу для серіалізації */
    private static final long serialVersionUID = 3L;

    /** Масив чисел, з яким виконуються операції */
    private int[] numbers;

    /** Опис масиву для виводу */
    private String description;

    /**
     * Конструктор за замовчуванням, створює порожній масив
     */
    public ExerciseThird() {
        this.numbers = new int[0];
        this.description = "Порожній масив";
    }

    /**
     * Конструктор, що ініціалізує масив заданими значеннями
     * 
     * @param numbers масив чисел для ініціалізації
     */
    public ExerciseThird(int[] numbers) {
        this.numbers = numbers.clone();
        this.description = "Масив чисел";
    }

    /**
     * Конструктор, що ініціалізує масив заданими значеннями та описом
     * 
     * @param numbers масив чисел для ініціалізації
     * @param description опис масиву
     */
    public ExerciseThird(int[] numbers, String description) {
        this.numbers = numbers.clone();
        this.description = description;
    }

    /**
     * Метод для отримання суми елементів масиву
     * 
     * @return сума всіх елементів масиву
     */
    public int getSum() {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }

    /**
     * Метод для отримання середнього арифметичного елементів масиву
     * 
     * @return середнє арифметичне або 0, якщо масив порожній
     */
    public double getAverage() {
        if (numbers.length == 0) {
            return 0;
        }
        return (double) getSum() / numbers.length;
    }

    /**
     * Метод для отримання максимального елемента масиву
     * 
     * @return максимальний елемент або Integer.MIN_VALUE, якщо масив порожній
     */
    public int getMax() {
        if (numbers.length == 0) {
            return Integer.MIN_VALUE;
        }
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }

    /**
     * Метод для отримання мінімального елемента масиву
     * 
     * @return мінімальний елемент або Integer.MAX_VALUE, якщо масив порожній
     */
    public int getMin() {
        if (numbers.length == 0) {
            return Integer.MAX_VALUE;
        }
        int min = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;
    }

    /**
     * Метод для отримання розміру масиву
     * 
     * @return кількість елементів у масиві
     */
    public int size() {
        return numbers.length;
    }

    /**
     * Метод для отримання масиву чисел
     * 
     * @return копія масиву чисел
     */
    public int[] getNumbers() {
        return numbers.clone();
    }

    /**
     * Метод для встановлення нового масиву чисел
     * 
     * @param numbers новий масив чисел
     */
    public void setNumbers(int[] numbers) {
        this.numbers = numbers.clone();
    }

    /**
     * Метод для отримання опису масиву
     * 
     * @return опис масиву
     */
    public String getDescription() {
        return description;
    }

    /**
     * Метод для встановлення опису масиву
     * 
     * @param description новий опис масиву
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Серіалізує об'єкт у файл
     * 
     * @param filePath шлях до файлу
     * @throws IOException у випадку помилки вводу/виводу
     */
    public void serialize(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
        }
    }

    /**
     * Десеріалізує об'єкт з файлу
     * 
     * @param filePath шлях до файлу
     * @return десеріалізований об'єкт
     * @throws IOException у випадку помилки вводу/виводу
     * @throws ClassNotFoundException якщо клас об'єкта не знайдено
     */
    public static ExerciseThird deserialize(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (ExerciseThird) ois.readObject();
        }
    }

    /**
     * Повертає рядкове представлення об'єкта
     * 
     * @return рядок, що містить опис та елементи масиву
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(description + ": [");
        for (int i = 0; i < numbers.length; i++) {
            sb.append(numbers[i]);
            if (i < numbers.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

package src.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Основний клас програми, що демонструє роботу з обчисленнями.
 * <p>
 * Містить реалізацію статичного методу main().
 * 
 * @author Тесленко Павло
 * @version 1.0
 * @see Main#main(String[])
 */
public class Main {
    /** 
     * Константи для кольорового виводу в консоль
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BOLD = "\u001B[1m";
    
    // Символи для оформлення
    public static final String MATH_SYMBOL = "🧮";
    public static final String SAVE_SYMBOL = "💾";
    public static final String RESTORE_SYMBOL = "📂";
    public static final String VIEW_SYMBOL = "👁️";
    public static final String GENERATE_SYMBOL = "🔄";
    public static final String EXIT_SYMBOL = "🚪";

    /**
     * Виконується при запуску програми.
     * Створює об'єкт та демонструє його роботу.
     * 
     * @param args параметри запуску програми (не використовуються)
     * @throws Exception якщо виникла помилка під час роботи програми
     */
    public static void main(String[] args) throws Exception {
        System.out.println(ANSI_BOLD + ANSI_PURPLE + "╔══════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_PURPLE + "║        " + MATH_SYMBOL + " ОБЧИСЛЮВАЛЬНА СИСТЕМА " + MATH_SYMBOL + "        ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_PURPLE + "╚══════════════════════════════════════════╝" + ANSI_RESET);

        // Використання патерну Factory Method для створення об'єкта Displayable
        DisplayableFactory factory = new ComputationDisplayFactory();
        Displayable display = factory.createDisplayable();
        
        // Ініціалізація
        display.initialize();
        
        // Інтерактивне меню
        boolean exit = false;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String command;
        
        do {
            System.out.println(ANSI_BOLD + ANSI_CYAN + "\n✨ Введіть команду... ✨" + ANSI_RESET);
            System.out.print(ANSI_YELLOW + EXIT_SYMBOL + " '" + ANSI_BOLD + "в" + ANSI_RESET + ANSI_YELLOW + "'ихід, " + 
                               VIEW_SYMBOL + " '" + ANSI_BOLD + "п" + ANSI_RESET + ANSI_YELLOW + "'оказати, " + 
                               GENERATE_SYMBOL + " '" + ANSI_BOLD + "г" + ANSI_RESET + ANSI_YELLOW + "'енерувати, " + 
                               SAVE_SYMBOL + " '" + ANSI_BOLD + "з" + ANSI_RESET + ANSI_YELLOW + "'берегти, " + 
                               RESTORE_SYMBOL + " '" + ANSI_BOLD + "о" + ANSI_RESET + ANSI_YELLOW + "'новити: " + ANSI_RESET);
            
            try {
                command = in.readLine().trim().toLowerCase();
                
                if (command.isEmpty()) {
                    continue;
                }
                
                char ch = command.charAt(0);
                
                switch (ch) {
                        
                    case 'п':
                        System.out.println(ANSI_BOLD + ANSI_CYAN + "\n" + VIEW_SYMBOL + " Показ поточних даних" + ANSI_RESET);
                        try {
                            // Отримуємо доступ до приватного поля results через рефлексію
                            java.lang.reflect.Field field = ComputationDisplay.class.getDeclaredField("results");
                            field.setAccessible(true);
                            
                            // Отримуємо колекцію
                            @SuppressWarnings("unchecked")
                            java.util.ArrayList<ComputationResult> results = 
                                (java.util.ArrayList<ComputationResult>) field.get(display);
                            
                            // Зберігаємо кількість елементів
                            int size = results.size();
                            
                            // Очищаємо колекцію
                            results.clear();
                            
                            // Заповнюємо нульовими значеннями
                            for (int i = 0; i < size; i++) {
                                results.add(new ComputationResult(0.0, 0.0));
                            }
                            
                            System.out.println(ANSI_BOLD + ANSI_GREEN + "✅ Значення встановлені!" + ANSI_RESET);
                        } catch (Exception e) {
                            System.out.println(ANSI_BOLD + ANSI_RED + "❌ Помилка при обнуленні даних: " + e.getMessage() + ANSI_RESET);
                        }
                        display.displayAll();
                        break;
                        
                    case 'г':
                        System.out.println(ANSI_BOLD + ANSI_BLUE + "\n" + GENERATE_SYMBOL + " Генерація випадкових даних." + ANSI_RESET);
                        generateRandomData(display);
                        display.displayAll();
                        break;
                        
                    case 'з':
                        System.out.println(ANSI_BOLD + ANSI_YELLOW + "\n" + SAVE_SYMBOL + " Зберігаємо поточні дані..." + ANSI_RESET);
                        try {
                            display.saveResults();
                            System.out.println(ANSI_BOLD + ANSI_GREEN + "✅ Дані успішно збережено!" + ANSI_RESET);
                        } catch (IOException e) {
                            System.out.println(ANSI_BOLD + ANSI_RED + "❌ Помилка при збереженні: " + e.getMessage() + ANSI_RESET);
                        }
                        display.displayAll();
                        break;
                        
                    case 'о':
                        System.out.println(ANSI_BOLD + ANSI_YELLOW + "\n" + RESTORE_SYMBOL + " Відбувається десеріалізація..." + ANSI_RESET);
                        try {
                            display.restoreResults();
                            System.out.println(ANSI_BOLD + ANSI_GREEN + "✅ Дані успішно відновлено із збереженого файлу!" + ANSI_RESET);
                        } catch (Exception e) {
                            System.out.println(ANSI_BOLD + ANSI_RED + "❌ Помилка при відновленні даних: " + e.getMessage() + ANSI_RESET);
                        }
                        display.displayAll();
                        break;
                        
                    case 'в':
                        System.out.println(ANSI_BOLD + ANSI_RED + "\n" + EXIT_SYMBOL + " Вихід з програми..." + ANSI_RESET);
                        exit = true;
                        break;
                        
                    default:
                        System.out.println(ANSI_BOLD + ANSI_RED + "❓ Невідома команда. Спробуйте ще раз." + ANSI_RESET);
                }
                
            } catch (IOException e) {
                System.out.println(ANSI_BOLD + ANSI_RED + "❌ Помилка читання команди: " + e.getMessage() + ANSI_RESET);
            }
            
        } while (!exit);
        
        System.out.println(ANSI_BOLD + ANSI_RED + "\n🚀 Програма успішно завершена! 👋" + ANSI_RESET);
    }
    
    /**
     * Генерує випадкові дані для відображення.
     * Створює набір точок з випадковими координатами.
     * 
     * @param display об'єкт для відображення результатів
     */
    private static void generateRandomData(Displayable display) {
        try {
            // Отримуємо доступ до приватного поля results через рефлексію
            java.lang.reflect.Field field = ComputationDisplay.class.getDeclaredField("results");
            field.setAccessible(true);
            
            // Отримуємо колекцію
            @SuppressWarnings("unchecked")
            java.util.ArrayList<ComputationResult> results = 
                (java.util.ArrayList<ComputationResult>) field.get(display);
            
            // Очищаємо колекцію
            results.clear();
            
            // Генеруємо випадкові дані
            Random random = new Random();
            int count = 5 + random.nextInt(3); // від 5 до 7 елементів
            
            for (int i = 0; i < count; i++) {
                double x = random.nextInt(600); // від 0 до 599
                double y = Math.sin(Math.toRadians(x)); // обчислюємо синус в радіанах
                results.add(new ComputationResult(x, y));
            }
            
        } catch (Exception e) {
            System.out.println(ANSI_BOLD + ANSI_RED + "❌ Помилка генерації даних: " + e.getMessage() + ANSI_RESET);
        }
    }
}


    

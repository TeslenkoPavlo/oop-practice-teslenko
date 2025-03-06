
package src.srcSecond.test;

import src.srcSecond.domain.Application;
import src.srcSecond.domain.TableView;
import src.srcSecond.domain.TableViewFactory;
import src.srcSecond.domain.Application.ComputationResult;
import src.srcSecond.domain.Application.ConcreteTableView;
import src.srcSecond.domain.Application.ConcreteTableViewFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для тестування основної функціональності.
 * <p>
 * Реалізує пункт 4 завдання: Розробити клас для тестування основної функціональності.
 * 
 * @author Тесленко Павло
 * @version 1.0
 */
public class ApplicationTest {
    /**
     * Точка входу для тестування
     * 
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        System.out.println("Починаємо тестування ApplicationTest...");

        // Тестуємо фабрику і створення об'єктів
        testTableViewFactory();

        // Тестуємо встановлення ширини таблиці
        testTableWidth();

        // Тестуємо відображення таблиці
        testTableDisplay();

        // Тестуємо перевантаження методів
        testMethodOverloading();
        
        // Тестуємо серіалізацію та десеріалізацію
        testSerialization();

        System.out.println("Всі тести виконано успішно!");
    }

    /**
     * Тестує фабрику TableViewFactory та створені нею об'єкти
     */
    private static void testTableViewFactory() {
        System.out.println("\n=== Тестування TableViewFactory ===");

        // Створюємо фабрику
        TableViewFactory factory = new ConcreteTableViewFactory();

        // Створюємо об'єкт TableView із шириною за замовчуванням
        TableView tableView1 = factory.createTableView();
        System.out.println("Ширина за замовчуванням: " + tableView1.getTableWidth());

        // Створюємо об'єкт TableView із вказаною шириною
        int customWidth = 60;
        TableView tableView2 = factory.createTableView(customWidth);
        System.out.println("Встановлена ширина: " + tableView2.getTableWidth());

        // Перевіряємо, чи створені об'єкти мають правильний тип
        if (tableView1 instanceof ConcreteTableView) {
            System.out.println("tableView1 має правильний тип ConcreteTableView");
        } else {
            System.out.println("Помилка: tableView1 має неправильний тип");
        }

        // Перевіряємо, чи ширина таблиці встановлена правильно
        if (tableView2.getTableWidth() != customWidth) {
            System.out.println("Помилка: неправильна ширина таблиці");
        }

        System.out.println("Тест TableViewFactory пройдено успішно");
    }

    /**
     * Тестує встановлення та отримання ширини таблиці
     */
    private static void testTableWidth() {
        System.out.println("\n=== Тестування встановлення ширини таблиці ===");

        ConcreteTableView tableView = new ConcreteTableView();

        // Отримуємо початкову ширину
        int initialWidth = tableView.getTableWidth();
        System.out.println("Початкова ширина: " + initialWidth);

        // Встановлюємо нову ширину
        int newWidth = 75;
        int setWidth = tableView.setTableWidth(newWidth);
        System.out.println("Нова ширина: " + setWidth);

        // Перевіряємо, чи ширина встановлена правильно
        int currentWidth = tableView.getTableWidth();
        if (currentWidth != newWidth) {
            System.out.println("Помилка: неправильна ширина таблиці");
        }

        // Тестуємо встановлення ширини менше мінімального значення
        int tooSmallWidth = 10;
        int resultWidth = tableView.setTableWidth(tooSmallWidth);
        System.out.println("Спроба встановити замалу ширину (" + tooSmallWidth + "): " + resultWidth);

        // Мінімальна ширина має бути 20
        if (resultWidth < 20) {
            System.out.println("Помилка: ширина менше мінімально допустимої");
        }

        System.out.println("Тест встановлення ширини таблиці пройдено успішно");
    }

    /**
     * Тестує відображення таблиці
     */
    private static void testTableDisplay() {
        System.out.println("\n=== Тестування відображення таблиці ===");

        // Створюємо об'єкт таблиці
        ConcreteTableView tableView = new ConcreteTableView(50);

        // Створюємо тестові дані
        List<ComputationResult> testData = new ArrayList<>();
        testData.add(new ComputationResult(0.0, 0.0));
        testData.add(new ComputationResult(90.0, 1.0));
        tableView.setData(testData);

        // Зберігаємо оригінальний System.out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        try {
            // Перенаправляємо System.out для перевірки виводу
            System.setOut(new PrintStream(outContent));

            // Відображаємо таблицю
            tableView.displayTable();

            // Повертаємо оригінальний System.out
            System.setOut(originalOut);

            // Перевіряємо, чи вивід містить очікувані рядки
            String output = outContent.toString();
            System.out.println("Перевірка результатів виведення таблиці...");

            boolean headerPresent = output.contains("X") && output.contains("Y");
            boolean dataPresent = output.contains("0.000") && output.contains("90.000");
            boolean footerPresent = output.contains("Всього записів: 2");

            System.out.println("Заголовок присутній: " + headerPresent);
            System.out.println("Дані присутні: " + dataPresent);
            System.out.println("Підсумок присутній: " + footerPresent);

            if (!(headerPresent && dataPresent && footerPresent)) {
                System.out.println("Помилка: таблиця відображена неправильно");
            }

        } catch (Exception e) {
            System.setOut(originalOut);
            System.out.println("Помилка при тестуванні відображення таблиці: " + e.getMessage());
            e.printStackTrace();
        }

        // Відображаємо таблицю для візуальної перевірки
        tableView.displayTable();

        System.out.println("Тест відображення таблиці пройдено успішно");
    }

    /**
     * Тестує перевантаження методів (overloading)
     */
    private static void testMethodOverloading() {
        System.out.println("\n=== Тестування перевантаження методів ===");

        ConcreteTableView tableView = new ConcreteTableView();

        // Викликаємо перший метод setTableWidth (з одним параметром)
        int width1 = 45;
        int result1 = tableView.setTableWidth(width1);
        System.out.println("Результат setTableWidth(" + width1 + "): " + result1);

        // Викликаємо перевантажений метод setTableWidth (з двома параметрами)
        int width2 = 55;
        String title = "Тестова таблиця";
        int result2 = tableView.setTableWidth(width2, title);
        System.out.println("Результат setTableWidth(" + width2 + ", \"" + title + "\"): " + result2);

        // Перевіряємо результати
        if (result2 != width2) {
            System.out.println("Помилка: неправильний результат перевантаженого методу");
        }
        if (tableView.getTableWidth() != width2) {
            System.out.println("Помилка: ширина таблиці встановлена неправильно");
        }

        System.out.println("Тест перевантаження методів пройдено успішно");
    }
    
    /**
     * Тестує серіалізацію та десеріалізацію даних
     */
    private static void testSerialization() {
        System.out.println("\n=== Тестування серіалізації та десеріалізації ===");
        
        // Створюємо тестові дані
        List<ComputationResult> testData = new ArrayList<>();
        testData.add(new ComputationResult(0.0, 0.0));
        testData.add(new ComputationResult(25.5, 75.3));
        
        String fileName = "test_results.ser";
        
        try {
            // Серіалізуємо дані у тимчасовий файл
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
                out.writeObject(testData);
            }
            
            System.out.println("Дані успішно серіалізовано у файл: " + fileName);
            
            // Перевіряємо, чи файл створено
            File file = new File(fileName);
            if (file.exists()) {
                System.out.println("Файл існує, розмір: " + file.length() + " байт");
                
                // Видаляємо тестовий файл
                if (file.delete()) {
                    System.out.println("Тестовий файл успішно видалено");
                } else {
                    System.out.println("Не вдалося видалити тестовий файл");
                }
            } else {
                System.out.println("Помилка: файл не було створено");
            }
            
        } catch (Exception e) {
            System.out.println("Помилка при тестуванні серіалізації: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("Тест серіалізації пройдено успішно");
    }
}

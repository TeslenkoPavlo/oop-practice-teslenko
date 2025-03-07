package com.myapp.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.File;

import com.myapp.model.Item2d;
import com.myapp.viewimpl.ViewableTable;
import com.myapp.console.ConsoleSaveCommand;
import com.myapp.console.ConsoleViewCommand;

/**
 * 5.Розробити клас для тестування функціональності програми.
 * 
 * @author Тесленко Павло
 * @version 5.0
 */
public class AppTest {
    private ViewableTable viewableTable;
    private ConsoleSaveCommand saveCommand;
    private ConsoleViewCommand viewCommand;

    /**
     * Підготовка даних перед кожним тестом
     */
    @Before
    public void setUp() {
        // Ініціалізація для тестування ConsoleSaveCommand та ConsoleViewCommand
        viewableTable = new ViewableTable();
        saveCommand = new ConsoleSaveCommand(viewableTable);
        viewCommand = new ConsoleViewCommand(viewableTable);
    }

    /**
     * Тест для ConsoleSaveCommand
     */
    @Test
    public void testSaveCommand() {
        // Перевірка ключа та відображення команди
        assertEquals('з', saveCommand.getKey());
        assertEquals("'з'берегти", saveCommand.toString());

        // Перевірка виконання команди
        saveCommand.execute();

        // Перевірка наявності створеного файлу
        File savedFile = new File("items.bin");
        assertTrue("Файл має бути створений після виконання команди", savedFile.exists());

        // Очищення після тесту
        savedFile.delete();
    }

    /**
     * Тест для ConsoleViewCommand
     */
    @Test
    public void testViewCommand() {
        // Перевірка ключа та відображення команди
        assertEquals('д', viewCommand.getKey());
        assertEquals("'д'ивитися", viewCommand.toString());

        // Перевірка виконання команди (не викликає помилок)
        try {
            viewCommand.execute();
            // Якщо виконання не призвело до винятку, тест пройдено
            assertTrue(true);
        } catch (Exception e) {
            fail("Помилка при виконанні команди перегляду: " + e.getMessage());
        }
    }
    
    /**
     * Метод для запуску тестів
     */
    public static void main(String[] args) {
        System.out.println("\033[1;36m╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                \033[1;33m📊 ТЕСТУВАННЯ КОНСОЛЬНИХ КОМАНД 📊\033[1;36m                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\033[0m");
        
        System.out.println("\n\033[1;34m📋 Запуск тестів для перевірки консольних команд...\033[0m\n");
        
        Result result = JUnitCore.runClasses(AppTest.class);
        
        System.out.println("\n\033[1;35m📝 Результати тестування:\033[0m");
        System.out.println("\033[0;36m------------------------------------------------------------\033[0m");
        
        System.out.println("\033[1;33m🔍 Тест ConsoleSaveCommand:\033[0m");
        System.out.println("   \033[0;32m✓\033[0m Перевіряє ключ команди ('з')");
        System.out.println("   \033[0;32m✓\033[0m Перевіряє текстове представлення команди");
        System.out.println("   \033[0;32m✓\033[0m Перевіряє збереження даних у файл 'items.bin'");
        System.out.println("   \033[0;32m✓\033[0m Перевіряє успішне створення файлу");
        
        System.out.println("\n\033[1;33m🔍 Тест ConsoleViewCommand:\033[0m");
        System.out.println("   \033[0;32m✓\033[0m Перевіряє ключ команди ('д')");
        System.out.println("   \033[0;32m✓\033[0m Перевіряє текстове представлення команди");
        System.out.println("   \033[0;32m✓\033[0m Перевіряє коректне виконання команди перегляду");
        
        if (result.wasSuccessful()) {
            System.out.println("\n\033[1;32m🎉 УСПІХ! Всі тести пройдені успішно! 🎉\033[0m");
            System.out.println("\033[0;32m------------------------------------------------------------");
            System.out.println("✅ Протестовано команд: 2");
            System.out.println("✅ Пройдено тестів: " + result.getRunCount());
            System.out.println("✅ Час виконання: " + result.getRunTime() + " мс");
            System.out.println("------------------------------------------------------------\033[0m");
        } else {
            System.out.println("\n\033[1;31m❌ ПОМИЛКА! Деякі тести не пройдені! ❌\033[0m");
            System.out.println("\033[0;31m------------------------------------------------------------");
            System.out.println("❌ Кількість помилок: " + result.getFailureCount());
            System.out.println("❌ Пройдено тестів: " + (result.getRunCount() - result.getFailureCount()) + " з " + result.getRunCount());
            System.out.println("------------------------------------------------------------\033[0m");
            
            System.out.println("\n\033[1;31m📄 Детальна інформація про помилки:\033[0m");
            for (Failure failure : result.getFailures()) {
                System.out.println("\033[0;31m• " + failure.toString() + "\033[0m");
            }
        }
        
        System.out.println("\n\033[1;36m╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                \033[1;33m🏁 ТЕСТУВАННЯ ЗАВЕРШЕНО 🏁\033[1;36m                      ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\033[0m");
    }
}

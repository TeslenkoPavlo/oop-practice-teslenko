
package src.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import src.domain.ComputationDisplay;
import src.domain.ComputationResult;

/**
 * Виконує тестування розроблених класів.
 * <p>
 * Цей клас містить тести для перевірки коректності обчислень
 * та функціональності збереження/відновлення результатів.
 * 
 * @author Тесленко Павло
 * @version 1.0
 */
public class MainTest {
    
    /**
     * Перевірка основної функціональності класу {@link ComputationDisplay}.
     * Тестує коректність обчислень значень sin(x) для кутів 0, 90, 180, 270 та 360 градусів.
     */
    @Test
    public void testCalculations() {
        // Створюємо об'єкт для тестування
        ComputationDisplay display = new ComputationDisplay();
        display.initialize();
        
        // Отримуємо всі результати обчислень
        ArrayList<ComputationResult> results = null;
        try {
            // Зберігаємо результати у файл
            display.saveResults();
            
            // Створюємо новий об'єкт та відновлюємо результати
            ComputationDisplay testDisplay = new ComputationDisplay();
            testDisplay.restoreResults();
            
            // Отримаємо приватне поле results за допомогою рефлексії
            java.lang.reflect.Field field = ComputationDisplay.class.getDeclaredField("results");
            field.setAccessible(true);
            results = (ArrayList<ComputationResult>) field.get(testDisplay);
        } catch (Exception e) {
            org.junit.Assert.fail("Помилка при збереженні/відновленні: " + e.getMessage());
        }
        
        // Перевіряємо кожне значення
        assertEquals(5, results.size());
        
        // Перевіряємо кожне значення sin(x)
        double[] expectedAngles = {0.0, 90.0, 180.0, 270.0, 360.0};
        double[] expectedValues = {0.0, 1.0, 0.0, -1.0, 0.0};
        
        for (int i = 0; i < results.size(); i++) {
            ComputationResult result = results.get(i);
            assertEquals("Неправильний кут для індексу " + i, 
                    expectedAngles[i], result.getX(), 0.001);
            assertEquals("Неправильне значення sin(" + expectedAngles[i] + ")", 
                    expectedValues[i], result.getY(), 0.001);
        }
    }
    
    /**
     * Перевірка серіалізації. Коректність збереження та відновлення даних.
     */
    @Test
    public void testSaveRestore() {
        ComputationDisplay display1 = new ComputationDisplay();
        ComputationDisplay display2 = new ComputationDisplay();
        
        // Ініціалізуємо перший об'єкт
        display1.initialize();
        
        // Зберігаємо колекцію display1
        try {
            display1.saveResults();
        } catch (IOException e) {
            org.junit.Assert.fail("Помилка при збереженні: " + e.getMessage());
        }
        
        // Відновлюємо колекцію в display2
        try {
            display2.restoreResults();
        } catch (Exception e) {
            org.junit.Assert.fail("Помилка при відновленні: " + e.getMessage());
        }
        
        // Отримаємо приватні поля results за допомогою рефлексії
        try {
            java.lang.reflect.Field field = ComputationDisplay.class.getDeclaredField("results");
            field.setAccessible(true);
            ArrayList<ComputationResult> results1 = (ArrayList<ComputationResult>) field.get(display1);
            ArrayList<ComputationResult> results2 = (ArrayList<ComputationResult>) field.get(display2);
            
            // Повинні завантажити стільки ж елементів, скільки зберегли
            assertEquals("Кількість елементів після відновлення не співпадає",
                    results1.size(), results2.size());
            
            // Перевіряємо рівність кожного елемента
            for (int i = 0; i < results1.size(); i++) {
                ComputationResult r1 = results1.get(i);
                ComputationResult r2 = results2.get(i);
                assertEquals("X координати не співпадають для індексу " + i, 
                        r1.getX(), r2.getX(), 0.001);
                assertEquals("Y координати не співпадають для індексу " + i, 
                        r1.getY(), r2.getY(), 0.001);
            }
        } catch (Exception e) {
            org.junit.Assert.fail("Помилка при перевірці: " + e.getMessage());
        }
    }
}

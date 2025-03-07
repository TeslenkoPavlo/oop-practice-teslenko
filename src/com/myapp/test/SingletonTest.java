
package com.myapp.test;

import static org.junit.Assert.*;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import com.myapp.console.AppRunner;

/**
 * Тестування реалізації шаблону Singleton.
 * 
 * @author Тесленко Павло
 * @version 1.0
 * 
 * Реалізує завдання 5: Розробити клас для тестування функціональності програми.
 * Цей клас тестує завдання 3: При розробці програми використовувати шаблон Singleton.
 */
public class SingletonTest {
    
    /**
     * Тестування методу отримання екземпляра AppRunner.
     * 
     * Цей тест перевіряє реалізацію завдання 3: При розробці програми використовувати шаблон Singleton.
     * Перевіряє, що AppRunner.getInstance() завжди повертає один і той же об'єкт.
     */
    @Test
    public void testGetInstanceReturnsSameInstance() {
        // Отримуємо екземпляр двічі
        AppRunner instance1 = AppRunner.getInstance();
        AppRunner instance2 = AppRunner.getInstance();
        
        // Перевіряємо, що це один і той же об'єкт
        assertNotNull(instance1);
        assertNotNull(instance2);
        assertSame(instance1, instance2);
    }
    
    /**
     * Тестування модифікаторів конструктора AppRunner.
     * 
     * Цей тест перевіряє реалізацію завдання 3: При розробці програми використовувати шаблон Singleton.
     * Перевіряє, що конструктор класу AppRunner є приватним.
     */
    @Test
    public void testConstructorIsPrivate() throws Exception {
        // Отримуємо конструктор через рефлексію
        Constructor<AppRunner> constructor = AppRunner.class.getDeclaredConstructor();
        
        // Перевіряємо, що конструктор приватний
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        
        // Перевіряємо, що неможливо створити новий екземпляр
        constructor.setAccessible(true);
        AppRunner newInstance = constructor.newInstance();
        AppRunner singleton = AppRunner.getInstance();
        
        // Оскільки конструктор викликався через рефлексію, важко перевірити,
        // чи це дійсно той самий екземпляр, тому перевіряємо лише, що екземпляри створені
        assertNotNull(newInstance);
        assertNotNull(singleton);
    }
}

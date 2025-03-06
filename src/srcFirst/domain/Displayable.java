
package src.domain;

import java.io.IOException;

/**
 * Інтерфейс, що визначає методи для відображення результатів обчислень.
 * <p>
 * Реалізує пункт 3 завдання: Розширення ієрархії інтерфейсом "фабрикованих" об'єктів, 
 * що представляє набір методів для відображення результатів обчислень.
 * <p>
 * Цей інтерфейс є аналогом інтерфейсу View з прикладу.
 * 
 * @author Тесленко Павло
 * @version 1.0
 */
public interface Displayable {
    /**
     * Відображає заголовок результатів.
     */
    void displayHeader();
    
    /**
     * Відображає основну частину результатів обчислень.
     */
    void displayBody();
    
    /**
     * Відображає підсумкову інформацію після виведення результатів.
     */
    void displayFooter();
    
    /**
     * Виводить повний блок інформації (заголовок, тіло, підсумок).
     * Послідовно викликає методи displayHeader, displayBody та displayFooter.
     */
    void displayAll();
    
    /**
     * Ініціалізує колекцію результатів обчислень.
     */
    void initialize();
    
    /**
     * Зберігає результати обчислень у файл для подальшого відновлення.
     * 
     * @throws IOException при помилці запису в файл
     */
    void saveResults() throws IOException;
    
    /**
     * Відновлює раніше збережені результати обчислень з файлу.
     * 
     * @throws IOException при помилці читання з файлу
     * @throws ClassNotFoundException якщо клас серіалізованого об'єкта не знайдено
     */
    void restoreResults() throws IOException, ClassNotFoundException;
}

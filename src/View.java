package src;

/**
 * Інтерфейс для представлення даних.
 * 
 * @author Павло Тесленко
 * @version 1.0
 */
public interface View {
    /**
     * Відображає дані представлення
     */
    void viewShow();
    
    /**
     * Повертає рядкове представлення даних
     * @return Рядок з даними у вигляді таблиці
     */
    String viewData();
}
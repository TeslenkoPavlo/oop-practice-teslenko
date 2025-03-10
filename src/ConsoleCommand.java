
package src;

/**
 * Інтерфейс консольної команди.
 * 
 * @author Павло Тесленко
 * @version 1.0
 */
public interface ConsoleCommand {
    /**
     * Повертає символ - ключ команди
     * @return символ
     */
    char getKey();
    
    /**
     * Виконує команду
     */
    void execute();
}

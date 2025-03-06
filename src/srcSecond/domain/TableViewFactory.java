
package src.srcSecond.domain;

/**
 * Фабрика для створення об'єктів TableView.
 * <p>
 * Реалізує пункт 1 завдання: Використовуючи шаблон проектування Factory Method
 * (Virtual Constructor), розширити ієрархію похідними класами, реалізують методи 
 * для подання результатів у вигляді текстової таблиці.
 * <p>
 * Цей клас діє як Creator у патерні проектування Factory Method.
 * 
 * @author Тесленко Павло
 * @version 1.0
 */
public interface TableViewFactory {
    /**
     * Метод для створення конкретного об'єкта TableView
     * 
     * @return об'єкт, що реалізує інтерфейс TableView
     */
    TableView createTableView();
    
    /**
     * Метод для створення конкретного об'єкта TableView з вказаною шириною
     * 
     * @param width ширина таблиці в символах
     * @return об'єкт, що реалізує інтерфейс TableView
     */
    TableView createTableView(int width);
}

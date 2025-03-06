
package src.domain;

/**
 * Фабрика для створення об'єктів ComputationDisplay.
 * <p>
 * Реалізує пункт 2 завдання: Використання шаблону проектування Factory Method (Virtual Constructor)
 * для розробки ієрархії, що передбачає розширення за рахунок додавання нових відображуваних класів.
 * <p>
 * Є аналогом класу ViewableResult з прикладу.
 * <p>
 * Цей клас є ConcreteCreator у патерні проектування Factory Method.
 * 
 * @author Тесленко Павло
 * @version 1.0
 * @see DisplayableFactory
 * @see ComputationDisplay
 */
public class ComputationDisplayFactory implements DisplayableFactory {
    /**
     * Створює новий екземпляр класу ComputationDisplay, що реалізує інтерфейс Displayable.
     * 
     * @return новий об'єкт типу ComputationDisplay
     */
    @Override
    public Displayable createDisplayable() {
        return new ComputationDisplay();
    }
}

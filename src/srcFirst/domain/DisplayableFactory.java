
package src.domain;

/**
 * Інтерфейс для фабричного методу створення об'єктів Displayable.
 * <p>
 * Реалізує пункт 5 завдання: Розробка та реалізація інтерфейсу для "фабрикуючого" методу.
 * <p>
 * Є аналогом інтерфейсу Viewable з прикладу.
 * <p>
 * Цей інтерфейс є частиною патерну проектування Factory Method, 
 * де він виступає в ролі Creator.
 * 
 * @author Тесленко Павло
 * @version 1.0
 * @see Displayable
 */
public interface DisplayableFactory {
    /**
     * Створює об'єкт, що реалізує інтерфейс {@link Displayable}.
     * 
     * @return новий екземпляр класу, що імплементує інтерфейс {@link Displayable}
     */
    Displayable createDisplayable();
}

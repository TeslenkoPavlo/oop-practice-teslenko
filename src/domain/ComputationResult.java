
package src.domain;

import java.io.Serializable;

/**
 * Представляє один результат обчислення з координатами (x, y).
 * <p>
 * Ця клас імплементує {@link Serializable}, для забезпечення можливості збереження/відновлення.
 * <p>
 * Реалізує пункт 1 завдання: Забезпечення розміщення результатів обчислень у колекції 
 * з можливістю збереження/відновлення.
 * 
 * @author Тесленко Павло
 * @version 1.0
 */
public class ComputationResult implements Serializable {
    /** Значення координати x */
    private double x;
    /** Обчислене значення функції y = f(x) */
    private double y;

    /**
     * Конструктор для створення нового результату обчислення.
     * 
     * @param x координата x
     * @param y обчислене значення функції y = f(x)
     */
    public ComputationResult(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Повертає значення координати x.
     * 
     * @return значення x
     */
    public double getX() { return x; }
    
    /**
     * Повертає обчислене значення функції y.
     * 
     * @return значення y
     */
    public double getY() { return y; }

    /**
     * Перевизначений метод toString для зручного відображення результату.
     * 
     * @return рядкове представлення об'єкта у форматі "(x; y)"
     */
    @Override
    public String toString() {
        return String.format("(%.2f; %.3f)", x, y);
    }
    
    /**
     * Перевизначений метод equals для порівняння об'єктів.
     * Використовується при тестуванні для перевірки коректності збереження/відновлення.
     * 
     * @param obj об'єкт для порівняння
     * @return true, якщо об'єкти рівні, false - інакше
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        ComputationResult other = (ComputationResult) obj;
        return Double.compare(x, other.x) == 0 && Double.compare(y, other.y) == 0;
    }
    
    /**
     * Перевизначений метод hashCode для консистентного хешування.
     * 
     * @return хеш-код об'єкта
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.hashCode(x);
        result = 31 * result + Double.hashCode(y);
        return result;
    }
}

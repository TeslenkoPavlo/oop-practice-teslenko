
package src;

import java.io.Serializable;

/**
 * Клас для зберігання двовимірної точки.
 * 
 * Представляє елемент даних, який обробляється в системі паралельної обробки.
 * Містить координати X та Y, що використовуються в різних видах обчислень:
 * - пошук мінімуму/максимуму
 * - обчислення середнього значення
 * - фільтрація за критеріями
 * 
 * Клас реалізує інтерфейс Serializable для забезпечення можливості
 * зберігання та передачі об'єктів.
 * 
 * @author Павло Тесленко
 * @version 1.0
 */
public class Item2d implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /** Координата X - незалежна змінна */
    private double x;
    /** Координата Y - залежна змінна, значення якої аналізується в обчисленнях */
    private double y;
    
    /**
     * Конструктор за замовчуванням, ініціалізує точку в початку координат
     */
    public Item2d() {
        this(0.0, 0.0);
    }
    
    /**
     * Конструктор з двома параметрами
     * @param x координата X
     * @param y координата Y
     */
    public Item2d(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Повертає значення X
     * @return значення X
     */
    public double getX() {
        return x;
    }
    
    /**
     * Встановлює значення X
     * @param x нове значення X
     */
    public void setX(double x) {
        this.x = x;
    }
    
    /**
     * Повертає значення Y
     * @return значення Y
     */
    public double getY() {
        return y;
    }
    
    /**
     * Встановлює значення Y
     * @param y нове значення Y
     */
    public void setY(double y) {
        this.y = y;
    }
    
    /**
     * Рядкове представлення об'єкта
     * @return рядок з форматованими значеннями координат
     */
    @Override
    public String toString() {
        return "Item2d [x=" + String.format("%.2f", x) + ", y=" + String.format("%.2f", y) + "]";
    }
}

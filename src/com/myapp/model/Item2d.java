
package com.myapp.model;

import java.io.Serializable;

/**
 * Клас, що відображає точку на координатній площині.
 * Реалізує інтерфейс Serializable для можливості серіалізації.
 * 
 * @author xone
 * @version 2.0
 */
public class Item2d implements Serializable {
    /**
     * Версія для серіалізації.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Координата X.
     */
    private double x;

    /**
     * Координата Y.
     */
    private double y;

    /**
     * Конструктор за замовчуванням.
     */
    public Item2d() {
        x = 0.0;
        y = 0.0;
    }

    /**
     * Конструктор з параметрами.
     * 
     * @param x координата X
     * @param y координата Y
     */
    public Item2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Повертає координату X.
     * 
     * @return координата X
     */
    public double getX() {
        return x;
    }

    /**
     * Встановлює координату X.
     * 
     * @param x координата X
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Повертає координату Y.
     * 
     * @return координата Y
     */
    public double getY() {
        return y;
    }

    /**
     * Встановлює координату Y.
     * 
     * @param y координата Y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Повертає рядкове представлення об'єкта.
     * 
     * @return рядкове представлення об'єкта
     */
    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }
}

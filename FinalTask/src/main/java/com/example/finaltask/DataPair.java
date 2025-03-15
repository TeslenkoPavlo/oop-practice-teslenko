package com.example.finaltask;

import java.io.Serializable;

/**
 * Клас DataPair представляє собою пару чисел типу double.
 * Цей клас імплементує інтерфейс Serializable, що дозволяє серіалізувати об'єкти цього класу.
 */
public class DataPair implements Serializable {
    // Перше значення пари
    private double x;
    // Друге значення пари
    private double y;

    /**
     * Конструктор, який ініціалізує об'єкт DataPair заданими значеннями.
     *
     * @param x перше значення пари
     * @param y друге значення пари
     */
    public DataPair(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Повертає перше значення пари.
     *
     * @return значення x
     */
    public double getX() {
        return x;
    }

    /**
     * Встановлює нове значення для x.
     *
     * @param x нове значення для першої координати
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Повертає друге значення пари.
     *
     * @return значення y
     */
    public double getY() {
        return y;
    }

    /**
     * Встановлює нове значення для y.
     *
     * @param y нове значення для другої координати
     */
    public void setY(double y) {
        this.y = y;
    }
}

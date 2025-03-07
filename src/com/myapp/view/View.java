
package com.myapp.view;

import java.io.IOException;

/**
 * Інтерфейс відображення результатів.
 * Частина компонентної структури для реалізації шаблону Command.
 * 
 * Завдання 1: Реалізувати можливість скасування (undo) операцій (команд).
 * Інтерфейс визначає методи для збереження та відновлення стану об'єктів,
 * що є ключовим механізмом для реалізації можливості скасування операцій.
 * Методи viewSave() та viewRestore() забезпечують серіалізацію поточного
 * стану та його відновлення за потреби.
 * 
 * @author Тесленко Павло
 * @version 1.0
 */
public interface View {
    /**
     * Ініціалізація відображення.
     * Стан об'єкта після ініціалізації можна зберегти для подальшого скасування (undo).
     */
    public void viewInit();

    /**
     * Відображення результатів.
     * Виводить поточний стан, з можливістю повернення до попереднього (undo).
     */
    public void viewShow();

    /**
     * Збереження результатів.
     * Зберігає стан об'єкта, що дозволяє реалізувати скасування операцій (undo).
     *
     * @throws IOException помилка при збереженні
     */
    public void viewSave() throws IOException;

    /**
     * Відновлення результатів.
     * Може використовуватися для реалізації функції undo - повернення до попереднього стану.
     * 
     * @throws Exception помилка при відновленні
     */
    public void viewRestore() throws Exception;
}

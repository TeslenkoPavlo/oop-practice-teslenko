
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
     */
    public void viewInit();

    /**
     * Відображення результатів.
     */
    public void viewShow();

    /**
     * Збереження результатів.
     *
     * @throws IOException помилка при збереженні
     */
    public void viewSave() throws IOException;

    /**
     * Відновлення результатів.
     *
     * @throws Exception помилка при відновленні
     */
    public void viewRestore() throws Exception;
}


package com.myapp.view;

import java.io.IOException;

/**
 * Інтерфейс відображення результатів.
 * 
 * Автор: xone
 * Версія: 1.0
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

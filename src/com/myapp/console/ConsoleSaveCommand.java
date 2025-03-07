
package com.myapp.console;

import com.myapp.viewimpl.ViewableTable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Команда збереження даних у файл.
 * Реалізує шаблон Command.
 * 
 * Завдання 1: Реалізувати можливість скасування (undo) операцій (команд).
 * Цей клас є ключовою частиною механізму скасування операцій, оскільки
 * він серіалізує поточний стан об'єктів у файл, що дозволяє пізніше
 * відновити цей стан за допомогою ConsoleRestoreCommand, ефективно
 * реалізуючи операцію undo.
 * 
 * Завдання 4: Забезпечити діалоговий інтерфейс із користувачем.
 * Клас реалізує інтерфейс IConsoleCommand, забезпечуючи взаємодію через
 * консоль та надаючи інформативні повідомлення про результат збереження.
 * 
 * @author Тесленко Павло
 * @version 1.0
 */

public class ConsoleSaveCommand implements IConsoleCommand {
    private ViewableTable viewableTable;

    public ConsoleSaveCommand(ViewableTable viewableTable) {
        this.viewableTable = viewableTable;
    }

    @Override
    public char getKey() {
        return 'з';
    }

    @Override
    public String toString() {
        return "'з'берегти";
    }

    @Override
    public void execute() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("items.bin"))) {
            oos.writeObject(viewableTable.getItems());
            System.out.println("✅ Дані успішно збережено у файл items.bin");
        } catch (Exception e) {
            System.out.println("❌ Помилка збереження: " + e.getMessage());
        }
    }
}

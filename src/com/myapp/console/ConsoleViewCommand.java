
package com.myapp.console;

import com.myapp.viewimpl.ViewableTable;

/**
 * Команда перегляду даних.
 * Реалізує шаблон Command.
 * 
 * Завдання 1: Реалізувати можливість скасування (undo) операцій (команд).
 * Цей клас, хоча безпосередньо не реалізує механізм скасування, є частиною
 * загальної архітектури команд, що підтримує можливість скасування операцій.
 * 
 * Завдання 4: Забезпечити діалоговий інтерфейс із користувачем.
 * Клас реалізує інтерфейс IConsoleCommand для забезпечення взаємодії через
 * консоль. Він відображає дані у форматованому вигляді, забезпечуючи
 * зручний перегляд інформації для користувача.
 * 
 * @author Тесленко Павло
 * @version 1.0
 */

public class ConsoleViewCommand implements IConsoleCommand {
    private ViewableTable viewableTable;

    public ConsoleViewCommand(ViewableTable viewableTable) {
        this.viewableTable = viewableTable;
    }

    @Override
    public char getKey() {
        return 'д';
    }

    @Override
    public String toString() {
        return "'д'ивитися";
    }

    @Override
    public void execute() {
        viewableTable.viewShow();
    }
}

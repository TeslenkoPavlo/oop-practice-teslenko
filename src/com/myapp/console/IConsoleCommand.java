package com.myapp.console;

/**
 * Інтерфейс консольної команди.
 * Шаблон Command.
 * 
 * Автор: xone
 * Версія: 1.0
 */
public interface IConsoleCommand extends ICommand {
    /**
     * Гаряча клавіша команди.
     *
     * @return символ гарячої клавіші
     */
    public char getKey();
}

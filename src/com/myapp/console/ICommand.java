package com.myapp.console;

/**
 * Інтерфейс команди або задачі.
 * Шаблони: Command, Worker Thread.
 * 
 * Автор: xone
 * Версія: 1.0
 */
public interface ICommand {
    /**
     * Виконання команди (шаблони: Command, Worker Thread).
     */
    public void execute();
}

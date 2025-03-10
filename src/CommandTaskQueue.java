
package src;

import java.util.Vector;

/**
 * Черга завдань для обробки команд (Worker Thread).
 * Реалізує шаблон проектування Worker Thread - створює окремий потік, який виконує
 * завдання з черги. Це дозволяє виконувати операції паралельно основному потоку програми.
 * 
 * Клас забезпечує розподілення обчислювальних ресурсів для обробки різних команд:
 * - Пошук мінімальних/максимальних значень
 * - Обчислення середнього значення
 * - Фільтрація елементів за критерієм
 * - Інші операції над даними
 * 
 * @author Павло Тесленко
 * @version 1.0
 * @see Command
 */
public class CommandTaskQueue implements TaskQueue {

    /** Черга команд - містить завдання, що очікують на виконання */
    private Vector<Command> taskQueue;
    /** Прапорець очікування - визначає, чи потік Worker перебуває в режимі очікування */
    private boolean isWaiting;
    /** Прапорець завершення роботи черги - сигналізує про необхідність завершення потоку */
    private boolean isShutdown;

    /**
     * Завершує роботу черги, встановлюючи прапорець isShutdown
     * Після цього потік Worker завершить своє виконання після обробки всіх завдань у черзі
     */
    public void shutdownQueue() {
        isShutdown = true;
    }

    /**
     * Ініціалізація черги команд та запуск робочого потоку.
     * Створює вектор для зберігання команд і запускає новий потік TaskWorker,
     * який обробляє завдання з черги в асинхронному режимі.
     */
    public CommandTaskQueue() {
        taskQueue = new Vector<Command>();
        isWaiting = false;
        new Thread(new TaskWorker()).start();
    }

    /**
     * Додає нову команду до черги.
     * Якщо робочий потік перебуває в стані очікування, надсилає сигнал
     * для продовження його роботи.
     * 
     * @param command команда для виконання
     */
    @Override
    public void enqueue(Command command) {
        taskQueue.add(command);
        if (isWaiting) {
            synchronized (this) {
                notifyAll();
            }
        }
    }

    /**
     * Виймає команду з черги.
     * Якщо черга порожня, потік переходить у стан очікування, 
     * доки не буде додано нову команду.
     * 
     * @return команда з черги для виконання
     */
    @Override
    public Command dequeue() {
        if (taskQueue.isEmpty()) {
            synchronized (this) {
                isWaiting = true;
                try {
                    wait();
                } catch (InterruptedException ie) {
                    isWaiting = false;
                }
            }
        }
        return taskQueue.remove(0);
    }

    /**
     * Внутрішній клас, що обслуговує чергу завдань.
     * Реалізує основний цикл обробки команд - постійно виймає команди
     * з черги та виконує їх, доки не отримає сигнал про завершення роботи.
     * Забезпечує паралельне виконання команд відносно основного потоку програми.
     */
    private class TaskWorker implements Runnable {
        @Override
        public void run() {
            while (!isShutdown) {
                Command command = dequeue();
                command.execute();
            }
        }
    }
}

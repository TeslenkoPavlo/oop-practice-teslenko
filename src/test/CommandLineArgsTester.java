
package src.test;

import src.domain.CommandLineArgsPrinter;

/**
 * <h1>Command Line Arguments Tester</h1>
 * 
 * Тестовий клас для демонстрації функціональності класу CommandLineArgsPrinter.
 * Цей клас створює екземпляр основного класу та передає йому аргументи
 * командного рядка для обробки та виводу.
 * 
 * @author Pavlo Teslenko
 */
public class CommandLineArgsTester {
    
    /**
     * Головний метод програми, який демонструє використання класу CommandLineArgsPrinter
     * 
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        // Виводимо вітальне повідомлення
        System.out.println("🚀 Запуск програми для виводу аргументів командного рядка\n");
        
        // Створюємо екземпляр класу CommandLineArgsPrinter
        CommandLineArgsPrinter printer = new CommandLineArgsPrinter();
        
        // Викликаємо метод для виводу аргументів
        printer.printArgs(args);
        
        // Виводимо завершальне повідомлення
        System.out.println("\n✅ Програма успішно завершила роботу!");
    }
}

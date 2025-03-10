package src;

import java.util.Scanner;
import java.util.Random;

/**
 * Консольна команда для зміни елемента колекції.
 * 
 * @author Павло Тесленко
 * @version 1.0
 */
public class ChangeConsoleCommand implements ConsoleCommand {
    /** Представлення для роботи з даними */
    private View view;
    /** Сканер для читання вводу */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Конструктор з ініціалізацією представлення
     * @param view представлення
     */
    public ChangeConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'з';
    }

    @Override
    public String toString() {
        return "📝 'з'мінити значення Y";
    }

    @Override
    public void execute() {
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String RED = "\u001B[31m";
        String BOLD = "\u001B[1m";

        System.out.println(YELLOW + BOLD + "🔧 ЗМІНА ЗНАЧЕНЬ Y В ТАБЛИЦІ 🔧" + RESET);
        System.out.println(CYAN + "══════════════════════════════════════" + RESET);
        System.out.println(view.viewData());
        
        try {
            // Генеруємо випадковий коефіцієнт масштабування від 0.5 до 2.5
            Random random = new Random();
            double coefficient = 0.5 + random.nextDouble() * 2.0; // від 0.5 до 2.5
            
            System.out.println(GREEN + "🔄 Згенеровано коефіцієнт масштабування: " + String.format("%.2f", coefficient) + RESET);
            
            for (Item2d item : ((ViewResult)view).getItems()) {
                item.setY(item.getY() * coefficient);
            }
            System.out.println(GREEN + BOLD + "✅ Значення Y успішно змінено для всіх елементів!" + RESET);
            System.out.println(CYAN + "══════════════════════════════════════" + RESET);
            System.out.println(view.viewData());
        } catch (Exception e) {
            System.out.println(RED + "❌ Помилка: " + e.getMessage() + RESET);
        }
    }
}
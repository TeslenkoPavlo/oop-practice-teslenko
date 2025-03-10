package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Клас для роботи з консольним меню.
 * 
 * @author Павло Тесленко
 * @version 1.0
 */
public class Menu {
    /** Список команд */
    private ArrayList<ConsoleCommand> commands = new ArrayList<>();
    /** Прапорець завершення роботи */
    private boolean isExited = false;
    /** Читач вводу з консолі */
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Додає команду в меню
     * @param command команда для додавання
     * @return додана команда
     */
    public ConsoleCommand add(ConsoleCommand command) {
        commands.add(command);
        return command;
    }

    /**
     * Видаляє команду з меню
     * @param command команда для видалення
     * @return видалена команда
     */
    public ConsoleCommand remove(ConsoleCommand command) {
        commands.remove(command);
        return command;
    }

    /**
     * Запускає обробку команд користувача
     */
    public void execute() {
        // Кольори для консолі
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String PURPLE = "\u001B[35m";
        String BLUE = "\u001B[34m";
        String BOLD = "\u001B[1m";

        System.out.println(BLUE + BOLD + "\n\n🌟 ВІТАЄМО В ПРОГРАМІ АНАЛІЗУ ДАНИХ 🌟" + RESET);

        while (!isExited) {
            showMenu();
            try {
                String input = reader.readLine();
                if (input.length() > 0) {
                    char key = input.charAt(0);
                    if (key == 'в' || key == 'q') {
                        isExited = true;
                        System.out.println(PURPLE + "\n👋 Дякуємо за використання програми!" + RESET);
                        System.out.println(RED + "🔒 Програма завершується..." + RESET);
                        System.exit(0); // Повне завершення програми
                        continue;
                    }
                    boolean commandFound = false;
                    for (ConsoleCommand cmd : commands) {
                        if (cmd.getKey() == key) {
                            cmd.execute();
                            commandFound = true;
                            break;
                        }
                    }
                    if (!commandFound) {
                        System.out.println(RED + "❌ Невідома команда! Спробуйте ще раз." + RESET);
                    }
                }
            } catch (IOException e) {
                System.err.println(RED + "❌ Помилка вводу: " + e.getMessage() + RESET);
            }
        }
    }

    /**
     * Відображає список доступних команд
     */
    private void showMenu() {
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String PURPLE = "\u001B[35m";
        String BOLD = "\u001B[1m";
        
        System.out.println("\n" + PURPLE + "═══════════════════════════════════════════════" + RESET);
        System.out.println(CYAN + BOLD + "📋 ДОСТУПНІ КОМАНДИ: " + RESET);
        
        for (ConsoleCommand cmd : commands) {
            System.out.println(YELLOW + cmd.toString() + RESET);
        }
        
        System.out.println(GREEN + "🚪 'в'ихід - завершити програму" + RESET);
        System.out.println(PURPLE + "═══════════════════════════════════════════════" + RESET);
        System.out.print(BOLD + "👉 Введіть команду: " + RESET);
    }
}
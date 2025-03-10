package src;

/**
 * Консольна команда для генерації нових випадкових даних.
 * 
 * @author Павло Тесленко
 * @version 1.0
 */
public class GenerateConsoleCommand implements ConsoleCommand {
    /** Представлення для роботи з даними */
    private View view;

    /**
     * Конструктор з ініціалізацією представлення
     * @param view представлення
     */
    public GenerateConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'г';
    }

    @Override
    public String toString() {
        return "🔄 'г'енерувати нові дані";
    }

    @Override
    public void execute() {
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        String GREEN = "\u001B[32m";
        String BOLD = "\u001B[1m";
        String YELLOW = "\u001B[33m"; //Added this back, it seems crucial for the original functionality


        ViewResult viewResult = (ViewResult) view;
        viewResult.viewInit();
        System.out.println(GREEN + BOLD + "\n\n✨ ДАНІ УСПІШНО ЗГЕНЕРОВАНО ✨" + RESET);
        System.out.println(CYAN + "══════════════════════════════════════" + RESET);
        System.out.println(YELLOW + "📋 Результат:" + RESET);
        viewResult.viewShow();
    }
}
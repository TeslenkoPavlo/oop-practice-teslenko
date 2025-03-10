package src;

/**
 * Консольна команда для відображення даних.
 * 
 * @author Павло Тесленко
 * @version 1.0
 */
public class ViewConsoleCommand implements ConsoleCommand {
    /** Представлення для роботи з даними */
    private View view;

    /**
     * Конструктор з ініціалізацією представлення
     * @param view представлення
     */
    public ViewConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'п';
    }

    @Override
    public String toString() {
        return "📊 'п'ереглянути дані";
    }

    @Override
    public void execute() {
        String RESET = "\u001B[0m";
        String CYAN = "\u001B[36m";
        String GREEN = "\u001B[32m";
        String BOLD = "\u001B[1m";

        System.out.println(CYAN + BOLD + "📊 ПЕРЕГЛЯД ПОТОЧНИХ ДАНИХ 📊" + RESET);
        System.out.println(GREEN + "══════════════════════════════════════" + RESET);
        view.viewShow();
    }
}
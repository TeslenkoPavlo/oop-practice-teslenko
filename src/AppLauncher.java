
package src;

/**
 * Точка входу в програму. Створює представлення даних,
 * меню команд та запускає обробку користувацьких команд.
 * 
 * @author Павло Тесленко
 * @version 5.0
 */
public class AppLauncher {

    /** Представлення даних */
    private View dataView = new ViewableResult().getView();
    /** Меню команд */
    private Menu commandMenu = new Menu();

    /** Обробляє команди користувача */
    public void run() {
        // Додаємо команди з новими українськими ключами
        commandMenu.add(new ViewConsoleCommand(dataView));
        commandMenu.add(new GenerateConsoleCommand(dataView));
        commandMenu.add(new ChangeConsoleCommand(dataView));
        commandMenu.add(new RunAllCommandsConsoleCommand(dataView));
        commandMenu.execute();
    }

    /**
     * Точка входу в програму.
     * @param args параметри запуску
     */
    public static void main(String[] args) {
        AppLauncher app = new AppLauncher();
        app.run();
    }
}

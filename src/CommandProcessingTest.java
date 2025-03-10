
package src;

/**
 * Тест для класу RunAllCommandsConsoleCommand
 * 
 * @author Тесленко Павло
 * @version 1.0
 */
public class CommandProcessingTest {
    
    // Кольори для форматування консольного виводу
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String BOLD = "\u001B[1m";
    
    private static ViewResult mockView;
    private static RunAllCommandsConsoleCommand command;
    
    /**
     * Підготовка тестових даних перед всіма тестами
     */
    private static void setUp() {
        printHeader("Початок тестування RunAllCommandsConsoleCommand");
        
        // Створення мокових даних для тесту
        mockView = new ViewResult(10); // Створюємо з 10 елементами
        mockView.viewInit();
        
        System.out.println(YELLOW + "📊 Заповнення тестових даних..." + RESET);
        
        // Заповнюємо дані вручну через список елементів
        for (int i = 0; i < 10; i++) {
            // Отримуємо існуючий елемент і змінюємо його значення
            Item2d item = mockView.getItems().get(i);
            item.setX(i);
            item.setY(i * 2 - 5);
            System.out.println(CYAN + "   ✓ Елемент " + i + ": X=" + item.getX() + ", Y=" + item.getY() + RESET);
        }
        
        // Створюємо команду для тестування
        command = new RunAllCommandsConsoleCommand(mockView);
        System.out.println(GREEN + "✓ Тестові дані підготовлено успішно!" + RESET);
        System.out.println();
    }
    
    /**
     * Тестує ключ команди
     */
    private static boolean testCommandKey() {
        System.out.println(YELLOW + "🔑 Тестування ключа команди..." + RESET);
        
        char expectedKey = 'а';
        char actualKey = command.getKey();
        
        boolean result = expectedKey == actualKey;
        
        if (result) {
            System.out.println(GREEN + "✓ Тест пройдено: ключ команди = '" + actualKey + "'" + RESET);
        } else {
            System.out.println(RED + "❌ Тест провалено: очікувалось '" + expectedKey + "', отримано '" + actualKey + "'" + RESET);
        }
        
        System.out.println();
        return result;
    }
    
    /**
     * Тестує опис команди
     */
    private static boolean testCommandToString() {
        System.out.println(YELLOW + "📝 Тестування опису команди..." + RESET);
        
        String description = command.toString();
        boolean notNull = description != null;
        boolean notEmpty = !description.isEmpty();
        boolean result = notNull && notEmpty;
        
        if (result) {
            System.out.println(GREEN + "✓ Тест пройдено: опис команди = '" + description + "'" + RESET);
        } else {
            if (!notNull) {
                System.out.println(RED + "❌ Тест провалено: опис команди має значення null" + RESET);
            } else if (!notEmpty) {
                System.out.println(RED + "❌ Тест провалено: опис команди є порожнім рядком" + RESET);
            }
        }
        
        System.out.println();
        return result;
    }
    
    /**
     * Тестує методи доступу до представлення
     */
    private static boolean testCommandView() {
        System.out.println(YELLOW + "👁️ Тестування методів доступу до представлення..." + RESET);
        
        // Перевірка наявності представлення
        boolean viewNotNull = command.getConsoleView() != null;
        
        if (viewNotNull) {
            System.out.println(GREEN + "✓ Представлення не є null" + RESET);
        } else {
            System.out.println(RED + "❌ Представлення є null" + RESET);
            return false;
        }
        
        // Перевірка встановлення представлення
        ViewResult newView = new ViewResult(5); // Створюємо з 5 елементами
        View returnedView = command.setConsoleView(newView);
        
        boolean viewSetCorrectly = returnedView == newView;
        boolean getViewReturnsCorrect = command.getConsoleView() == newView;
        
        if (viewSetCorrectly) {
            System.out.println(GREEN + "✓ Метод setConsoleView повертає те ж представлення" + RESET);
        } else {
            System.out.println(RED + "❌ Метод setConsoleView повертає неправильне представлення" + RESET);
        }
        
        if (getViewReturnsCorrect) {
            System.out.println(GREEN + "✓ Метод getConsoleView повертає правильне представлення" + RESET);
        } else {
            System.out.println(RED + "❌ Метод getConsoleView повертає неправильне представлення" + RESET);
        }
        
        boolean result = viewNotNull && viewSetCorrectly && getViewReturnsCorrect;
        
        System.out.println();
        return result;
    }
    
    /**
     * Виконується після всіх тестів
     */
    private static void tearDown() {
        command = null;
        mockView = null;
        System.out.println(PURPLE + "🧹 Тестові ресурси очищено" + RESET);
        printHeader("Тестування завершено");
    }
    
    /**
     * Метод для виведення заголовка
     */
    private static void printHeader(String title) {
        System.out.println();
        System.out.println(CYAN + "╔═════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║ " + BOLD + YELLOW + "  " + title + CYAN + "  ║" + RESET);
        System.out.println(CYAN + "╚═════════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
    }
    
    /**
     * Метод для виведення підсумків тестування
     */
    private static void printResults(boolean[] results) {
        int passed = 0;
        for (boolean result : results) {
            if (result) passed++;
        }
        
        System.out.println(CYAN + "╔═════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║ " + BOLD + YELLOW + "  📊 ПІДСУМОК ТЕСТУВАННЯ" + CYAN + "                                    ║" + RESET);
        System.out.println(CYAN + "╠═════════════════════════════════════════════════════════════════╣" + RESET);
        
        System.out.println(CYAN + "║ " + WHITE + "  📌 Загальна кількість тестів: " + results.length + CYAN + "                              ║" + RESET);
        System.out.println(CYAN + "║ " + GREEN + "  ✅ Успішних тестів: " + passed + CYAN + "                                        ║" + RESET);
        
        if (passed < results.length) {
            System.out.println(CYAN + "║ " + RED + "  ❌ Провалених тестів: " + (results.length - passed) + CYAN + "                                     ║" + RESET);
        } else {
            System.out.println(CYAN + "║ " + YELLOW + "  🎉 Всі тести пройдено успішно! " + CYAN + "                              ║" + RESET);
        }
        
        System.out.println(CYAN + "╚═════════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
    }
    
    /**
     * Головний метод для запуску тестів
     */
    public static void main(String[] args) {
        try {
            setUp();
            
            boolean[] results = new boolean[3];
            results[0] = testCommandKey();
            results[1] = testCommandToString();
            results[2] = testCommandView();
            
            printResults(results);
            
            tearDown();
        } catch (Exception e) {
            System.out.println(RED + "💥 Помилка при виконанні тестів: " + e.getMessage() + RESET);
            e.printStackTrace();
        }
    }
}

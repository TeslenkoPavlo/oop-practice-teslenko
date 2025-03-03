
package src.domain;

/**
 * <h1>Command Line Arguments Printer</h1>
 * 
 * Цей клас відповідає за обробку та форматований вивід аргументів 
 * командного рядка. Він демонструє правильне використання JavaDoc
 * та коментарів відповідно до стандартів Java.
 * 
 * @author Pavlo Teslenko
 */
public class CommandLineArgsPrinter {
    
    /** Константа, що визначає розділювач для виводу */
    private static final String SEPARATOR = "===========================================";
    
    /** Константа для виводу, коли аргументи відсутні */
    private static final String NO_ARGS_MESSAGE = "Аргументи командного рядка відсутні!";
    
    /**
     * Метод для обробки і виводу аргументів командного рядка
     * 
     * @param args масив рядків, що містить аргументи командного рядка
     * @return форматований рядок з аргументами
     */
    public String formatAndProcessArgs(String[] args) {
        // Створюємо StringBuilder для ефективної побудови результуючого рядка
        StringBuilder output = new StringBuilder();
        
        // Додаємо верхній розділювач
        output.append(SEPARATOR).append("\n");
        
        // Додаємо заголовок
        output.append("📋 АРГУМЕНТИ КОМАНДНОГО РЯДКА\n");
        
        // Додаємо ще один розділювач
        output.append(SEPARATOR).append("\n\n");
        
        // Перевіряємо, чи передані аргументи
        if (args == null || args.length == 0) {
            // Якщо аргументів немає, додаємо відповідне повідомлення
            output.append("❌ ").append(NO_ARGS_MESSAGE).append("\n");
        } else {
            // Додаємо інформацію про кількість аргументів
            output.append("📊 Кількість аргументів: ").append(args.length).append("\n\n");
            
            // Проходимо по всіх аргументах і додаємо їх до результату
            for (int i = 0; i < args.length; i++) {
                output.append("🔹 Аргумент #").append(i + 1).append(": ")
                      .append("\"").append(args[i]).append("\"").append("\n");
            }
        }
        
        // Додаємо нижній розділювач
        output.append("\n").append(SEPARATOR);
        
        // Повертаємо побудований рядок
        return output.toString();
    }
    
    /**
     * Допоміжний метод для виводу аргументів в консоль
     * 
     * @param args масив рядків, що містить аргументи командного рядка
     */
    public void printArgs(String[] args) {
        // Викликаємо метод форматування і виводимо результат
        System.out.println(formatAndProcessArgs(args));
    }
}

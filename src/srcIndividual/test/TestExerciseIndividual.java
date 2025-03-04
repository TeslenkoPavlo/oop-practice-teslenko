
package srcIndividual.test;

import srcIndividual.domain.ExerciseIndividual;

/**
 * Клас для тестування функціональності ExerciseIndividual.
 * 
 * @author Тесленко Павло
 */
public class TestExerciseIndividual {
    
    /**
     * Головний метод для запуску тестів та виведення результатів.
     * 
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        ExerciseIndividual exercise = new ExerciseIndividual();
        
        // Тестові значення
        long[] testNumbers = {0, 10, 255, 1000};
        
        // Виведення заголовку
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║  🔢 ТЕСТУВАННЯ ПІДРАХУНКУ ЦИФР 🔢  ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.println();
        
        // Виконання тестів для кожного числа
        for (long number : testNumbers) {
            testNumber(exercise, number);
            System.out.println("✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨✨");
            System.out.println();
        }
    }
    
    /**
     * Допоміжний метод для тестування одного числа.
     * 
     * @param exercise екземпляр класу ExerciseIndividual для тестування
     * @param number число для аналізу
     */
    private static void testNumber(ExerciseIndividual exercise, long number) {
        // Отримуємо результати
        int hexCount = exercise.countHexadecimalDigits(number);
        int octalCount = exercise.countOctalDigits(number);
        
        // Виводимо число в різних системах числення
        String hexString = Long.toHexString(Math.abs(number));
        String octalString = Long.toOctalString(Math.abs(number));
        
        // Виведення результатів
        System.out.println("📊 АНАЛІЗ ЧИСЛА: " + number);
        System.out.println("┌───────────────────────────────────────────┐");
        System.out.println("│ 🔶 У 16-річній системі: " + (number < 0 ? "-" : "") + hexString);
        System.out.println("│ 🔷 У 8-річній системі:  " + (number < 0 ? "-" : "") + octalString);
        System.out.println("├───────────────────────────────────────────┤");
        System.out.println("│ 📈 Кількість 16-річних цифр: " + hexCount);
        System.out.println("│ 📉 Кількість 8-річних цифр:  " + octalCount);
        System.out.println("└───────────────────────────────────────────┘");
    }
}

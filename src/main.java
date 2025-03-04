
import java.util.Scanner;

/**
 * Головний клас для вибору та запуску тестових програм з різних пакетів.
 * 
 * 
 */
public class main {
    /**
     * Головний метод програми, який відображає меню вибору тестових програм
     * і запускає вибрану користувачем програму.
     * 
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Виведення заголовка меню
        System.out.println("\n★═════════════════════════════════════════════════★");
        System.out.println("║         🌟 ГОЛОВНЕ МЕНЮ ВИБОРУ ПРОГРАМ 🌟        ║");
        System.out.println("★═════════════════════════════════════════════════★");
        System.out.println("║                                                  ║");
        System.out.println("║  1️⃣  📊 Тест Exercise (srcFirst)                 ║");
        System.out.println("║  2️⃣  🔄 Тест ExerciseSecond (srcSecond)          ║");
        System.out.println("║  3️⃣  📈 Тест ExerciseThird (srcThird)            ║");
        System.out.println("║  4️⃣  🧪 Тест ExerciseIndividual (srcIndividual)  ║");
        System.out.println("║  0️⃣  🚪 Вихід з програми                         ║");
        System.out.println("║                                                  ║");
        System.out.println("★═════════════════════════════════════════════════★");

        System.out.print("\n🔍 Ваш вибір (введіть номер): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            // Обробка вибору користувача
            switch (choice) {
                case 0:
                    System.out.println("\n🚪 Програму завершено. До побачення! 👋");
                    break;
                case 1:
                    System.out.println("\n🚀 Запуск тесту Exercise з srcFirst...\n");
                    System.out.println("⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️");
                    srcFirst.test.TestExercise.main(null);
                    break;
                case 2:
                    System.out.println("\n🚀 Запуск тесту ExerciseSecond з srcSecond...\n");
                    System.out.println("⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️");
                    srcSecond.test.TestExerciseSecond.main(null);
                    break;
                case 3:
                    System.out.println("\n🚀 Запуск тесту ExerciseThird з srcThird...\n");
                    System.out.println("⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️");
                    srcThird.test.TestExerciseThird.main(null);
                    break;
                case 4:
                    System.out.println("\n🚀 Запуск тесту ExerciseIndividual з srcIndividual...\n");
                    System.out.println("⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️");
                    srcIndividual.test.TestExerciseIndividual.main(null);
                    break;
                default:
                    System.out.println("\n❌ Помилка: Неправильний вибір! Введіть число від 0 до 4. ❌");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Помилка: Потрібно ввести числове значення! ❌");
        } catch (Exception e) {
            System.out.println("\n⛔ Критична помилка при запуску програми: " + e.getMessage());
            System.out.println("📋 Деталі помилки:");
            e.printStackTrace();
        }

        scanner.close();
    }
}

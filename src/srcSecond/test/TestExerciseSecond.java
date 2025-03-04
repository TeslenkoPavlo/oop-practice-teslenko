
package srcSecond.test;

import srcSecond.domain.ExerciseSecond;
import java.io.File;
import java.util.Scanner;

/**
 * Тестовий клас, що демонструє серіалізацію та десеріалізацію
 * об'єктів ExerciseSecond та показує ефект на transient поля.
 */
public class TestExerciseSecond {
    
    /**
     * Основний метод, який запускає інтерактивну демонстрацію.
     * 
     * @param args Аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = "object_state.ser";
        
        System.out.println("✨✨✨ Демонстрація серіалізації та transient полів ✨✨✨");
        
        try {
            // Створення об'єкта з початковими значеннями
            System.out.println("\n🔹 Створення об'єкта 🔹");
            System.out.print("📝 Введіть ім'я: ");
            String name = scanner.nextLine();
            
            System.out.print("🔢 Введіть вік: ");
            int age = Integer.parseInt(scanner.nextLine());
            
            System.out.print("🔐 Введіть пароль (буде transient): ");
            String password = scanner.nextLine();
            
            System.out.print("🔄 Введіть тимчасовий лічильник (буде transient): ");
            int counter = Integer.parseInt(scanner.nextLine());
            
            // Створення об'єкта
            ExerciseSecond originalObject = new ExerciseSecond(name, age, password, counter);
            
            // Показ початкового стану об'єкта
            System.out.println("\n🔹 Початковий стан об'єкта 🔹");
            System.out.println("📊 " + originalObject);
            
            // Збереження стану об'єкта
            System.out.println("\n🔹 Збереження стану об'єкта 🔹");
            if (originalObject.saveState(fileName)) {
                System.out.println("✅ Стан об'єкта успішно збережено у " + fileName);
            } else {
                System.out.println("❌ Не вдалося зберегти стан об'єкта");
                scanner.close();
                return;
            }
            
            // Модифікація лічильника для демонстрації змін перед відновленням
            System.out.println("\n🔹 Модифікація об'єкта перед відновленням 🔹");
            originalObject.incrementCounter();
            System.out.println("🔄 Лічильник збільшено. Новий стан:");
            System.out.println("📊 " + originalObject);
            
            // Відновлення стану об'єкта
            System.out.println("\n🔹 Відновлення стану об'єкта 🔹");
            ExerciseSecond restoredObject = ExerciseSecond.restoreState(fileName);
            
            if (restoredObject != null) {
                System.out.println("✅ Стан об'єкта успішно відновлено");
                
                // Показ відновленого стану об'єкта
                System.out.println("\n🔹 Відновлений стан об'єкта 🔹");
                System.out.println("📊 " + restoredObject);
                
                // Демонстрація того, що transient поля скидаються до значень за замовчуванням
                System.out.println("\n🔹 Вплив transient полів 🔹");
                System.out.println("🔐 Оригінальний пароль: " + originalObject.getPassword());
                System.out.println("🔐 Відновлений пароль: " + restoredObject.getPassword());
                
                System.out.println("🔄 Оригінальний лічильник: " + originalObject.getTemporaryCounter());
                System.out.println("🔄 Відновлений лічильник: " + restoredObject.getTemporaryCounter());
            } else {
                System.out.println("❌ Не вдалося відновити стан об'єкта");
            }
            
            // Видалення файлу
            System.out.println("\n🔹 Прибирання 🔹");
            File file = new File(fileName);
            if (file.delete()) {
                System.out.println("🗑️ Тимчасовий файл успішно видалено");
            } else {
                System.out.println("⚠️ Не вдалося видалити тимчасовий файл");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Помилка: введіть коректне числове значення");
        } catch (Exception e) {
            System.out.println("❌ Виникла помилка: " + e.getMessage());
        } finally {
            System.out.println("\n✨✨✨ Демонстрація завершена ✨✨✨");
            scanner.close();
        }
    }
}


package srcFirst.test;

import srcFirst.domain.Exercise;
import srcFirst.domain.ExerciseSolver;

import java.io.IOException;

/**
 * Тестовий клас для демонстрації функціональності класів Exercise та ExerciseSolver.
 * 
 * <p>Цей клас демонструє:</p>
 * <ul>
 *   <li>Створення і використання класу {@link Exercise}, який серіалізується для 
 *       зберігання параметрів і результатів обчислень</li>
 *   <li>Використання класу {@link ExerciseSolver}, який демонструє агрегування через 
 *       включення об'єкта Exercise</li>
 *   <li>Серіалізацію та десеріалізацію об'єктів Exercise</li>
 * </ul>
 */
public class TestExercise {
    /**
     * Основний метод для демонстрації функціональності класів.
     * 
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        System.out.println("\n📊 ТЕСТУВАННЯ КЛАСІВ EXERCISE ТА EXERCISESOLVER 📊");
        System.out.println("=====================================================");
        
        // Створення об'єкта Exercise
        Exercise exerciseTask = new Exercise(3.0, 4.0);
        System.out.println("\n🔹 СТВОРЕННЯ НОВОГО ОБ'ЄКТА 🔹");
        System.out.println("✅ Створено новий об'єкт Exercise з параметрами:");
        System.out.println("   🔸 Перший параметр: " + exerciseTask.getFirstParameter());
        System.out.println("   🔸 Другий параметр: " + exerciseTask.getSecondParameter());
        System.out.println("   🔸 Початковий результат: " + exerciseTask.getCalculationResult());
        
        // Створення об'єкта ExerciseSolver з використанням агрегування
        ExerciseSolver exerciseSolver = new ExerciseSolver(exerciseTask);
        
        // Обчислення результату
        System.out.println("\n🔹 ОБЧИСЛЕННЯ РЕЗУЛЬТАТУ 🔹");
        double calculationResult = exerciseSolver.solveExercise();
        System.out.println("✅ Обчислення виконано успішно!");
        System.out.println("   🔸 Формула: Перший параметр² + Другий параметр²");
        System.out.println("   🔸 Обчислення: " + exerciseTask.getFirstParameter() + "² + " + 
                           exerciseTask.getSecondParameter() + "² = " + calculationResult);
        
        // Серіалізація об'єкта у файл
        System.out.println("\n🔹 СЕРІАЛІЗАЦІЯ ОБ'ЄКТА 🔹");
        try {
            exerciseSolver.saveExerciseToFile("exercise.ser");
            System.out.println("✅ Об'єкт успішно збережено у файл!");
            System.out.println("   🔸 Шлях до файлу: srcFirst/exercise.ser");
        } catch (IOException e) {
            System.err.println("❌ Помилка при збереженні об'єкта: " + e.getMessage());
        }
        
        // Створення нового об'єкта ExerciseSolver
        ExerciseSolver newExerciseSolver = new ExerciseSolver(new Exercise());
        
        // Десеріалізація об'єкта з файлу
        System.out.println("\n🔹 ДЕСЕРІАЛІЗАЦІЯ ОБ'ЄКТА 🔹");
        try {
            newExerciseSolver.loadExerciseFromFile("exercise.ser");
            System.out.println("✅ Об'єкт успішно завантажено з файлу!");
            
            Exercise loadedExercise = newExerciseSolver.getExerciseTask();
            System.out.println("   🔸 Завантажений об'єкт Exercise з параметрами:");
            System.out.println("      - Перший параметр: " + loadedExercise.getFirstParameter());
            System.out.println("      - Другий параметр: " + loadedExercise.getSecondParameter());
            System.out.println("      - Результат: " + loadedExercise.getCalculationResult());
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Помилка при завантаженні об'єкта: " + e.getMessage());
        }
        
        System.out.println("\n=====================================================");
        System.out.println("📊 ТЕСТУВАННЯ ЗАВЕРШЕНО 📊");
    }
}

package srcFirst.test;

import srcFirst.domain.Exercise;
import srcFirst.domain.ExerciseSolver;

import java.io.File;
import java.io.IOException;

/**
 * Клас TestExercise тестує роботу з Exercise та ExerciseSolver,
 * зокрема серіалізацію та десеріалізацію об'єктів.
 */
public class TestExercise {
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

        // Створення об'єкта ExerciseSolver
        ExerciseSolver exerciseSolver = new ExerciseSolver(exerciseTask);

        // Обчислення результату
        System.out.println("\n🔹 ОБЧИСЛЕННЯ РЕЗУЛЬТАТУ 🔹");
        double calculationResult = exerciseSolver.solveExercise();
        System.out.println("✅ Обчислення виконано успішно!");
        System.out.println("   🔸 Формула: Перший параметр² + Другий параметр²");
        System.out.println("   🔸 Обчислення: "
                + exerciseTask.getFirstParameter() + "² + "
                + exerciseTask.getSecondParameter() + "² = "
                + calculationResult);

        // Підготовка теки та імені файлу для серіалізації
        String folderName = "srcFirst";    // назва теки
        String fileName = "exercise.ser";  // назва файлу
        File directory = new File(folderName);
        if (!directory.exists()) {
            directory.mkdirs(); // створюємо теку, якщо не існує
        }
        File file = new File(directory, fileName);

        // Серіалізація об'єкта у файл
        System.out.println("\n🔹 СЕРІАЛІЗАЦІЯ ОБ'ЄКТА 🔹");
        try {
            // Якщо файлу не існує, створюємо новий (необов'язково, але хай буде)
            if (!file.exists()) {
                file.createNewFile();
            }

            // Серіалізуємо
            exerciseSolver.saveExerciseToFile(file.getPath());
            System.out.println("✅ Об'єкт успішно збережено у файл!");
            System.out.println("   🔸 Шлях до файлу: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("❌ Помилка при збереженні об'єкта: " + e.getMessage());
        }

        // Створення нового ExerciseSolver для завантаження
        ExerciseSolver newExerciseSolver = new ExerciseSolver(new Exercise());

        // Десеріалізація об'єкта з файлу
        System.out.println("\n🔹 ДЕСЕРІАЛІЗАЦІЯ ОБ'ЄКТА 🔹");
        try {
            newExerciseSolver.loadExerciseFromFile(file.getPath());
            System.out.println("✅ Об'єкт успішно завантажено з файлу!");

            Exercise loadedExercise = newExerciseSolver.getExerciseTask();
            System.out.println("   🔸 Завантажений об'єкт Exercise з параметрами:");
            System.out.println("      - Перший параметр: " + loadedExercise.getFirstParameter());
            System.out.println("      - Другий параметр: " + loadedExercise.getSecondParameter());
            System.out.println("      - Результат: " + loadedExercise.getCalculationResult());

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Помилка при завантаженні об'єкта: " + e.getMessage());
        }

        // Видалення файлу
        System.out.println("\n🔹 ВИДАЛЕННЯ ФАЙЛУ ПІСЛЯ ДЕСЕРІАЛІЗАЦІЇ 🔹");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("✅ Файл успішно видалено!");
            } else {
                System.out.println("❌ Не вдалося видалити файл!");
            }
        } else {
            System.out.println("❌ Файл для видалення не знайдено!");
        }

        System.out.println("\n=====================================================");
        System.out.println("📊 ТЕСТУВАННЯ ЗАВЕРШЕНО 📊");
    }
}

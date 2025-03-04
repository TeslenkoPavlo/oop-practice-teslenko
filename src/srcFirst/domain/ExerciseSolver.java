
package srcFirst.domain;

import java.io.*;

/**
 * Клас ExerciseSolver вирішує завдання та керує серіалізацією/десеріалізацією об'єктів Exercise.
 * 
 * <p><strong>Агрегування:</strong> Цей клас демонструє принцип агрегування, оскільки 
 * містить поле типу {@link Exercise}, але не успадковує його. Агрегування - це відношення
 * "має" (has-a relationship), де один клас містить посилання на об'єкт іншого класу.</p>
 * 
 * <p>ExerciseSolver не є серіалізованим класом, але він управляє серіалізацією 
 * та десеріалізацією об'єктів {@link Exercise} через методи {@link #saveExerciseToFile}
 * та {@link #loadExerciseFromFile}.</p>
 * 
 * <p>Таким чином, клас ExerciseSolver виконує роль "управляючого" класу, який
 * використовує агрегований об'єкт Exercise для зберігання даних і обчислень.</p>
 */
public class ExerciseSolver {
    /**
     * Референція на об'єкт Exercise - приклад агрегування.
     * ExerciseSolver "має" (has-a) об'єкт Exercise, але не "є" (is-a) ним.
     */
    private Exercise exerciseTask;

    /**
     * Конструктор, що приймає об'єкт Exercise.
     * Демонструє агрегування через отримання посилання на існуючий об'єкт.
     * 
     * @param exerciseTask завдання для вирішення
     */
    public ExerciseSolver(Exercise exerciseTask) {
        this.exerciseTask = exerciseTask;
    }

    /**
     * Повертає агрегований об'єкт Exercise.
     * 
     * @return об'єкт Exercise
     */
    public Exercise getExerciseTask() {
        return exerciseTask;
    }

    /**
     * Встановлює агрегований об'єкт Exercise.
     * 
     * @param exerciseTask новий об'єкт Exercise
     */
    public void setExerciseTask(Exercise exerciseTask) {
        this.exerciseTask = exerciseTask;
    }

    /**
     * Вирішує завдання, обчислюючи суму квадратів параметрів агрегованого об'єкта Exercise.
     * Демонструє використання агрегованого об'єкта для виконання операцій.
     * 
     * @return результат обчислення
     */
    public double solveExercise() {
        double result = Math.pow(exerciseTask.getFirstParameter(), 2) + 
                        Math.pow(exerciseTask.getSecondParameter(), 2);
        exerciseTask.setCalculationResult(result);
        return result;
    }

    /**
     * Зберігає агрегований об'єкт Exercise у файл через серіалізацію.
     * Цей метод серіалізує агрегований об'єкт Exercise, який є Serializable.
     * 
     * @param filename ім'я файлу
     * @throws IOException якщо виникають проблеми з вводом/виводом
     */
    public void saveExerciseToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("srcFirst/" + filename))) {
            oos.writeObject(exerciseTask);
        }
    }

    /**
     * Завантажує об'єкт Exercise з файлу через десеріалізацію.
     * Відновлює стан агрегованого об'єкта з даних, збережених у файлі.
     * 
     * @param filename ім'я файлу
     * @throws IOException якщо виникають проблеми з вводом/виводом
     * @throws ClassNotFoundException якщо клас завантаженого об'єкта не знайдено
     */
    public void loadExerciseFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("srcFirst/" + filename))) {
            exerciseTask = (Exercise) ois.readObject();
        }
    }
}

package srcFirst.domain;

import java.io.*;

/**
 * Клас ExerciseSolver вирішує завдання та керує серіалізацією/десеріалізацією об'єктів Exercise.
 *
 * <p><strong>Агрегування:</strong> Цей клас демонструє принцип агрегування, оскільки
 * містить поле типу {@link Exercise}, але не успадковує його.</p>
 *
 * <p>Клас ExerciseSolver не є серіалізованим, але він управляє серіалізацією
 * та десеріалізацією об'єктів {@link Exercise} через методи
 * {@link #saveExerciseToFile} та {@link #loadExerciseFromFile}.</p>
 */
public class ExerciseSolver {
    /**
     * Референція на об'єкт Exercise - приклад агрегування.
     * ExerciseSolver "має" (has-a) об'єкт Exercise, але не "є" (is-a) ним.
     */
    private Exercise exerciseTask;

    /**
     * Конструктор, що приймає об'єкт Exercise.
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
     *
     * @return результат обчислення
     */
    public double solveExercise() {
        double result = Math.pow(exerciseTask.getFirstParameter(), 2)
                + Math.pow(exerciseTask.getSecondParameter(), 2);
        exerciseTask.setCalculationResult(result);
        return result;
    }

    /**
     * Зберігає агрегований об'єкт Exercise у файл через серіалізацію.
     *
     * @param filename шлях до файлу
     * @throws IOException якщо виникають проблеми з вводом/виводом
     */
    public void saveExerciseToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(exerciseTask);
        }
    }

    /**
     * Завантажує об'єкт Exercise з файлу через десеріалізацію.
     *
     * @param filename шлях до файлу
     * @throws IOException            якщо виникають проблеми з вводом/виводом
     * @throws ClassNotFoundException якщо клас завантаженого об'єкта не знайдено
     */
    public void loadExerciseFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            exerciseTask = (Exercise) ois.readObject();
        }
    }
}

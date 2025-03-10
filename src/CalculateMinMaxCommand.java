
package src;

import java.util.concurrent.TimeUnit;

/**
 * Завдання для обчислення індексів мінімального позитивного та максимального негативного елементів.
 * Є частиною системи паралельної обробки даних і демонструє можливість пошуку екстремальних 
 * значень у колекції за певними критеріями.
 * 
 * Клас реалізує алгоритм одночасного пошуку двох екстремумів у колекції:
 * - індексу мінімального позитивного елемента
 * - індексу максимального негативного елемента
 * 
 * Виконується як окрема команда в системі Worker Thread, що дозволяє 
 * виконувати обробку асинхронно, не блокуючи інтерфейс користувача.
 * 
 * @author Павло Тесленко
 * @version 1.0
 * @see Command
 * @see CommandTaskQueue
 */
public class CalculateMinMaxCommand implements Command {

    /** Індекс мінімального позитивного значення */
    private int minPositiveIndex = -1;
    /** Індекс максимального негативного значення */
    private int maxNegativeIndex = -1;
    /** Відсоток виконання операції для відображення прогресу користувачу */
    private int progressPercentage = 0;
    /** Представлення даних для обчислень - джерело елементів колекції */
    private ViewResult resultView;

    /**
     * Повертає поточне представлення результатів
     * @return поточне представлення даних
     */
    public ViewResult getResultView() {
        return resultView;
    }

    /**
     * Встановлює представлення результатів для обробки
     * @param resultView нове представлення даних
     * @return встановлене представлення для ланцюжка викликів
     */
    public ViewResult setResultView(ViewResult resultView) {
        return this.resultView = resultView;
    }

    /**
     * Конструктор з ініціалізацією представлення результатів
     * @param resultView джерело даних для обчислень
     */
    public CalculateMinMaxCommand(ViewResult resultView) {
        this.resultView = resultView;
    }

    /**
     * Повертає індекс мінімального позитивного значення
     * @return індекс елемента або -1, якщо такого елемента немає
     */
    public int getMinPositiveIndex() {
        return minPositiveIndex;
    }

    /**
     * Повертає індекс максимального негативного значення
     * @return індекс елемента або -1, якщо такого елемента немає
     */
    public int getMaxNegativeIndex() {
        return maxNegativeIndex;
    }

    /**
     * Перевіряє, чи завершилося виконання завдання
     * @return true, якщо завдання все ще виконується, false - якщо завершено
     */
    public boolean isRunning() {
        return progressPercentage < 100;
    }
    
    /**
     * Повертає відсоток виконання завдання
     * @return значення від 0 до 100, що показує прогрес виконання
     */
    public int getProgressPercentage() {
        return progressPercentage;
    }

    /**
     * Виконує обчислення індексів мінімального позитивного та максимального негативного елементів.
     * 
     * Метод послідовно перебирає всі елементи колекції і одночасно шукає:
     * - мінімальне позитивне значення (Y > 0) та його індекс
     * - максимальне негативне значення (Y < 0) та його індекс
     * 
     * Для імітації тривалого обчислення додано затримки між ітераціями.
     * Метод також відображає проміжні результати в консоль для демонстрації
     * прогресу виконання.
     */
    @Override
    public void execute() {
        progressPercentage = 0;
        System.out.println("Виконується обчислення мін/макс значень...");
        int idx = 0, size = resultView.getItems().size();
        for (Item2d item : resultView.getItems()) {
            if (item.getY() < 0) {
                if (maxNegativeIndex == -1 || resultView.getItems().get(maxNegativeIndex).getY() < item.getY()) {
                    maxNegativeIndex = idx;
                }
            } else {
                if (minPositiveIndex == -1 || resultView.getItems().get(minPositiveIndex).getY() > item.getY()) {
                    minPositiveIndex = idx;
                }
            }
            idx++;
            progressPercentage = idx * 100 / size;
            if (idx % (size / 5) == 0) {
                System.out.println("Прогрес обчислення мін/макс: " + progressPercentage + "%");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(5000 / size);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        if (minPositiveIndex > -1 && maxNegativeIndex > -1) {
            // Результати зберігаються у полях класу і будуть використані пізніше
        } else if (minPositiveIndex > -1) {
            // Тільки мінімальне позитивне значення знайдено
        } else if (maxNegativeIndex > -1) {
            // Тільки максимальне негативне значення знайдено
        } else {
            // Жодне значення не знайдено
        }
        progressPercentage = 100;
    }
}


package src;

import java.util.concurrent.TimeUnit;

/**
 * Завдання для обчислення середнього значення.
 * Є частиною системи паралельної обробки даних та використовується Worker Thread шаблоном.
 * 
 * Клас демонструє можливість статистичної обробки елементів колекції шляхом обчислення 
 * арифметичного середнього значення всіх елементів. Результат обчислення зберігається
 * для подальшого використання.
 * 
 * При виконанні команди в окремому потоці основний потік програми не блокується,
 * що дозволяє користувачу продовжувати роботу з програмою.
 * 
 * @author Павло Тесленко
 * @version 1.0
 * @see Command
 * @see CommandTaskQueue
 */
public class CalculateAverageCommand implements Command {

    /** Результат обчислення середнього значення */
    private double average = 0.0;
    /** Відсоток виконання операції для відображення прогресу користувачу */
    private int progressPercentage = 0;
    /** Представлення даних для обчислення - джерело елементів колекції */
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
     * Конструктор, що ініціалізує представлення результатів
     * @param resultView джерело даних для обчислень
     */
    public CalculateAverageCommand(ViewResult resultView) {
        this.resultView = resultView;
    }

    /**
     * Повертає обчислене середнє значення елементів колекції
     * @return середнє значення координати Y всіх елементів
     */
    public double getAverage() {
        return average;
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
     * Виконує обчислення середнього значення по колекції об'єктів.
     * Метод проходить через всі елементи колекції, додає значення Y до загальної суми
     * і в кінці ділить на кількість елементів.
     * 
     * Для імітації тривалого обчислення додано затримки між ітераціями.
     * Метод також відображає проміжні результати в консоль для демонстрації
     * прогресу виконання.
     */
    @Override
    public void execute() {
        progressPercentage = 0;
        System.out.println("Виконується обчислення середнього значення...");
        average = 0.0;
        int idx = 1, size = resultView.getItems().size();
        for (Item2d item : resultView.getItems()) {
            average += item.getY();
            progressPercentage = idx * 100 / size;
            if (idx++ % (size / 2) == 0) {
                System.out.println("Прогрес обчислення середнього: " + progressPercentage + "%");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(2000 / size);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        average /= size;
        System.out.println("Average done. Result = " + String.format("%.2f", average).replace('.', ','));
        progressPercentage = 100;
    }
}

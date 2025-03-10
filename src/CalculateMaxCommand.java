package src;

import java.util.concurrent.TimeUnit;

/**
 * Завдання для обчислення індексу елемента з максимальним значенням.
 * Використовується обробником потоку (Worker Thread).
 * 
 * Клас демонструє пошук екстремального значення в колекції елементів.
 * Виконується асинхронно в окремому потоці, що дозволяє програмі
 * залишатися реактивною під час тривалих обчислень.
 * 
 * @author Павло Тесленко
 * @version 1.0
 * @see Command
 * @see CommandTaskQueue
 */
public class CalculateMaxCommand implements Command {

    /** Індекс елемента з максимальним значенням */
    private int maxIndex = -1;
    /** Відсоток виконання */
    private int progressPercentage = 0;
    /** Представлення даних для обчислень */
    private ViewResult resultView;

    /** Повертає поточне представлення результатів */
    public ViewResult getResultView() {
        return resultView;
    }

    /** Встановлює представлення результатів */
    public ViewResult setResultView(ViewResult resultView) {
        return this.resultView = resultView;
    }

    /** Конструктор з ініціалізацією представлення результатів */
    public CalculateMaxCommand(ViewResult resultView) {
        this.resultView = resultView;
    }

    /** Повертає індекс елемента з максимальним значенням */
    public int getMaxIndex() {
        return maxIndex;
    }

    /** Перевіряє, чи завершилося виконання завдання */
    public boolean isRunning() {
        return progressPercentage < 100;
    }

    /** Повертає відсоток виконання */
    public int getProgressPercentage() {
        return progressPercentage;
    }

    /**
     * Виконує обчислення максимального елемента колекції.
     */
    @Override
    public void execute() {
        progressPercentage = 0;
        System.out.println("Виконується обчислення максимального значення...");
        int size = resultView.getItems().size();
        maxIndex = 0;
        for (int idx = 1; idx < size; idx++) {
            if (resultView.getItems().get(maxIndex).getY() < resultView.getItems().get(idx).getY()) {
                maxIndex = idx;
            }
            progressPercentage = idx * 100 / size;
            if (idx % (size / 3) == 0) {
                System.out.println("Прогрес обчислення максимуму: " + progressPercentage + "%");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(3000 / size);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        progressPercentage = 100;
        System.out.println("Max done. Item #" + maxIndex + " found: x = " + 
                           resultView.getItems().get(maxIndex).getX() + 
                           ", y = " + resultView.getItems().get(maxIndex).getY());
    }
}
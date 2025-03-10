
package src;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Завдання для фільтрації елементів за пороговим значенням.
 * Є частиною системи паралельної обробки даних і демонструє можливість 
 * відбору елементів колекції за критерієм.
 * 
 * Клас реалізує фільтрацію елементів, значення яких перевищують задане порогове значення.
 * Результат фільтрації зберігається в окремій колекції для подальшого використання.
 * 
 * Виконується як окрема команда в системі Worker Thread, що дозволяє 
 * проводити фільтрацію асинхронно, не блокуючи інтерфейс користувача.
 * 
 * @author Павло Тесленко
 * @version 1.0
 * @see Command
 * @see CommandTaskQueue
 */
public class FilterByValueCommand implements Command {
    /** Відфільтровані елементи - результат фільтрації */
    private List<Item2d> filteredItems;
    /** Порогове значення для фільтрації - елементи, що перевищують це значення, будуть відібрані */
    private double threshold = 0.0;
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
    public FilterByValueCommand(ViewResult resultView) {
        this.resultView = resultView;
        this.filteredItems = new ArrayList<>();
    }

    /**
     * Повертає список відфільтрованих елементів
     * @return колекція елементів, що задовольняють критерію фільтрації
     */
    public List<Item2d> getFilteredItems() {
        return filteredItems;
    }

    /**
     * Встановлює порогове значення для фільтрації
     * @param threshold порогове значення - елементи, модуль значення Y яких 
     * перевищує це значення, будуть включені до результату
     */
    public void setThreshold(double threshold) {
        this.threshold = threshold;
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
     * Виконує фільтрацію елементів за пороговим значенням.
     * 
     * Метод перебирає всі елементи колекції і відбирає ті, модуль значення Y
     * яких перевищує встановлене порогове значення. Відфільтровані елементи
     * зберігаються в окремій колекції.
     * 
     * Для імітації тривалого обчислення додано затримки між ітераціями.
     * Метод також відображає проміжні результати в консоль для демонстрації
     * прогресу виконання, а в кінці виводить результати фільтрації.
     */
    @Override
    public void execute() {
        progressPercentage = 0;
        System.out.println("Виконується фільтрація елементів за порогом: " + threshold + "...");
        filteredItems.clear();

        int idx = 0, size = resultView.getItems().size();
        for (Item2d item : resultView.getItems()) {
            if (Math.abs(item.getY()) > threshold) {
                filteredItems.add(item);
            }
            idx++;
            progressPercentage = idx * 100 / size;
            if (idx % (size / 4) == 0) {
                System.out.println("Прогрес фільтрації: " + progressPercentage + "%");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(4000 / size);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

        System.out.println("Фільтрацію завершено. Знайдено " + filteredItems.size() + " елементів.");

        // Виведення результатів фільтрації
        if (!filteredItems.isEmpty()) {
            System.out.println("Результати фільтрації:");
            System.out.println("-------------------------------");
            System.out.println("| № |    X    |    Y    |");
            System.out.println("-------------------------------");
            for (int i = 0; i < filteredItems.size(); i++) {
                Item2d item = filteredItems.get(i);
                System.out.printf("| %2d | %8.2f | %8.2f |%n", i, item.getX(), item.getY());
            }
            System.out.println("-------------------------------");
        }

        progressPercentage = 100;
    }
}

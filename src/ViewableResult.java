package src;

/**
 * Клас, що представляє результат, який можна відобразити.
 * Використовується для повернення представлення.
 * 
 * @author Павло Тесленко
 * @version 1.0
 */
public class ViewableResult {
    /** Представлення з результатами */
    private ViewResult view;

    /**
     * Конструктор за замовчуванням, створює колекцію з 10 елементами
     */
    public ViewableResult() {
        view = new ViewResult(10);
        view.viewInit();
    }

    /**
     * Повертає представлення
     * @return представлення даних
     */
    public View getView() {
        return view;
    }
}
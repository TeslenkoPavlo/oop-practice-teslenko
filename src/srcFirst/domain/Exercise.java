package srcFirst.domain;

import java.io.Serializable;

/**
 * Клас Exercise представляє завдання з двома параметрами та результатом обчислення.
 *
 * <p><strong>Серіалізація:</strong> Цей клас реалізує інтерфейс {@link Serializable},
 * що дозволяє зберігати стан об'єктів цього класу (параметри та результати обчислень)
 * у байтовий потік для подальшого відновлення.</p>
 *
 * <p>Поля {@code firstParameter}, {@code secondParameter} та {@code calculationResult}
 * зберігають параметри та результат обчислення і серіалізуються разом з об'єктом.</p>
 *
 * @see java.io.Serializable
 */
public class Exercise implements Serializable {
    /**
     * Ідентифікатор версії серіалізованого класу (SerialVersionUID).
     * Необхідний для забезпечення сумісності серіалізованих даних
     * між різними версіями класу.
     */
    private static final long serialVersionUID = 1L;

    /** Перший параметр для обчислення */
    private double firstParameter;

    /** Другий параметр для обчислення */
    private double secondParameter;

    /** Результат обчислення, який зберігається разом з параметрами */
    private double calculationResult;

    /**
     * Конструктор за замовчуванням.
     * Ініціалізує всі параметри та результат обчислення нульовими значеннями.
     */
    public Exercise() {
        this.firstParameter = 0.0;
        this.secondParameter = 0.0;
        this.calculationResult = 0.0;
    }

    /**
     * Конструктор з параметрами.
     * Ініціалізує об'єкт із заданими параметрами та нульовим результатом.
     *
     * @param firstParameter  перший параметр
     * @param secondParameter другий параметр
     */
    public Exercise(double firstParameter, double secondParameter) {
        this.firstParameter = firstParameter;
        this.secondParameter = secondParameter;
        this.calculationResult = 0.0;
    }

    /**
     * Повертає перший параметр.
     *
     * @return перший параметр
     */
    public double getFirstParameter() {
        return firstParameter;
    }

    /**
     * Встановлює перший параметр.
     *
     * @param firstParameter новий перший параметр
     */
    public void setFirstParameter(double firstParameter) {
        this.firstParameter = firstParameter;
    }

    /**
     * Повертає другий параметр.
     *
     * @return другий параметр
     */
    public double getSecondParameter() {
        return secondParameter;
    }

    /**
     * Встановлює другий параметр.
     *
     * @param secondParameter новий другий параметр
     */
    public void setSecondParameter(double secondParameter) {
        this.secondParameter = secondParameter;
    }

    /**
     * Повертає результат обчислення, який зберігається в об'єкті.
     *
     * @return результат обчислення
     */
    public double getCalculationResult() {
        return calculationResult;
    }

    /**
     * Встановлює результат обчислення.
     *
     * @param calculationResult новий результат обчислення
     */
    public void setCalculationResult(double calculationResult) {
        this.calculationResult = calculationResult;
    }

    /**
     * Метод для обчислення результату за формулою (сума квадратів параметрів).
     * Результат зберігається в полі calculationResult.
     *
     * @return результат обчислення
     */
    public double calculateResult() {
        calculationResult = Math.pow(firstParameter, 2) + Math.pow(secondParameter, 2);
        return calculationResult;
    }

    /**
     * Повертає рядкове представлення об'єкта Exercise.
     *
     * @return рядок, що містить інформацію про параметри та результат
     */
    @Override
    public String toString() {
        return "Exercise{" +
                "firstParameter=" + firstParameter +
                ", secondParameter=" + secondParameter +
                ", calculationResult=" + calculationResult +
                '}';
    }
}

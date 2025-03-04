
package srcSecond.domain;

import java.io.*;

/**
 * Клас, що демонструє процес серіалізації та десеріалізації,
 * показуючи особливості використання transient полів.
 */
public class ExerciseSecond implements Serializable {
    // Серійний версійний UID для сумісності серіалізації
    private static final long serialVersionUID = 1L;
    
    // Звичайні поля, які серіалізуються
    private String name;
    private int age;
    
    // Transient поля, які не будуть серіалізовані
    private transient String password;
    private transient int temporaryCounter;
    
    /**
     * Конструктор для ініціалізації всіх полів.
     * 
     * @param name Ім'я користувача
     * @param age Вік користувача
     * @param password Пароль користувача (буде позначено як transient)
     * @param temporaryCounter Тимчасовий лічильник (буде позначено як transient)
     */
    public ExerciseSecond(String name, int age, String password, int temporaryCounter) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.temporaryCounter = temporaryCounter;
    }
    
    /**
     * Зберігає поточний стан об'єкта у файл, використовуючи серіалізацію.
     * 
     * @param fileName Назва файлу для збереження стану
     * @return true, якщо збереження успішне, false у протилежному випадку
     */
    public boolean saveState(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            // Серіалізуємо цей об'єкт у вказаний файл
            oos.writeObject(this);
            System.out.println("Об'єкт успішно серіалізовано у файл: " + fileName);
            return true;
        } catch (IOException e) {
            System.out.println("Помилка збереження стану: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Відновлює стан об'єкта з файлу, використовуючи десеріалізацію.
     * 
     * @param fileName Назва файлу, з якого відновлюється стан
     * @return Відновлений об'єкт або null, якщо відновлення не вдалося
     */
    public static ExerciseSecond restoreState(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fileName))) {
            // Десеріалізуємо об'єкт з вказаного файлу
            System.out.println("Виконується десеріалізація об'єкта з файлу: " + fileName);
            return (ExerciseSecond) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка відновлення стану: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Встановлює значення для transient поля password.
     * Це демонструє, що transient поля потрібно 
     * вручну відновлювати після десеріалізації.
     * 
     * @param password Пароль для встановлення
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Збільшує тимчасовий лічильник.
     * Це демонструє зміни у transient полях.
     */
    public void incrementCounter() {
        this.temporaryCounter++;
    }
    
    /**
     * Повертає рядкове представлення об'єкта, що показує всі поля.
     * 
     * @return Рядкове представлення об'єкта
     */
    @Override
    public String toString() {
        return "ExerciseSecond [name=" + name + 
               ", age=" + age + 
               ", password=" + (password != null ? password : "null") + 
               ", temporaryCounter=" + temporaryCounter + "]";
    }
    
    // Гетери для всіх полів
    
    /**
     * Отримує ім'я.
     * @return ім'я
     */
    public String getName() {
        return name;
    }
    
    /**
     * Отримує вік.
     * @return вік
     */
    public int getAge() {
        return age;
    }
    
    /**
     * Отримує пароль (transient поле).
     * @return пароль
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Отримує тимчасовий лічильник (transient поле).
     * @return тимчасовий лічильник
     */
    public int getTemporaryCounter() {
        return temporaryCounter;
    }
}

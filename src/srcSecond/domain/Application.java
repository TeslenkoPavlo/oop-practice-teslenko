
package src.srcSecond.domain;

import java.io.*;
import java.util.*;

/**
 * Основний клас програми, що демонструє роботу з відображенням результатів у вигляді таблиці.
 * <p>
 * Реалізує пункт 3 завдання: Забезпечити діалоговий інтерфейс із користувачем.
 * <p>
 * Використовує об'єкти TableView, створені через фабрику TableViewFactory.
 * 
 * @author Тесленко Павло
 * @version 1.0
 */
public class Application {
    /**
     * Константи для кольорового виводу в консоль
     */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BOLD = "\u001B[1m";
    
    // Символи для оформлення
    public static final String MATH_SYMBOL = "🧮";
    public static final String VIEW_SYMBOL = "👁️";
    public static final String GENERATE_SYMBOL = "🔄";
    public static final String SAVE_SYMBOL = "💾";
    public static final String RESTORE_SYMBOL = "📂";
    public static final String EXIT_SYMBOL = "🚪";
    
    /**
     * Константи для меню
     */
    private static final String MENU_HEADER = "\n====== МЕНЮ ======";
    private static final String MENU_FOOTER = "==================";
    
    /**
     * Фабрика для створення об'єктів TableView
     */
    private TableViewFactory tableViewFactory;
    
    /**
     * Об'єкт для відображення даних у вигляді таблиці
     */
    private TableView tableView;
    
    /**
     * Дані для відображення в таблиці
     */
    private List<ComputationResult> results;
    
    /**
     * Ім'я файлу для збереження/відновлення даних
     */
    private static final String FILE_NAME = "results.ser";
    
    /**
     * Конструктор за замовчуванням
     */
    public Application() {
        this.tableViewFactory = new ConcreteTableViewFactory();
        this.tableView = tableViewFactory.createTableView();
        this.results = new ArrayList<>();
    }
    
    /**
     * Конструктор з вказаною фабрикою
     * 
     * @param factory фабрика для створення об'єктів TableView
     */
    public Application(TableViewFactory factory) {
        this.tableViewFactory = factory;
        this.tableView = factory.createTableView();
        this.results = new ArrayList<>();
    }
    
    /**
     * Ініціалізує список результатів обчислень
     */
    public void initializeResults() {
        results.clear();
        // Додаємо два нульових результати
        results.add(new ComputationResult(0.0, 0.0));
        results.add(new ComputationResult(0.0, 0.0));
    }
    
    /**
     * Запускає головне меню програми
     */
    public void run() {
        displayHeader();
        
        // Інтерактивне меню
        boolean exit = false;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        initializeResults();  // Ініціалізація початкових даних
        
        do {
            try {
                displayInteractiveMenu();
                System.out.print(ANSI_CYAN + "Ваш вибір: " + ANSI_RESET);
                String input = in.readLine();
                char command = '?';
                if (input != null && !input.isEmpty()) {
                    command = input.charAt(0);
                }
                
                switch (command) {
                    case 'д':
                        System.out.println(ANSI_BOLD + ANSI_BLUE + "\n" + VIEW_SYMBOL + " Відображення поточних даних." + ANSI_RESET);
                        displayCurrentTable();
                        break;
                        
                    case 'г':
                        System.out.println(ANSI_BOLD + ANSI_BLUE + "\n" + GENERATE_SYMBOL + " Генерація випадкових даних." + ANSI_RESET);
                        generateRandomData();
                        displayCurrentTable();
                        break;
                        
                    case 'з':
                        System.out.println(ANSI_BOLD + ANSI_YELLOW + "\n" + SAVE_SYMBOL + " Зберігаємо поточні дані..." + ANSI_RESET);
                        try {
                            saveResults();
                            System.out.println(ANSI_BOLD + ANSI_GREEN + "✅ Дані успішно збережено!" + ANSI_RESET);
                        } catch (IOException e) {
                            System.out.println(ANSI_BOLD + ANSI_RED + "❌ Помилка при збереженні: " + e.getMessage() + ANSI_RESET);
                        }
                        break;
                        
                    case 'п':
                        System.out.println(ANSI_BOLD + ANSI_YELLOW + "\n" + RESTORE_SYMBOL + " Відбувається десеріалізація..." + ANSI_RESET);
                        try {
                            restoreResults();
                            System.out.println(ANSI_BOLD + ANSI_GREEN + "✅ Дані успішно відновлено із збереженого файлу!" + ANSI_RESET);
                            displayCurrentTable();
                        } catch (Exception e) {
                            System.out.println(ANSI_BOLD + ANSI_RED + "❌ Помилка при відновленні даних: " + e.getMessage() + ANSI_RESET);
                        }
                        break;
                        
                    case 'в':
                        System.out.println(ANSI_BOLD + ANSI_RED + "\n" + EXIT_SYMBOL + " Вихід з програми..." + ANSI_RESET);
                        exit = true;
                        break;
                        
                    default:
                        System.out.println(ANSI_BOLD + ANSI_RED + "❓ Невідома команда. Спробуйте ще раз." + ANSI_RESET);
                }
                
            } catch (IOException e) {
                System.out.println(ANSI_BOLD + ANSI_RED + "❌ Помилка читання команди: " + e.getMessage() + ANSI_RESET);
            }
            
        } while (!exit);
        
        System.out.println(ANSI_BOLD + ANSI_RED + "\n🚀 Програма успішно завершена! 👋" + ANSI_RESET);
    }
    
    /**
     * Відображає заголовок програми
     */
    private void displayHeader() {
        System.out.println(ANSI_BOLD + ANSI_PURPLE + "╔══════════════════════════════════════════╗" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_PURPLE + "║        " + MATH_SYMBOL + " ОБЧИСЛЮВАЛЬНА СИСТЕМА " + MATH_SYMBOL + "        ║" + ANSI_RESET);
        System.out.println(ANSI_BOLD + ANSI_PURPLE + "╚══════════════════════════════════════════╝" + ANSI_RESET);
    }
    
    /**
     * Відображає інтерактивне меню
     */
    private void displayInteractiveMenu() {
        System.out.println(ANSI_BOLD + ANSI_CYAN + "\n✨ Введіть команду... ✨" + ANSI_RESET);
        System.out.print(ANSI_YELLOW + EXIT_SYMBOL + " '" + ANSI_BOLD + "в" + ANSI_RESET + ANSI_YELLOW + "'ихід, " + 
                           VIEW_SYMBOL + " '" + ANSI_BOLD + "д" + ANSI_RESET + ANSI_YELLOW + "'ивитися, " + 
                           GENERATE_SYMBOL + " '" + ANSI_BOLD + "г" + ANSI_RESET + ANSI_YELLOW + "'енерувати, " + 
                           SAVE_SYMBOL + " '" + ANSI_BOLD + "з" + ANSI_RESET + ANSI_YELLOW + "'берегти, " + 
                           RESTORE_SYMBOL + " '" + ANSI_BOLD + "п" + ANSI_RESET + ANSI_YELLOW + "'ереглянути: " + ANSI_RESET);
    }
    
    /**
     * Відображає таблицю з результатами
     * 
     * <p>Демонстрація динамічного призначення методів (пізнє зв'язування, поліморфізм).
     * У цьому методі змінна {@code tableView} має тип інтерфейсу {@link TableView},
     * але фактично посилається на об'єкт класу {@link ConcreteTableView}.</p>
     * 
     * <p>При виклику {@code tableView.displayTable()}, визначення того, який саме метод
     * виконати (реалізацію в {@link ConcreteTableView} чи деяку іншу реалізацію),
     * відбувається під час виконання програми, а не під час компіляції. Це і є
     * прикладом dynamic method dispatch - методи призначаються динамічно в залежності
     * від фактичного типу об'єкта.</p>
     */
    private void displayCurrentTable() {
        if (results.isEmpty()) {
            System.out.println(ANSI_BOLD + ANSI_RED + "❌ Немає даних для відображення. Спочатку ініціалізуйте дані." + ANSI_RESET);
            return;
        }
        
        // Встановлення даних для відображення
        if (tableView instanceof ConcreteTableView) {
            ((ConcreteTableView) tableView).setData(results);
        }
        
        // Відображення таблиці
        tableView.displayTable();
    }
    
    /**
     * Генерує випадкові дані, зберігаючи перший запис з нульовими координатами
     */
    private void generateRandomData() {
        if (results.isEmpty() || results.size() < 2) {
            initializeResults();  // Якщо даних немає або їх менше 2, ініціалізуємо заново
        }
        
        // Перші координати залишаємо нульовими
        results.get(0).setXY(0.0, 0.0);
        
        // Генеруємо випадкові значення для другої координати
        Random random = new Random();
        double randomX = random.nextDouble() * 100; // від 0 до 100
        double randomY = random.nextDouble() * 100; // від 0 до 100
        
        // Округляємо до 2 десяткових знаків для кращого відображення
        randomX = Math.round(randomX * 100.0) / 100.0;
        randomY = Math.round(randomY * 100.0) / 100.0;
        
        results.get(1).setXY(randomX, randomY);
        
        System.out.println(ANSI_BOLD + ANSI_GREEN + "✅ Дані успішно згенеровано!" + ANSI_RESET);
    }
    
    /**
     * Зберігає результати обчислень у файл для подальшого відновлення.
     * 
     * @throws IOException при помилці запису в файл
     */
    private void saveResults() throws IOException {
        if (results.isEmpty()) {
            throw new IOException("Немає даних для збереження.");
        }
        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(results);
        }
    }
    
    /**
     * Відновлює раніше збережені результати обчислень з файлу та видаляє файл після відновлення.
     * 
     * @throws IOException при помилці читання з файлу
     * @throws ClassNotFoundException якщо клас збереженого об'єкта не знайдено
     */
    @SuppressWarnings("unchecked")
    private void restoreResults() throws IOException, ClassNotFoundException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            throw new IOException("Файл з даними не знайдено.");
        }
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            results = (List<ComputationResult>) in.readObject();
        }
        
        // Видаляємо файл після десеріалізації
        if (!file.delete()) {
            System.out.println(ANSI_BOLD + ANSI_YELLOW + "⚠️ Увага: не вдалося видалити файл після десеріалізації." + ANSI_RESET);
        } else {
            System.out.println(ANSI_BOLD + ANSI_GREEN + "✅ Файл успішно видалено після десеріалізації." + ANSI_RESET);
        }
    }
    
    /**
     * Метод main - входна точка програми
     * 
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        System.out.println("Запуск програми для відображення таблиці");
        Application app = new Application();
        app.run();
    }
    
    /**
     * Клас, що представляє результат обчислення з координатами (x, y)
     */
    public static class ComputationResult implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private double x;
        private double y;
        
        /**
         * Конструктор за замовчуванням
         */
        public ComputationResult() {
            this.x = 0.0;
            this.y = 0.0;
        }
        
        /**
         * Конструктор з параметрами
         * 
         * @param x значення координати x
         * @param y значення координати y
         */
        public ComputationResult(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        /**
         * Отримати значення x
         * 
         * @return значення x
         */
        public double getX() {
            return x;
        }
        
        /**
         * Встановити значення x
         * 
         * @param x нове значення x
         */
        public void setX(double x) {
            this.x = x;
        }
        
        /**
         * Отримати значення y
         * 
         * @return значення y
         */
        public double getY() {
            return y;
        }
        
        /**
         * Встановити значення y
         * 
         * @param y нове значення y
         */
        public void setY(double y) {
            this.y = y;
        }
        
        /**
         * Встановити значення x та y
         * 
         * @param x нове значення x
         * @param y нове значення y
         */
        public void setXY(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        /**
         * Перевизначений метод equals
         * 
         * @param o об'єкт для порівняння
         * @return true, якщо об'єкти рівні, false - в іншому випадку
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            
            ComputationResult that = (ComputationResult) o;
            
            if (Double.compare(that.x, x) != 0) return false;
            return Double.compare(that.y, y) == 0;
        }
        
        /**
         * Перевизначений метод hashCode
         * 
         * @return хеш-код об'єкта
         */
        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = Double.doubleToLongBits(x);
            result = (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(y);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
        
        /**
         * Перевизначений метод toString
         * 
         * @return рядкове представлення об'єкта
         */
        @Override
        public String toString() {
            return "ComputationResult{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    
    /**
     * Конкретна реалізація фабрики TableViewFactory
     */
    public static class ConcreteTableViewFactory implements TableViewFactory {
        /**
         * Створює об'єкт TableView з шириною за замовчуванням
         * 
         * @return об'єкт типу TableView
         */
        @Override
        public TableView createTableView() {
            return new ConcreteTableView();
        }
        
        /**
         * Створює об'єкт TableView з вказаною шириною
         * 
         * @param width ширина таблиці
         * @return об'єкт типу TableView
         */
        @Override
        public TableView createTableView(int width) {
            return new ConcreteTableView(width);
        }
    }
    
    /**
     * Конкретна реалізація TableView для відображення даних у вигляді таблиці
     * <p>
     * Реалізує пункт 2 завдання: Продемонструвати заміщення (перевизначення, overriding),
     * поєднання (перевантаження, overloading), динамічне призначення методів
     * (Пізнє зв'язування, поліморфізм, dynamic method dispatch).
     * 
     * <h2>Демонстрація ООП концепцій:</h2>
     * 
     * <h3>Заміщення (overriding):</h3>
     * <ul>
     *   <li>{@link #displayTableHeader()} - перевизначає метод інтерфейсу {@link TableView#displayTableHeader()}</li>
     *   <li>{@link #displayTableBody()} - перевизначає метод інтерфейсу {@link TableView#displayTableBody()}</li>
     *   <li>{@link #displayTableFooter()} - перевизначає метод інтерфейсу {@link TableView#displayTableFooter()}</li>
     *   <li>{@link #displayTable()} - перевизначає метод інтерфейсу {@link TableView#displayTable()}</li>
     *   <li>{@link #setTableWidth(int)} - перевизначає метод інтерфейсу {@link TableView#setTableWidth(int)}</li>
     *   <li>{@link #getTableWidth()} - перевизначає метод інтерфейсу {@link TableView#getTableWidth()}</li>
     * </ul>
     * 
     * <h3>Поєднання (overloading):</h3>
     * <ul>
     *   <li>Перевантажені конструктори:
     *     <ul>
     *       <li>{@link #ConcreteTableView()} - конструктор без параметрів</li>
     *       <li>{@link #ConcreteTableView(int)} - конструктор з параметром ширини</li>
     *       <li>{@link #ConcreteTableView(int, List)} - конструктор з параметрами ширини та даних</li>
     *     </ul>
     *   </li>
     *   <li>Перевантажені методи:
     *     <ul>
     *       <li>{@link #setTableWidth(int)} - реалізація інтерфейсу</li>
     *       <li>{@link #setTableWidth(int, String)} - перевантажений метод з додатковим параметром</li>
     *     </ul>
     *   </li>
     * </ul>
     * 
     * <h3>Динамічне призначення методів (поліморфізм):</h3>
     * <p>
     * У методі {@link Application#displayCurrentTable()} використовується поліморфізм 
     * та пізнє зв'язування, коли змінна типу {@link TableView} посилається на об'єкт 
     * типу {@link ConcreteTableView}, і викликається метод реалізації, а не інтерфейсу:
     * <pre>
     * if (tableView instanceof ConcreteTableView) {
     *     ((ConcreteTableView) tableView).setData(results);
     * }
     * tableView.displayTable(); // викликає реалізацію ConcreteTableView.displayTable()
     * </pre>
     * </p>
     */
    public static class ConcreteTableView implements TableView {
        /** Визначає ширину таблиці за замовчуванням */
        private static final int DEFAULT_WIDTH = 40;
        
        /** Поточна ширина таблиці */
        private int width;
        
        /** Дані для відображення в таблиці */
        private List<ComputationResult> data;
        
        /**
         * Конструктор за замовчуванням
         * 
         * <p>Частина демонстрації поєднання (overloading) - перший із трьох перевантажених конструкторів</p>
         */
        public ConcreteTableView() {
            this.width = DEFAULT_WIDTH;
            this.data = new ArrayList<>();
        }
        
        /**
         * Конструктор з вказаною шириною таблиці
         * 
         * <p>Частина демонстрації поєднання (overloading) - другий із трьох перевантажених конструкторів</p>
         * 
         * @param width ширина таблиці
         */
        public ConcreteTableView(int width) {
            this.width = width;
            this.data = new ArrayList<>();
        }
        
        /**
         * Перевантажений конструктор з шириною і даними
         * 
         * <p>Частина демонстрації поєднання (overloading) - третій із трьох перевантажених конструкторів</p>
         * 
         * @param width ширина таблиці
         * @param data дані для відображення
         */
        public ConcreteTableView(int width, List<ComputationResult> data) {
            this.width = width;
            this.data = new ArrayList<>(data);
        }
        
        /**
         * Встановлює дані для відображення в таблиці
         * 
         * @param data список результатів обчислень
         */
        public void setData(List<ComputationResult> data) {
            this.data = new ArrayList<>(data);
        }
        
        /**
         * Отримати поточні дані таблиці
         * 
         * @return список результатів обчислень
         */
        public List<ComputationResult> getData() {
            return new ArrayList<>(data);
        }
        
        /**
         * Виводить горизонтальну лінію шириною width символів
         */
        private void drawLine() {
            for (int i = 0; i < width; i++) {
                System.out.print("-");
            }
            System.out.println();
        }
        
        /**
         * Відображає заголовок таблиці
         * 
         * <p>Демонстрація заміщення (overriding) - цей метод перевизначає 
         * абстрактний метод інтерфейсу {@link TableView#displayTableHeader()}</p>
         */
        @Override
        public void displayTableHeader() {
            drawLine();
            
            // Визначаємо ширину стовпців
            int colWidth = (width - 3) / 2;
            
            // Форматуємо та виводимо заголовок
            String format = "| %" + colWidth + "s | %" + colWidth + "s |\n";
            System.out.printf(format, "X", "Y");
            
            drawLine();
        }
        
        /**
         * Відображає тіло таблиці (дані)
         * 
         * <p>Демонстрація заміщення (overriding) - цей метод перевизначає 
         * абстрактний метод інтерфейсу {@link TableView#displayTableBody()}</p>
         */
        @Override
        public void displayTableBody() {
            if (data.isEmpty()) {
                System.out.printf("| %" + (width - 4) + "s |\n", "Немає даних для відображення");
                return;
            }
            
            // Визначаємо ширину стовпців
            int colWidth = (width - 3) / 2;
            
            // Форматуємо та виводимо дані
            String format = "| %" + colWidth + ".3f | %" + colWidth + ".3f |\n";
            for (ComputationResult result : data) {
                System.out.printf(format, result.getX(), result.getY());
            }
        }
        
        /**
         * Відображає підсумкову частину таблиці
         * 
         * <p>Демонстрація заміщення (overriding) - цей метод перевизначає 
         * абстрактний метод інтерфейсу {@link TableView#displayTableFooter()}</p>
         */
        @Override
        public void displayTableFooter() {
            drawLine();
        }
        
        /**
         * Відображає всю таблицю (заголовок, тіло, підсумок)
         * 
         * <p>Демонстрація заміщення (overriding) - цей метод перевизначає 
         * абстрактний метод інтерфейсу {@link TableView#displayTable()}</p>
         * 
         * <p>Цей метод демонструє пізнє зв'язування, оскільки при виклику 
         * {@code tableView.displayTable()} у методі {@link Application#displayCurrentTable()},
         * де {@code tableView} має тип {@link TableView}, виконується саме цей метод,
         * а не абстрактний метод інтерфейсу.</p>
         */
        @Override
        public void displayTable() {
            System.out.println("\n" + ANSI_BOLD + ANSI_GREEN + "📊 Відображення таблиці " + ANSI_RESET);
            displayTableHeader();
            displayTableBody();
            displayTableFooter();
        }
        
        /**
         * Встановлює ширину таблиці
         * 
         * <p>Демонстрація заміщення (overriding) - цей метод перевизначає 
         * абстрактний метод інтерфейсу {@link TableView#setTableWidth(int)}</p>
         * 
         * <p>Демонстрація поєднання (overloading) - цей метод є першим із двох
         * перевантажених методів {@code setTableWidth}</p>
         * 
         * @param width ширина таблиці в символах
         * @return встановлена ширина таблиці
         */
        @Override
        public int setTableWidth(int width) {
            // Мінімальна ширина - 20 символів
            if (width < 20) {
                width = 20;
            }
            this.width = width;
            return this.width;
        }
        
        /**
         * Повертає поточну ширину таблиці
         * Реалізація методу інтерфейсу
         * 
         * @return поточна ширина таблиці в символах
         */
        @Override
        public int getTableWidth() {
            return width;
        }
        
        /**
         * Перевантажений метод для демонстрації overloading
         * Встановлює ширину таблиці і назву заголовка
         * 
         * <p>Демонстрація поєднання (overloading) - цей метод є другим із двох 
         * перевантажених методів {@code setTableWidth}</p>
         * 
         * <p>Має той самий ідентифікатор (назву), але інший список параметрів 
         * порівняно з {@link #setTableWidth(int)}</p>
         * 
         * @param width ширина таблиці
         * @param title назва заголовка
         * @return встановлена ширина таблиці
         */
        public int setTableWidth(int width, String title) {
            System.out.println("Заголовок таблиці: " + title);
            return setTableWidth(width);
        }
    }
}

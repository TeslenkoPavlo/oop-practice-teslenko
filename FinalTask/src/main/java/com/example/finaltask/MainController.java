package com.example.finaltask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * Клас-контролер JavaFX, який керує інтерфейсом та логікою роботи програми.
 * Створює таблицю, панель із командами та виконує обробку подій.
 */
public class MainController {

    /**
     * Перелік можливих команд для ComboBox.
     */
    private enum Command {
        CREATE_TABLE("Створити таблицю"),
        GENERATE_VALUES("Згенерувати значення"),
        SAVE_VALUES("Зберегти значення"),
        VIEW_VALUES("Переглянути значення"),
        CHANGE_Y("Змінити значення стовпця Y"),
        SHOW_STATS("Показати статистику"),
        EXIT("Вихід");

        private final String displayName;

        Command(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    /** Основний список даних для таблиці (X, Y). */
    private final ObservableList<DataPair> dataList = FXCollections.observableArrayList();

    /** Таблиця для відображення пар (X, Y). */
    private TableView<DataPair> tableView;

    /** Головний контейнер (HBox), де зліва таблиця, а справа – панель управління. */
    private HBox root;

    /** Випадаючий список із командами. */
    private ComboBox<Command> comboBoxCommands;

    /** Кнопка для виконання обраної команди. */
    private Button btnExecute;

    /** Ім'я файлу для серіалізації/десеріалізації. */
    private final String fileName = "data.ser";

    /** Прапорець, що вказує, чи були згенеровані значення. */
    private boolean isGenerated = false;

    /**
     * Конструктор контролера. Створює інтерфейс та ініціалізує елементи.
     */
    public MainController() {
        createUI();
    }

    /**
     * @return кореневий вузол (HBox), який містить усі елементи інтерфейсу.
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * Створює інтерфейс: таблиця розташована ліворуч, а панель управління – праворуч.
     * У правій панелі спочатку розташовується ComboBox, а під ним – кнопка "Виконати".
     */
    private void createUI() {
        // Головний контейнер - HBox
        root = new HBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.TOP_LEFT);

        // 1. Створюємо таблицю (ліворуч)
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefHeight(300); // висота таблиці
        tableView.setPrefWidth(400);  // ширина таблиці для двох стовпців

        // Стовпець X
        TableColumn<DataPair, Double> colX = new TableColumn<>("X");
        colX.setCellValueFactory(new PropertyValueFactory<>("x"));
        colX.setCellFactory(tc -> new TableCell<DataPair, Double>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty || value == null ? null : String.format("%.2f", value));
            }
        });

        // Стовпець Y
        TableColumn<DataPair, Double> colY = new TableColumn<>("Y");
        colY.setCellValueFactory(new PropertyValueFactory<>("y"));
        colY.setCellFactory(tc -> new TableCell<DataPair, Double>() {
            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                setText(empty || value == null ? null : String.format("%.2f", value));
            }
        });

        tableView.getColumns().addAll(colX, colY);
        root.getChildren().add(tableView);

        // 2. Панель управління (праворуч)
        VBox rightPanel = new VBox(10);
        // Вирівнюємо елементи по центру в межах панелі
        rightPanel.setAlignment(Pos.TOP_CENTER);

        // Спочатку додаємо ComboBox
        comboBoxCommands = new ComboBox<>();
        comboBoxCommands.getItems().addAll(Command.values());
        comboBoxCommands.setPromptText("Оберіть команду...");

        // Потім додаємо кнопку "Виконати"
        btnExecute = new Button("Виконати");
        btnExecute.setOnAction(e -> executeSelectedCommand());

        // Додаємо елементи до правої панелі (ComboBox зверху, кнопка під ним)
        rightPanel.getChildren().addAll(comboBoxCommands, btnExecute);

        // Додаємо праву панель до головного контейнера
        root.getChildren().add(rightPanel);
    }

    /**
     * Обробник кнопки "Виконати": визначає обрану команду і викликає відповідний метод.
     */
    private void executeSelectedCommand() {
        Command selected = comboBoxCommands.getValue();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Оберіть команду зі списку!");
            return;
        }
        switch (selected) {
            case CREATE_TABLE:
                createTable();
                break;
            case GENERATE_VALUES:
                generateValues();
                break;
            case SAVE_VALUES:
                saveValues();
                break;
            case VIEW_VALUES:
                viewValues();
                break;
            case CHANGE_Y:
                changeYColumn();
                break;
            case SHOW_STATS:
                showStatistics();
                break;
            case EXIT:
                exitApp();
                break;
        }
    }

    /**
     * (1) Створює таблицю з 10 рядками (X=0, Y=0).
     */
    private void createTable() {
        dataList.clear();
        for (int i = 0; i < 10; i++) {
            dataList.add(new DataPair(0, 0));
        }
        tableView.setItems(dataList);
        isGenerated = false;
        showAlert(Alert.AlertType.INFORMATION, "Таблицю створено з 10 пар (0,0).");
    }

    /**
     * (2) Генерує випадкові значення X та Y для всієї таблиці у діапазоні [-10, 10],
     *    округлюючи їх до двох знаків після коми.
     */
    private void generateValues() {
        if (dataList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Спочатку створіть таблицю!");
            return;
        }
        Random random = new Random();
        for (DataPair pair : dataList) {
            double xVal = Math.round((-10 + (20 * random.nextDouble())) * 100) / 100.0;
            double yVal = Math.round((-10 + (20 * random.nextDouble())) * 100) / 100.0;
            pair.setX(xVal);
            pair.setY(yVal);
        }
        tableView.refresh();
        isGenerated = true;
        showAlert(Alert.AlertType.INFORMATION,
                "Значення (X, Y) згенеровано в діапазоні [-10, 10].");
    }

    /**
     * (3) Зберігає поточний список у файл data.ser.
     *    Якщо дані не згенеровано, виводиться попередження.
     */
    private void saveValues() {
        if (!isGenerated) {
            showAlert(Alert.AlertType.WARNING, "Дані не згенеровано!");
            return;
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(new ArrayList<>(dataList));
            showAlert(Alert.AlertType.INFORMATION, "Дані збережено у файл: " + fileName);
        } catch (IOException ex) {
            showAlert(Alert.AlertType.ERROR, "Помилка збереження: " + ex.getMessage());
        }
    }

    /**
     * (4) Завантажує дані з файлу data.ser і відображає їх у таблиці.
     *    Якщо файл не існує, виводиться попередження.
     */
    private void viewValues() {
        File file = new File(fileName);
        if (!file.exists()) {
            showAlert(Alert.AlertType.WARNING, "Файл з даними не знайдено!");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList) {
                @SuppressWarnings("unchecked")
                ArrayList<DataPair> loadedData = (ArrayList<DataPair>) obj;
                dataList.clear();
                dataList.addAll(loadedData);
                tableView.setItems(dataList);
                isGenerated = true;
                showAlert(Alert.AlertType.INFORMATION, "Дані успішно завантажено з файлу.");
            }
        } catch (IOException | ClassNotFoundException ex) {
            showAlert(Alert.AlertType.ERROR, "Помилка завантаження: " + ex.getMessage());
        }
    }

    /**
     * (5) Змінює значення стовпця Y шляхом випадкового масштабування (0.5...2.0),
     *    округлюючи результат до двох знаків після коми.
     */
    private void changeYColumn() {
        if (!isGenerated) {
            showAlert(Alert.AlertType.WARNING, "Дані не згенеровано!");
            return;
        }
        Random random = new Random();
        double scale = 0.5 + (1.5 * random.nextDouble());
        for (DataPair pair : dataList) {
            double newY = Math.round(pair.getY() * scale * 100) / 100.0;
            pair.setY(newY);
        }
        tableView.refresh();
        showAlert(Alert.AlertType.INFORMATION,
                "Значення стовпця Y змінено за коефіцієнтом: " + String.format("%.2f", scale));
    }

    /**
     * (6) Виводить статистику:
     *    - Середнє X
     *    - Середнє Y
     *    - Мінімальне позитивне Y
     *    - Максимальне негативне Y
     *    Якщо дані не згенеровано, виводиться попередження.
     */
    private void showStatistics() {
        if (!isGenerated) {
            showAlert(Alert.AlertType.WARNING, "Дані не згенеровано!");
            return;
        }
        double sumX = 0, sumY = 0;
        int count = dataList.size();
        Optional<Double> minPositive = Optional.empty();
        Optional<Double> maxNegative = Optional.empty();

        for (DataPair pair : dataList) {
            sumX += pair.getX();
            sumY += pair.getY();
            double yVal = pair.getY();
            if (yVal > 0 && (!minPositive.isPresent() || yVal < minPositive.get())) {
                minPositive = Optional.of(yVal);
            }
            if (yVal < 0 && (!maxNegative.isPresent() || yVal > maxNegative.get())) {
                maxNegative = Optional.of(yVal);
            }
        }

        double avgX = sumX / count;
        double avgY = sumY / count;

        StringBuilder stats = new StringBuilder();
        stats.append(String.format("Середнє значення X: %.2f\n", avgX));
        stats.append(String.format("Середнє значення Y: %.2f\n", avgY));
        stats.append(minPositive.isPresent() ? String.format("Мінімальне позитивне Y: %.2f\n", minPositive.get())
                : "Немає позитивних значень Y\n");
        stats.append(maxNegative.isPresent() ? String.format("Максимальне негативне Y: %.2f\n", maxNegative.get())
                : "Немає негативних значень Y\n");

        showAlert(Alert.AlertType.INFORMATION, stats.toString());
    }

    /**
     * (7) Завершує роботу програми.
     */
    private void exitApp() {
        System.exit(0);
    }

    /**
     * Відображає діалогове вікно Alert із заданим типом та повідомленням.
     * @param type тип повідомлення (INFORMATION, WARNING, ERROR)
     * @param message текст повідомлення
     */
    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Повідомлення");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

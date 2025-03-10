
# 🚀 ParallelDataProcessor — Двовимірний аналіз даних

## 📝 Постановка задачі

**Вам потрібно виконати наступне:**

1. 🔄 **Продемонструвати можливість паралельної обробки елементів колекції**:
   - 🔍 Пошук мінімуму та максимуму
   - 📏 Обчислення середнього значення
   - 🔎 Відбір за критерієм
   - 📊 Статистична обробка даних

2. 🧵 **Управління чергою завдань (команд) реалізувати за допомогою шаблону Worker Thread**:
   - 📋 Створення черги команд
   - 🔄 Асинхронне виконання завдань
   - 📡 Моніторинг прогресу виконання

Програма має забезпечувати ефективну обробку даних з використанням паралельних обчислень та надавати користувачеві зручний інтерфейс для взаємодії.

## ⚙️ Функціональність

<div align="center">

| 🔑 Команда | 🔤 Символ | 📋 Опис |
|:----------:|:--------:|:-------|
| 👁️ Переглянути дані | `п` | Відображення поточного набору даних у вигляді таблиці |
| 🔄 Генерувати дані | `г` | Створення нового набору випадкових двовимірних точок |
| 📝 Змінити значення | `з` | Редагування Y-координат елементів з коефіцієнтом |
| 📊 Аналізувати дані | `а` | Запуск комплексного паралельного аналізу даних |
| 🚪 Вихід | `в` | Завершення роботи програми |

</div>

## 🧵 Паралельна обробка даних

У програмі реалізовано паралельну обробку даних з використанням окремих потоків для виконання різних обчислювальних задач:

```mermaid
graph TD
    A[Головний потік інтерфейсу] --> B[Черга команд]
    B --> C[Потік Worker Thread]
    C --> D1[Обчислення максимуму]
    C --> D2[Обчислення середнього]
    C --> D3[Обчислення мін/макс]
    C --> D4[Фільтрація за критерієм]
    
    D1 --> E[Результати аналізу]
    D2 --> E
    D3 --> E
    D4 --> E
    
    E --> F[Відображення користувачу]
```

### 🔄 Механізм паралельних обчислень

Програма використовує **три незалежні черги завдань**:

1. **Черга 1**: Обробка завдань мінімуму/максимуму
2. **Черга 2**: Обробка завдань максимального значення
3. **Черга 3**: Обробка завдань середнього значення

<div align="center">

| ⚡ Тип операції | 🔄 Алгоритм | ⏱️ Прогрес моніторингу |
|----------------|------------|----------------------|
| Пошук максимуму | Послідовне порівняння | Через кожні 33% елементів |
| Пошук мін/макс | Розподіл на підзадачі | Через кожні 20% елементів |
| Обчислення середнього | Паралельне підсумовування | Через кожні 50% елементів |
| Пошук екстремумів X/Y | 10 паралельних потоків | По завершенню всіх потоків |

</div>

## 🔄 Worker Thread шаблон

У проекті реалізовано шаблон проектування **Worker Thread** через класи:

<div align="center">

```
┌─────────────────┐      ┌─────────────────┐      ┌─────────────────┐
│   TaskQueue     │      │CommandTaskQueue  │      │   TaskWorker    │
│    interface    │◄─────│  implementation  │◄─────│  inner class    │
└─────────────────┘      └─────────────────┘      └─────────────────┘
        ▲                        ▲
        │                        │
┌─────────────────┐      ┌─────────────────┐
│     Command     │      │CommandImplement │
│    interface    │◄─────│     ations      │
└─────────────────┘      └─────────────────┘
```

</div>

Схема роботи Worker Thread:

1. ➕ **Додавання команди**: Команда додається до черги `enqueue()`
2. 🔄 **Обробка потоком**: Робочий потік викликає `dequeue()` і отримує команду
3. ▶️ **Виконання**: Потік виконує метод `execute()` команди
4. 📈 **Моніторинг**: Основний потік відстежує прогрес через `getProgressPercentage()`

## 📸 Скріншоти виконання

### 📋 Головне меню
![Головне меню](https://example.com/main_menu_screenshot.png)

### 👁️ Переглянути дані
![Перегляд даних](https://example.com/view_data_screenshot.png)

### 🔄 Генерувати нові дані
![Генерація даних](https://example.com/generate_data_screenshot.png)

### 📝 Змінити значення Y
![Зміна значень](https://example.com/change_y_screenshot.png)

### 📊 Аналізувати дані
![Аналіз даних](https://example.com/analyze_data_screenshot.png)

### 🚪 Вихід - завершити програму
![Вихід](https://example.com/exit_screenshot.png)

## 🧪 Виконання тесту
![Виконання тесту](https://example.com/test_execution_screenshot.png)

## 🌟 Шаблони проектування

У проекті використано наступні шаблони проектування:

```mermaid
graph TD
    A[Шаблони проектування] --> B[Command Pattern]
    A --> C[Worker Thread Pattern]
    A --> D[Factory Method]
    
    B --> B1[Інкапсуляція запитів як об'єктів]
    B --> B2[Підтримка паралельного виконання]
    
    C --> C1[Асинхронна обробка завдань]
    C --> C2[Моніторинг прогресу операцій]
    
    D --> D1[Створення різних команд аналізу]
```

### Діаграма класів Worker Thread

```mermaid
classDiagram
    class TaskQueue {
        <<interface>>
        +enqueue(Command)
        +dequeue() Command
    }
    
    class CommandTaskQueue {
        -taskQueue Vector~Command~
        -isWaiting boolean
        -isShutdown boolean
        +shutdownQueue()
        +enqueue(Command)
        +dequeue() Command
    }
    
    class TaskWorker {
        +run()
    }
    
    class Command {
        <<interface>>
        +execute()
    }
    
    class CalculateMaxCommand {
        -maxIndex int
        -progressPercentage int
        -resultView ViewResult
        +getMaxIndex() int
        +isRunning() boolean
        +getProgressPercentage() int
        +execute()
    }
    
    TaskQueue <|-- CommandTaskQueue
    CommandTaskQueue *-- TaskWorker
    Command <|-- CalculateMaxCommand
    Command <|-- CalculateAverageCommand
    Command <|-- CalculateMinMaxCommand
    CommandTaskQueue o-- Command
```

---

<div align="center">
  
  *Розроблено з використанням принципів ООП та паттернів проектування*
  
  ![Java](https://img.shields.io/badge/Made%20with-Java-red?style=flat-square&logo=java)
  ![OOP](https://img.shields.io/badge/OOP-Principles-blue?style=flat-square)
  ![Threading](https://img.shields.io/badge/Multi-Threading-purple?style=flat-square)
  
</div>

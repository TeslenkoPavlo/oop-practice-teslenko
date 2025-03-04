# 🚀✨ JavaSerializeXplorer: Дослідження серіалізації та структур даних в Java
[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://github.com/codespaces/new?hide_repo_select=true&ref=task-2-classes-and-objects-%2804.03.25%29&repo=941975572)

## 📋 Про проект

**JavaSerializeXplorer** - це комплексний навчальний проект, що демонструє потужні можливості обробки даних та збереження стану об'єктів у Java через механізми серіалізації. Проект створений для розуміння основних концепцій серіалізації, transient полів, та обробки структур даних.

## 🌟 Структура проекту

```
src/
├── main.java                    # 🎮 Головний клас з інтерактивним меню вибору модулів
├── srcFirst/                    # 🔰 Модуль базової серіалізації
│   ├── domain/                  # 📊 Доменна модель
│   │   ├── Exercise.java        # 📝 Базовий серіалізований клас з математичними операціями
│   │   └── ExerciseSolver.java  # 🛠️ Клас-менеджер для роботи з Exercise (агрегація)
│   └── test/                    # 🧪 Тестовий модуль
│       └── TestExercise.java    # ✅ Демонстрація створення, обчислення та серіалізації
├── srcSecond/                   # 🔑 Модуль роботи з transient полями
│   ├── domain/
│   │   └── ExerciseSecond.java  # 🔐 Демонстрація впливу transient на серіалізацію
│   └── test/
│       └── TestExerciseSecond.java  # 🎭 Інтерактивна демонстрація transient полів
├── srcThird/                    # 📊 Модуль обробки масивів з серіалізацією
│   ├── domain/
│   │   └── ExerciseThird.java   # 📈 Операції над масивами з можливістю збереження стану
│   └── test/
│       └── TestExerciseThird.java  # 🌈 Тестування з кольоровим виводом результатів
└── srcIndividual/               # 🧮 Модуль перетворення систем числення
    ├── domain/
    │   └── ExerciseIndividual.java  # 🔢 Конвертація десяткових чисел у 16-річну та 8-річну системи
    └── test/
        └── TestExerciseIndividual.java  # 📊 Візуалізація перетворень систем числення
```

## 🔧 Технічні можливості

### 📦 Модуль базової серіалізації (srcFirst)

Модуль демонструє основні концепції серіалізації об'єктів у Java:

- **🧩 Клас Exercise:**
  - ✅ Реалізує інтерфейс `Serializable`
  - 💾 Зберігає математичні параметри та результати обчислень
  - 📤 Серіалізує весь стан об'єкта у байтовий потік

- **🛠️ Клас ExerciseSolver:**
  - 🔗 Демонструє патерн агрегації (має об'єкт Exercise, але не успадковує його)
  - 🎮 Керує процесами серіалізації та десеріалізації Exercise
  - 🧮 Забезпечує зручний API для математичних обчислень

> 💡 Цей модуль ідеально ілюструє концепцію збереження стану об'єкта та його відновлення для подальшого використання.

```java
// 📝 Приклад використання:
Exercise exercise = new Exercise(3.0, 4.0);
ExerciseSolver solver = new ExerciseSolver(exercise);
double result = solver.solveExercise(); // 🧮 Обчислення результату
solver.saveExerciseToFile("mydata.ser"); // 💾 Серіалізація
```

### 🔑 Модуль transient полів (srcSecond)

Цей модуль досліджує нюанси використання ключового слова `transient` у серіалізації:

- **🔐 Клас ExerciseSecond:**
  - 📊 Демонструє, які поля зберігаються при серіалізації, а які ні
  - 🛡️ Показує, як чутливі дані (паролі) можуть бути захищені від збереження
  - 🔄 Ілюструє, як тимчасові дані скидаються після десеріалізації

- **🎭 Інтерактивний тест:**
  - ⌨️ Дозволяє користувачу вводити дані для експериментів
  - 👁️‍🗨️ Наочно показує різницю між звичайними та transient полями після десеріалізації

> 💡 Цей модуль особливо цінний для розуміння безпеки даних та оптимізації пам'яті при серіалізації.

### 📊 Модуль аналізу масивів (srcThird)

Модуль фокусується на обробці числових масивів з можливістю збереження їхнього стану:

<details>
<summary>📈 <b>Клас ExerciseThird</b> – розгорніть для деталей</summary>

- 🧮 Реалізує різноманітні алгоритми обробки масивів:
  - ➕ Сума елементів
  - ➗ Середнє значення
  - ⬇️ Мінімальне значення
  - ⬆️ Максимальне значення
- 💾 Підтримує серіалізацію та десеріалізацію масивів
- 🛠️ Забезпечує зручний API для аналізу числових даних

</details>

- **🌈 Візуалізація результатів:**
  - 🎨 Використовує ANSI-кольори для наочного представлення результатів тестів
  - 📤 Демонструє процес серіалізації та десеріалізації масивів
  - 🔍 Забезпечує візуальне порівняння оригінальних та відновлених даних

> ⭐ Цей модуль особливо корисний для розуміння, як працювати з колекціями даних у контексті серіалізації.

### 🧮 Модуль перетворення систем числення (srcIndividual)

Індивідуальний модуль для роботи з різними системами числення:

| Система числення | Символи | Приклад перетворення |
|:----------------:|:-------:|:--------------------:|
| **Десяткова**    | 0-9     | **255**              |
| **Шістнадцяткова** | 0-9, A-F | **FF**               |
| **Вісімкова**    | 0-7     | **377**              |

- **🔢 Клас ExerciseIndividual:**
  - 🔄 Конвертує десяткові числа у шістнадцяткові та вісімкові представлення
  - 🔢 Підраховує кількість цифр у різних представленнях числа
  - 🔍 Забезпечує детальний аналіз структури чисел у різних системах числення

- **📊 Інтерактивна демонстрація:**
  - 📈 Візуалізує числа в різних форматах
  - 📏 Надає порівняльний аналіз довжини запису в різних системах числення

> 🌟 Цей модуль ідеально підходить для глибшого розуміння внутрішньої структури числових даних.

---

### 👨‍💻 Автор
> **Тесленко Павло** ✨

![Роздільник](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

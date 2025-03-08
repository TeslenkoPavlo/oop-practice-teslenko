# 🚀✨ JavaSerializeXplorer: Дослідження серіалізації та структур даних в Java
[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://github.com/codespaces/new?hide_repo_select=true&ref=task-2-classes-and-objects-%2804.03.25%29&repo=941975572)

## 📋 Постановка задачі

Вам потрібно виконати наступне:

1. **💾 Базова серіалізація:**
   - Розробити клас, що серіалізується, для зберігання параметрів і результатів обчислень.
   - Використовуючи агрегування, розробити клас для знаходження рішення задачі.

2. **🔐 Робота з transient полями:**
   - Розробити клас для демонстрації в діалоговому режимі збереження та відновлення стану об'єкта, використовуючи серіалізацію.
   - Показати особливості використання transient полів.

3. **🧪 Тестування:**
   - Розробити клас для тестування коректності результатів обчислень та серіалізації/десеріалізації.
   - Використовувати докладні коментарі для автоматичної генерації документації засобами javadoc.

4. **🔢 Індивідуальне завдання:**
   - Виконати індивідуальне завдання згідно номеру в списку (перетворення систем числення).

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

## 📸 Скріншоти виконання програм

![Роздільник](https://i.imgur.com/waxVImv.png)

### 🎮 Головне меню програми

<details open>
<summary>📊 <b>Інтерактивне меню вибору</b></summary>

![Головне меню](https://github.com/TeslenkoPavlo/oop-practice-teslenko/blob/task-2-classes-and-objects-(04.03.25)/img/photo5.png?raw=true)

*👆 Користувацький інтерфейс з кольоровим меню для вибору різних модулів програми.*
</details>

### 🔍 Результати роботи модулів

<details>
<summary>📊 <b>1️⃣ Модуль srcFirst</b> - Базова серіалізація об'єктів</summary>

![Результати модуля srcFirst](https://github.com/TeslenkoPavlo/oop-practice-teslenko/blob/task-2-classes-and-objects-(04.03.25)/img/photo2.png?raw=true)

*👆 Демонстрація роботи з об'єктами Exercise та їх серіалізації/десеріалізації.*
</details>

<details>
<summary>🔐 <b>2️⃣ Модуль srcSecond</b> - Робота з transient полями</summary>

![Результати модуля srcSecond](https://github.com/TeslenkoPavlo/oop-practice-teslenko/blob/task-2-classes-and-objects-(04.03.25)/img/photo1.png?raw=true)

*👆 Ілюстрація впливу ключового слова transient на серіалізацію полів.*
</details>

<details>
<summary>📈 <b>3️⃣ Модуль srcThird</b> - Аналіз масивів з серіалізацією</summary>

![Результати модуля srcThird](https://github.com/TeslenkoPavlo/oop-practice-teslenko/blob/task-2-classes-and-objects-(04.03.25)/img/photo3.png?raw=true)

*👆 Наочне представлення роботи з масивами та результатів їх обробки.*
</details>

<details>
<summary>🧮 <b>4️⃣ Модуль srcIndividual</b> - Перетворення систем числення</summary>

![Результати модуля srcIndividual](https://github.com/TeslenkoPavlo/oop-practice-teslenko/blob/task-2-classes-and-objects-(04.03.25)/img/photo4.png?raw=true)

*👆 Візуалізація результатів перетворення чисел між різними системами числення.*
</details>

---

### 👨‍💻 Автор
> **Тесленко Павло** ✨

![Роздільник](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

---

<div align="center">
  
  *Розроблено з використанням шаблонів проектування та принципів ООП*
  
  ![Stars](https://img.shields.io/badge/⭐⭐⭐⭐⭐-5%20з%205-yellow?style=flat-square)
  ![License](https://img.shields.io/badge/Ліцензія-MIT-blue?style=flat-square)
  
</div>

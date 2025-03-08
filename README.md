
# 🏗️ Патерн Factory Method у ООП - Проект з Використанням Шаблону Проектування
[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://github.com/codespaces/new?hide_repo_select=true&ref=task-3-inheritance-(05.03.25)&repo=941975572)

## 🎯 Постановка задачі

Вам потрібно виконати наступне:

1. **🔄 Основа проекту**: Як основа використовувати вихідний текст проекту попередньої лабораторної роботи. Забезпечити розміщення результатів обчислень у колекції з можливістю збереження/відновлення.

2. **🏭 Шаблон Factory Method**: Використовуючи шаблон проектування Factory Method (Virtual Constructor), розробити ієрархію, що передбачає розширення за рахунок додавання нових відображуваних класів.

3. **📚 Розширення ієрархії**: Розширити ієрархію інтерфейсом "фабрикованих" об'єктів, що представляє набір методів для відображення результатів обчислень.

4. **📝 Реалізація методів**: Реалізувати ці методи виведення результатів у текстовому вигляді.

5. **🛠️ Розробка інтерфейсу**: Розробити та реалізувати інтерфейс для "фабрикуючого" методу.

## 🏗️ Структура проекту

```bash
src/
├── domain/
│   ├── Main.java                  # Головний клас програми
│   ├── Displayable.java           # Інтерфейс для відображення
│   ├── DisplayableFactory.java    # Інтерфейс фабрики
│   ├── ComputationDisplay.java    # Реалізація інтерфейсу Displayable
│   ├── ComputationResult.java     # Клас для зберігання результатів обчислень
│   └── ComputationDisplayFactory.java  # Реалізація фабрики
└── test/
    └── MainTest.java              # Тести для перевірки роботи програми
```

## 🧩 Реалізація патерну Factory Method

У цьому проекті реалізовано патерн проектування Factory Method, який дозволяє:
- 🔹 Створювати об'єкти без прив'язки до конкретних класів
- 🔹 Легко розширювати систему новими типами об'єктів
- 🔹 Відокремити код створення об'єктів від їх використання

### Ключові компоненти:
- **DisplayableFactory** - інтерфейс Creator
- **ComputationDisplayFactory** - конкретна реалізація Creator
- **Displayable** - інтерфейс Product
- **ComputationDisplay** - конкретна реалізація Product

## 📱 Приклад використання програми

### 🚀 Запуск програми
![Запуск програми](https://github.com/TeslenkoPavlo/oop-practice-teslenko/blob/task-3-inheritance-(05.03.25)/img/photo1.png?raw=true)

### 👁️ Команда 'п' (показати)
![Команда показати](https://github.com/TeslenkoPavlo/oop-practice-teslenko/blob/task-3-inheritance-(05.03.25)/img/photo2.png?raw=true)

### 🔄 Команда 'г' (генерувати)
![Команда генерувати](https://github.com/TeslenkoPavlo/oop-practice-teslenko/blob/task-3-inheritance-(05.03.25)/img/photo3.png?raw=true)

### 💾 Команда 'з' (зберегти)
![Команда зберегти](https://github.com/TeslenkoPavlo/oop-practice-teslenko/blob/task-3-inheritance-(05.03.25)/img/photo4.png?raw=true)

### 📤 Команда 'о' (оновити)
![Команда відновити](https://github.com/TeslenkoPavlo/oop-practice-teslenko/blob/task-3-inheritance-(05.03.25)/img/photo5.png?raw=true)

### 🚪 Команда 'в' (вихід)
![Команда вихід](https://github.com/TeslenkoPavlo/oop-practice-teslenko/blob/task-3-inheritance-(05.03.25)/img/photo6.png?raw=true)

## 🔍 Принципи ООП в проекті

### 📌 Інкапсуляція
> 💡 У проекті використовується інкапсуляція для приховування деталей реалізації об'єктів і запобігання прямого доступу до даних.
```java
private double x;
private double y;
```

### 📌 Поліморфізм
> 💡 Використання інтерфейсів дозволяє звертатися до різних об'єктів через єдиний інтерфейс.
```java
Displayable display = factory.createDisplayable();
```

### 📌 Абстракція
> 💡 Використання інтерфейсів для абстрагування від конкретних реалізацій.
```java
public interface Displayable {
    void initialize();
    void display();
}
```

## 🧪 Тестування

Тестування проекту здійснюється за допомогою JUnit. У класі `MainTest.java` реалізовано тести для перевірки:
- ✅ Коректності обчислень
- ✅ Серіалізації та десеріалізації даних
- ✅ Роботи фабричного методу

### Приклад тесту:
```java
@Test
public void testComputationResults() {
    ComputationResult result = new ComputationResult(5.0, 25.0);
    assertEquals(5.0, result.getX(), 0.001);
    assertEquals(25.0, result.getY(), 0.001);
}
```

## 📈 Перспективи розвитку

- 🌟 **Розширення інтерфейсу користувача** - додавання графічного інтерфейсу з використанням JavaFX
- 🌟 **Додавання нових типів обчислень** - розширення системи за рахунок нових класів
- 🌟 **Інтеграція з базами даних** - зберігання результатів у реляційній або NoSQL БД
- 🌟 **Впровадження інших патернів проектування** - поєднання з іншими шаблонами для покращення архітектури

## 📊 Висновки

У цьому проекті було успішно реалізовано патерн Factory Method для створення об'єктів, що відображають результати обчислень. Використання цього патерну дозволяє легко розширювати функціональність програми додаванням нових класів без зміни існуючого коду.

---

<div align="center">
  
  *Розроблено з використанням шаблонів проектування та принципів ООП*
  
  ![Stars](https://img.shields.io/badge/⭐⭐⭐⭐⭐-5%20з%205-yellow?style=flat-square)
  ![License](https://img.shields.io/badge/Ліцензія-MIT-blue?style=flat-square)
  
</div>

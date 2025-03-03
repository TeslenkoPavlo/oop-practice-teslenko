#!/bin/bash
# Цей скрипт – приклад обчислення оцінки за 5‑балльною системою.
# Для демонстрації використовуємо змінні середовища (ви можете інтегрувати аналіз XML‑звітів, якщо потрібно).

# Припустимо, що тести пройшли – у реальному проєкті ви можете зчитувати результат із файлів.
# Змінна TESTS_STATUS має значення "pass" або "fail".
# Змінна CHECKSTYLE_WARNINGS містить кількість попереджень (ви можете отримати її шляхом парсингу Checkstyle звіту).

# Для демонстрації встановимо значення за замовчуванням:
TESTS_STATUS=${TESTS_STATUS:-pass}
CHECKSTYLE_WARNINGS=${CHECKSTYLE_WARNINGS:-3}

if [ "$TESTS_STATUS" = "pass" ]; then
  if [ "$CHECKSTYLE_WARNINGS" -eq 0 ]; then
    grade=5
  elif [ "$CHECKSTYLE_WARNINGS" -le 5 ]; then
    grade=4
  elif [ "$CHECKSTYLE_WARNINGS" -le 10 ]; then
    grade=3
  else
    grade=2
  fi
else
  grade=1
fi

echo "Оцінка: $grade"
# Для подальшого використання в інших кроках (наприклад, для коментарів у PR)
echo "grade=$grade" >> "$GITHUB_OUTPUT"

#!/bin/bash
# Отримуємо статус тестів із середовища (expected values: "success" або "failure")
TESTS_STATUS=${TESTS_STATUS:-failure}
CHECKSTYLE_WARNINGS=${CHECKSTYLE_WARNINGS:-0}

if [ "$TESTS_STATUS" = "success" ]; then
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
# Записуємо оцінку у GitHub Actions output, щоб її можна було використати в наступних кроках
echo "grade=$grade" >> "$GITHUB_OUTPUT"

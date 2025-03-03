#!/bin/bash
# Отримуємо статус тестів із середовища (expected: "success" або "failure")
TESTS_STATUS=${TESTS_STATUS:-failure}

if [ "$TESTS_STATUS" = "success" ]; then
    grade=5
else
    grade=1
fi

echo "Оцінка: $grade/5"
# Записуємо оцінку у GitHub Actions output, щоб її можна було використати в подальших кроках
echo "grade=$grade" >> "$GITHUB_OUTPUT"

#!/bin/bash

TESTS_STATUS=${TESTS_STATUS:-failure}

if [ "$TESTS_STATUS" = "success" ]; then
    grade=5
else
    grade=1
fi

echo "Оцінка: $grade/5"

echo "::notice title=Оцінка::Оцінка: $grade/5"

echo "grade=$grade" >> "$GITHUB_OUTPUT"

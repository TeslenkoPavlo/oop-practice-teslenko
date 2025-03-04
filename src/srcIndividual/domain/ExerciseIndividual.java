
package srcIndividual.domain;

/**
 * Клас для виконання завдання по підрахунку кількості 16-річних та 8-річних цифр у заданому десятковому числі.
 * 
 * @author Тесленко Павло
 */
public class ExerciseIndividual {
    
    /**
     * Підраховує кількість 16-річних цифр (0-9, A-F) у десятковому числі після його переведення в 16-річну систему числення.
     * 
     * @param decimalNumber десяткове число для аналізу
     * @return кількість 16-річних цифр
     */
    public int countHexadecimalDigits(long decimalNumber) {
        // Якщо число від'ємне, працюємо з його модулем
        if (decimalNumber < 0) {
            decimalNumber = Math.abs(decimalNumber);
        }
        
        // Якщо число 0, то це одна 16-річна цифра
        if (decimalNumber == 0) {
            return 1;
        }
        
        // Конвертуємо число в 16-річну систему числення
        String hexadecimalString = Long.toHexString(decimalNumber);
        
        // Повертаємо довжину рядка, яка і є кількістю 16-річних цифр
        return hexadecimalString.length();
    }
    
    /**
     * Підраховує кількість 8-річних цифр (0-7) у десятковому числі після його переведення в 8-річну систему числення.
     * 
     * @param decimalNumber десяткове число для аналізу
     * @return кількість 8-річних цифр
     */
    public int countOctalDigits(long decimalNumber) {
        // Якщо число від'ємне, працюємо з його модулем
        if (decimalNumber < 0) {
            decimalNumber = Math.abs(decimalNumber);
        }
        
        // Якщо число 0, то це одна 8-річна цифра
        if (decimalNumber == 0) {
            return 1;
        }
        
        // Конвертуємо число в 8-річну систему числення
        String octalString = Long.toOctalString(decimalNumber);
        
        // Повертаємо довжину рядка, яка і є кількістю 8-річних цифр
        return octalString.length();
    }
    
    /**
     * Повертає результати підрахунку цифр у вигляді форматованого рядка.
     * 
     * @param decimalNumber десяткове число для аналізу
     * @return рядок з результатами підрахунку
     */
    public String getDigitCountInfo(long decimalNumber) {
        int hexCount = countHexadecimalDigits(decimalNumber);
        int octalCount = countOctalDigits(decimalNumber);
        
        return String.format("Для числа %d:\n" +
                "Кількість 16-річних цифр: %d\n" +
                "Кількість 8-річних цифр: %d", 
                decimalNumber, hexCount, octalCount);
    }
}

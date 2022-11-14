package hexlet.hw.pr4;

public class Example {

    public static void main(String[] args) {

        // Работа с методами строки
        // Класс из стандартной библиотеки.
        // Дока: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html


        String str = " abc ";
        // Определение длинны строки
        System.out.println(str.length()); // => 5

        // Приведение строки к верхнему регистру
        System.out.println(str.toUpperCase()); // => " ABC "

        // Проверка на то, начинается ли строка с определенной подстроки
        System.out.println(str.startsWith(" ")); // => true
        System.out.println(str.startsWith("a")); // => false

        // Удаление концевых пробелов
        System.out.println(str.trim()); // => "abc"

        // Получение подстроки из исходной строки
        String substring = str.substring(1, 3);
        System.out.printl(substring); // => "ab"

        // Замена всех подстрок в строке на указанную подстроку
        String newString = str.replace(" ", "@");
        System.out.printl(newString); // => "@abc@";

        // Форматирование строки
        // %s - применяется к любому значению, на выходе будет строка
        // %d - применяется к числу, на выходе будет десятичное число
        String output = String.format("%s = %d", "joe", 35);
        System.out.printl(output); // => "joe = 35";


        // Math.
        // Класс из стандартной библиотеки.
        // Дока: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html

        // Вычисление квадратного корня из числа
        System.out.println(Math.sqrt(4)); // => 2

        // Вычисление абсолютного значения числа
        // Абсолютное значение числа — это само число без знака перед ним
        System.out.println(Math.abs(-3)); // => 3

        // Тригонометрические функции
        System.out.println(Math.sin(0)); // => 0
        System.out.println(Math.cos(0)); // => 1
    }
}

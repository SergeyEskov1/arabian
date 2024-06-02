package arabian;

import java.util.Scanner;
import java.util.stream.IntStream;

class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа(арабских или римских): ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }

    public static  String parse(String expression) throws  Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String [] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть два операнта");
        oper = detectOperation(expression);
        if (oper == null) throw new Exception("Неподдерживаемая математическая операция");
        //если 2 числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            //  Конвертируем оба числа в арабские для вычесления действия
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        // Если оба числа арабские
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 =Integer.parseInt(operands[0]);
            num2 =Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else {
            throw new Exception("Число должны быть в одном формате");
        }
        if (num1 > 10 || num2 > 10 ) {
            throw  new Exception("Число должно быть от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);
        if (isRoman) {
            //если римское число получилось меньше либо равно нулю, генерирум ошибку
            if (arabian <= 0) {
                throw  new Exception("Римское число должно быть больше нуля");
            }
            //конвертируем результат операции из арабского в римский
            result = Roman.convertToRoman(arabian);
        } else {
            //Конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        //возвращаем результат
        return  result;
    }
    static  String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*") ) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }
    static int calc(int a, int b, String oper) {
        switch (oper) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            default:
                return a / b;
        }
    }
}

class Roman {
    static String[] romanArray = new String[] {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX","XX", "XXI","XXII","XXIII", "XXIV",
            "XXV","XXVI", "XXVII", "XXVIII", "XXIX","XXX", "XXXI", "XXXII", "XXXIII","XXXIV", "XXXV",
            "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII","XLIV", "XLV", "XLVI", "XLVII",
            "XLVIII","XLIX", "XLV", "XLVI", "XLVII", "XLVIII","XLIV", "XLV","XLVI", "XLVII", "XLVIII", "XLIX",
            "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX","LXXI", "LXXII",
            "LXXIII","LXXIV", "LXXV", "LXXVI","LXXVII", "LXXVIII", "LXXIX","LXXX", "LXXXI","LXXXII", "LXXXIII",
            "LXXXIV", "LXXXV","LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV",
            "XCV","XCVI", "XCVII", "XCVIII", "XCIX", "C",};

    public static boolean isRoman(String val) {
        return IntStream.range(0, romanArray.length).anyMatch(i -> val.equals(romanArray[i]));
    }
    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }
    public static String convertToRoman(int arabian) { return romanArray[arabian]; }
}
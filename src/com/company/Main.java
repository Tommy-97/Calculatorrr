import java.util.Scanner;

class Calculator {
    public static class Main {
        private static final char exitCharacter = '!';

        public static void main(String[] args) {
            DataReader reader = new DataReader(exitCharacter);
            while (true) {
                try {
                    reader.read();
                } catch (RuntimeException e) {
                    System.err.println(e.getMessage());
                    continue;
                }
                if (reader.isExitFlag()) {
                    System.out.println("В выражении пристутствует знак выхода: " + exitCharacter);
                    System.out.println("Завершение программы.");
                    break;
                }
                double result = Calculator1.calculate(reader.getVar1(), reader.getVar2(), reader.getOper());
                if (reader.flag)
                    System.out.println(DataReader.numberToRoman(result));
                else System.out.println(String.format("%.1f", result));
            }
        }
    }


    public static class DataReader {

        private int number1;
        private int number2;
        private char operation;
        private boolean exitFlag;
        boolean flag;
        private final char exitCharacter;

        public DataReader(char exitCharacter) {
            this.exitCharacter = exitCharacter;
        }


        public void read() {

            Integer[] arabic = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
            String[] roman = {"X", "IX", "VIII", "VII", "VI", "V", "IV", "III", "II", "I"};

            System.out.println("Введите выражение, состоящее из двух целых чисел от 0 до 10, знака операции и знака равно (напр. 2+2=): ");
            Scanner scanner = new Scanner(System.in);
            String text = scanner.nextLine();
            if (text.indexOf(exitCharacter) != -1) {
                exitFlag = true;
                return;
            }

            try {

                text = text.substring(0, text.length());
                String[] blocks = text.split("[+-/*]");

                // проверяем, входит ли строка blocks[0] в массив строк массива "roman"
                flag = false;
                for (int i = 0; i < roman.length; i++) {
                    if (roman[i].equals(blocks[0]) || roman[i].equals(blocks[1])) {
                        flag = true;
                    }
                }
                //flag = true, значит будем иметь дело с римскими обозначениями
                if (flag) {
                    number1 = romanToNumber(blocks[0]);
                    number2 = romanToNumber(blocks[1]);
                    operation = text.charAt(blocks[0].length());
                } else {
                    number1 = Integer.parseInt(blocks[0]);
                    operation = text.charAt(blocks[0].length());
                    number2 = Integer.parseInt(blocks[1]);
                }

                if ((number1 > 10 || number1 < 0) || (number2 > 10 || number2 < 0)) {
                    throw new IllegalArgumentException();
                }
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Неверный формат данных");
            }
        }

        private static int romanToNumber(String roman) {
            if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            } else {
                return -1;
            }
        }

        public double getVar1() {
            return number1;
        }

        public double getVar2() {
            return number2;
        }

        public char getOper() {
            return operation;
        }

        public boolean isExitFlag() {
            return exitFlag;
        }

        public static String numberToRoman(double number) {

            String[] arrE = {"", "I", "II", "III", "IV", "V", "IV", "VII", "VIII", "IX"};
            String[] arrD = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC","C"};
            int num1 =  (int)(number % 10);
            int num10 = (int) (number / 10);
            return arrD[num10] + "" + arrE[num1];
        }

    }

    protected static class Calculator1 {
        private Calculator1() {
        }

        public static double calculate(double number1, double number2, char operation) {
            double result = 0;
            switch (operation) {
                case '+':
                    result = number1 + number2;
                    break;
                case '-':
                    result = number1 - number2;
                    break;
                case '*':
                    result = number1 * number2;
                    break;
                case '/':
                    result = number1 / number2;
                    break;
                default:
                    throw new IllegalArgumentException("Не верный знак операции");
            }
            return result;
        }

    }

}
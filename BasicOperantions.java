/*
- Autor: Ryan Santos Fidelis
- Data 13/05/2025
- Atividade 04
- Basic Mathematical Operations
*/

public class BasicOperations {
    public static Integer basicMath(String op, int v1, int v2) {
        if (op.equals("+")) {
            return v1 + v2;
        } else if (op.equals("-")) {
            return v1 - v2;
        } else if (op.equals("*")) {
            return v1 * v2;
        } else if (op.equals("/")) {
            return v1 / v2;
        }
        return 0;
    }
  
    static class Calc {
        public static int calcular(String operacao, int num1, int num2) {
            switch(operacao) {
                case "+": return num1 + num2;
                case "-": return num1 - num2;
                case "*": return num1 * num2;
                case "/": return num1 / num2;
                default: return 0;
            }
        }
    }
  
    public static void main(String[] args) {
        System.out.println("5 + 3 = " + basicMath("+", 5, 3));
        System.out.println("5 - 3 = " + basicMath("-", 5, 3));
        System.out.println("5 * 3 = " + basicMath("*", 5, 3));
        System.out.println("6 / 3 = " + basicMath("/", 6, 3));
        
        System.out.println("10 + 2 = " + Calc.calcular("+", 10, 2));
        System.out.println("10 - 2 = " + Calc.calcular("-", 10, 2));
        System.out.println("10 * 2 = " + Calc.calcular("*", 10, 2));
        System.out.println("10 / 2 = " + Calc.calcular("/", 10, 2));
    }
}

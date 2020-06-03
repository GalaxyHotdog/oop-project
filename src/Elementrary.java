import java.util.Scanner;
import java.util.Stack;

public class Elementrary {
    public void calculate(String str) {
        if (str == null || str.length() == 0) {
            System.out.println("Error");
        }
        assert str != null;
        str = str.trim().replaceAll("\\s+", "");
        if (str.length() == 0) {
            System.out.println("Error");
        }
        Stack<Character> opStack = new Stack<>();
        Stack<Double> numStack = new Stack<>();
        int i = 0;
        while (i < str.length()) {
            if (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.') {
                StringBuilder beforePra = new StringBuilder();
                while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.')) {
                    beforePra.append(str.charAt(i));
                    i++;
                }
                numStack.push(Double.parseDouble(String.valueOf(beforePra)));
            } else {
                char op = str.charAt(i);
                if (opStack.isEmpty()) {
                    opStack.push(op);
                    i++;
                } else if (op == '+' || op == '-') {
                    char top = opStack.peek();
                    if (top == '(') {
                        opStack.push(op);
                        i++;
                    } else {
                        calculate(numStack, opStack);
                    }
                } else if (op == '*' || op == '/') {
                    char peek = opStack.peek();
                    if (peek == '(') {
                        opStack.push(op);
                        i++;
                    } else if (peek == '*' || peek == '/') {
                        calculate(numStack, opStack);
                    } else if (peek == '+' || peek == '-') {
                        opStack.push(op);
                        i++;
                    }
                } else if (op == '(') {
                    opStack.push(op);
                    i++;
                } else if (op == ')') {
                    while (opStack.peek() != '(') {
                        calculate(numStack, opStack);
                    }
                    opStack.pop();
                    i++;
                }
            }
        }
        while (!opStack.isEmpty()) {
            calculate(numStack, opStack);
        }
        System.out.println(numStack.peek());
    }

    private void calculate(Stack<Double> numStack, Stack<Character> opStack) {
        double num2 = numStack.pop();
        double num1 = numStack.pop();

        char op = opStack.pop();

        double ans = 0.0;

        switch (op) {
            case '+':
                ans = num1 + num2;
                break;
            case '-':
                ans = num1 - num2;
                break;
            case '*':
                ans = num1 * num2;
                break;
            case '/':
                ans = num1 / num2;
                break;
        }

        numStack.push(ans);
    }

    public static void main(String[] args) {
        Elementrary m = new Elementrary();
        Scanner scn = new Scanner(System.in);
        m.calculate(scn.nextLine());
    }
}
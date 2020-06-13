package TanHengjie;

import java.util.Scanner;
import java.util.Stack;

import static java.lang.Double.NaN;

public class Elementrary {
    public double calculate(String str, boolean isRadian) {
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

            }else if(i<str.length() && str.charAt(i)=='π'){
                numStack.push(Math.PI);
                i++;
            }
            else if(i<str.length() && str.charAt(i)=='e'){
                numStack.push(2.71828182846);
                i++;
            }
            else if(i<str.length() && str.charAt(i)=='^'){
                double tobe = numStack.pop();
                StringBuilder beforePra = new StringBuilder();
                StringBuilder pow = new StringBuilder();
                i++;
                int kcount = 1;
                if(str.charAt(i)=='('){
                    i++;
                    StringBuilder str2 = new StringBuilder();
                    while (i < str.length() && kcount>0) {
                        if(str.charAt(i)=='('){
                            kcount++;
                        }
                        else if(str.charAt(i)==')'){
                            kcount--;
                            if(kcount<=0){
                                break;
                            }
                        }
                        str2.append(str.charAt(i));
                        i++;
                    }
                    i++;
                    numStack.push(Math.pow(tobe,calculate(str2.toString(),isRadian)));
                }else {
                    while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.')) {
                        pow.append(str.charAt(i));
                        i++;
                    }
                    numStack.push(Math.pow(tobe, Double.parseDouble(String.valueOf(pow))));
                }
            }
            else if(str.charAt(i)=='!'){
                i++;
                double frac = numStack.pop();
                double push_val=1;
                if(frac%1!=0){
                    System.out.println("Fraction must be int.");
                    return NaN;
                }
                int frac_=(int)frac;
                for(int k=1;k<=frac;k++){
                    push_val*=(double)k;
                }
                numStack.push((double)push_val);
            }
            else if(str.charAt(i)=='l' && str.charAt(i+1)=='n'){
                i+=2;
                if(str.charAt(i)=='('){
                    i++;
                    int kcount = 1;
                    StringBuilder str2 = new StringBuilder();
                    while (i < str.length() && kcount>0) {
                        if(str.charAt(i)=='('){
                            kcount++;
                        }
                        else if(str.charAt(i)==')'){
                            kcount--;
                            if(kcount<=0){
                                break;
                            }
                        }
                        str2.append(str.charAt(i));
                        i++;
                    }
                    i++;
                    double val = calculate(str2.toString(),isRadian);
                    double push_val = Math.log(val);
                    if(Math.abs(push_val)<1E-10){
                        push_val=0;
                    }
                    numStack.push(push_val);
                }else {
                    StringBuilder beforePra = new StringBuilder();
                    while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == 'π' || str.charAt(i) == 'e')) {
                        if(str.charAt(i)=='π'){
                            beforePra.append(str.charAt(i));
                            i++;
                            break;
                        }
                        else if(str.charAt(i) == 'e'){
                            beforePra.append(str.charAt(i));
                            i++;
                            break;
                        }
                        beforePra.append(str.charAt(i));
                        i++;
                    }
                    double val = this.calculate(beforePra.toString(),isRadian);
                    double push_val = Math.log(val);
                    if(Math.abs(push_val)<1E-10){
                        push_val=0;
                    }
                    numStack.push(push_val);
                }
            }
            else if(str.charAt(i)=='l' && str.charAt(i+1)=='o' && str.charAt(i+2)=='g'){
                i+=3;
                if(str.charAt(i)=='('){
                    i++;
                    int kcount = 1;
                    StringBuilder str2 = new StringBuilder();
                    while (i < str.length() && kcount>0) {
                        if(str.charAt(i)=='('){
                            kcount++;
                        }
                        else if(str.charAt(i)==')'){
                            kcount--;
                            if(kcount<=0){
                                break;
                            }
                        }
                        str2.append(str.charAt(i));
                        i++;
                    }
                    i++;
                    double val = calculate(str2.toString(),isRadian);
                    double push_val = Math.log(val)/Math.log(10);
                    if(Math.abs(push_val)<1E-10){
                        push_val=0;
                    }
                    numStack.push(push_val);
                }else {
                    StringBuilder beforePra = new StringBuilder();
                    while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == 'π' || str.charAt(i) == 'e')) {
                        if(str.charAt(i)=='π'){
                            beforePra.append(str.charAt(i));
                            i++;
                            break;
                        }
                        else if(str.charAt(i) == 'e'){
                            beforePra.append(str.charAt(i));
                            i++;
                            break;
                        }
                        beforePra.append(str.charAt(i));
                        i++;
                    }
                    double val = this.calculate(beforePra.toString(),isRadian);
                    double push_val = Math.log(val)/Math.log(10);
                    if(Math.abs(push_val)<1E-10){
                        push_val=0;
                    }
                    numStack.push(push_val);
                }
            }
            else if(str.charAt(i)=='c' && str.charAt(i+1)=='o' && str.charAt(i+2)=='s'){
                i+=3;
                if(str.charAt(i)=='('){
                    i++;
                    int kcount = 1;
                    StringBuilder str2 = new StringBuilder();
                    while (i < str.length() && kcount>0) {
                        if(str.charAt(i)=='('){
                            kcount++;
                        }
                        else if(str.charAt(i)==')'){
                            kcount--;
                            if(kcount<=0){
                                break;
                            }
                        }
                        str2.append(str.charAt(i));
                        i++;
                    }
                    i++;
                    double val = calculate(str2.toString(),isRadian);
                    if (!isRadian) {
                        val = Math.toRadians(val);
                    }
                    double push_val = Math.cos(val);
                    if(Math.abs(push_val)<1E-10){
                        push_val=0;
                    }
                    numStack.push(push_val);
                }else {
                    StringBuilder beforePra = new StringBuilder();
                    while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == 'π' || str.charAt(i) == 'e')) {
                        if(str.charAt(i)=='π'){
                            beforePra.append(str.charAt(i));
                            i++;
                            break;
                        }
                        else if(str.charAt(i) == 'e'){
                            beforePra.append(str.charAt(i));
                            i++;
                            break;
                        }
                        beforePra.append(str.charAt(i));
                        i++;
                    }
                    double val = this.calculate(beforePra.toString(),isRadian);
                    if (!isRadian) {
                        val = Math.toRadians(val);
                    }
                    double push_val = Math.cos(val);
                    if(Math.abs(push_val)<1E-10){
                        push_val=0;
                    }
                    numStack.push(push_val);
                }
            }else if(str.charAt(i)=='s' && str.charAt(i+1)=='i' && str.charAt(i+2)=='n'){
                i+=3;
                if(str.charAt(i)=='('){
                    int kcount=1;
                    i++;
                    StringBuilder str2 = new StringBuilder();
                    while (i < str.length() && kcount>0) {
                        if(str.charAt(i)=='('){
                            kcount++;
                        }
                        else if(str.charAt(i)==')'){
                            kcount--;
                            if(kcount<=0){
                                break;
                            }
                        }
                        str2.append(str.charAt(i));
                        i++;
                    }
                    i++;
                    double val = calculate(str2.toString(),isRadian);
                    if (!isRadian) {
                        val = Math.toRadians(val);
                    }
                    double push_val = Math.sin(val);
                    if(Math.abs(push_val)<1E-10){
                        push_val=0;
                    }
                    numStack.push(push_val);
                }else {
                    StringBuilder beforePra = new StringBuilder();
                    while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == 'π' || str.charAt(i) == 'e')) {
                        if(str.charAt(i)=='π'){
                            beforePra.append(str.charAt(i));
                            i++;
                            break;
                        }
                        else if(str.charAt(i) == 'e'){
                            beforePra.append(str.charAt(i));
                            i++;
                            break;
                        }
                        beforePra.append(str.charAt(i));
                        i++;
                    }
                    double val = this.calculate(beforePra.toString(),isRadian);
                    if (!isRadian) {
                        val = Math.toRadians(val);
                    }
                    double push_val = Math.sin(val);
                    if(Math.abs(push_val)<1E-10){
                        push_val=0;
                    }
                    numStack.push(push_val);
                }
            }else if(str.charAt(i)=='t' && str.charAt(i+1)=='a' && str.charAt(i+2)=='n'){
                i+=3;
                if(str.charAt(i)=='('){
                    i++;
                    int kcount = 1;
                    StringBuilder str2 = new StringBuilder();
                    while (i < str.length() && kcount>0) {
                        if(str.charAt(i)=='('){
                            kcount++;
                        }
                        else if(str.charAt(i)==')'){
                            kcount--;
                            if(kcount<=0){
                                break;
                            }
                        }
                        str2.append(str.charAt(i));
                        i++;
                    }
                    i++;
                    double val = calculate(str2.toString(),isRadian);
                    if (!isRadian) {
                        val = Math.toRadians(val);
                    }
                    if(val%Math.toRadians(90)==0){
                        System.out.println("Tangent invalid.");
                        return NaN;
                    }
                    double push_val = Math.tan(val);
                    if(Math.abs(push_val)<1E-10){
                        push_val=0;
                    }
                    numStack.push(push_val);
                }else {
                    StringBuilder beforePra = new StringBuilder();
                    while (i < str.length() && (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == 'π' || str.charAt(i) == 'e')) {
                        if(str.charAt(i)=='π'){
                            beforePra.append(str.charAt(i));
                            i++;
                            break;
                        }
                        else if(str.charAt(i) == 'e'){
                            beforePra.append(str.charAt(i));
                            i++;
                            break;
                        }
                        beforePra.append(str.charAt(i));
                        i++;
                    }
                    double val = this.calculate(beforePra.toString(),isRadian);
                    if (!isRadian) {
                        val = Math.toRadians(val);
                    }
                    if(val%Math.toRadians(90)==0){
                        System.out.println("Tangent invalid.");
                        return NaN;
                    }
                    double push_val = Math.tan(val);
                    if(Math.abs(push_val)<1E-10){
                        push_val=0;
                    }
                    numStack.push(push_val);
                }
            }
            else {
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
        return  (numStack.peek());
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
        System.out.println(m.calculate(scn.nextLine(),false));
    }
}

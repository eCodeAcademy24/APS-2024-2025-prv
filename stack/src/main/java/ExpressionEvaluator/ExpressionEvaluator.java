package ExpressionEvaluator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExpressionEvaluator {

    public static int evaluateExpression(String str) {
        ArrayStack<Integer> stack = new ArrayStack<Integer>(str.length());
        String number = "";
        char operator = '+';

        str += '+';

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                number += str.charAt(i);
            } else {
                if (operator == '+') {
                    stack.push(Integer.parseInt(number));
                } else if (operator == '*') {
                    stack.push(Integer.parseInt(number) * stack.pop());
                }

                number = "";
                operator = str.charAt(i);
            }
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }

}
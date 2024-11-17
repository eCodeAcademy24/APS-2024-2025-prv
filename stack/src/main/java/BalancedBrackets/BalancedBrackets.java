package BalancedBrackets;

import java.util.Scanner;

public class BalancedBrackets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        int result = checkBalancedBrackets(input);
        System.out.println(result);
    }

    static int checkBalancedBrackets(String input) {
        ArrayStack<Character> stack = new ArrayStack<Character>(input.length());

        int roundCount = 0; // '('
        int curlyCount = 0; // '{'
        int squareCount = 0; // '['

        for (char ch : input.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
                roundCount++;
            } else if (ch == '{') {
                stack.push(ch);
                curlyCount++;
            } else if (ch == '[') {
                stack.push(ch);
                squareCount++;
            }

            else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') return 0;
            } else if (ch == '}') {
                if (stack.isEmpty() || stack.pop() != '{') return 0;
            } else if (ch == ']') {
                if (stack.isEmpty() || stack.pop() != '[') return 0;
            }
        }

        if (!stack.isEmpty()) return 0;
        if (roundCount == curlyCount && curlyCount == squareCount) return 1;

        return 0;
    }
}

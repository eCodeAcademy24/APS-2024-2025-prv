package BalancedBrackets;

import java.util.Scanner;

public class BalancedBrackets {

    static int checkBalancedBrackets(String input) {
        ArrayStack<Character> stack = new ArrayStack<Character>(input.length());

        int roundCount = 0;
        int curlyCount = 0;
        int squareCount = 0;

        for (char c : input.toCharArray()) {
            if (c == '(') {
                roundCount++;
                stack.push(c);
            } else if (c == '{') {
                curlyCount++;
                stack.push(c);
            } else if (c == '[') {
                squareCount++;
                stack.push(c);
            }

            else if(c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return 0;
                }
            } else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return 0;
                }
            } else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return 0;
                }
            }
        }

        if (!stack.isEmpty()) {
            return 0;
        }

        if (roundCount == curlyCount && curlyCount == squareCount) {
            return 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        int result = checkBalancedBrackets(input);
        System.out.println(result);
    }
}

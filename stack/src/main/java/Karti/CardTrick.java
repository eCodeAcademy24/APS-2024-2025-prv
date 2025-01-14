package Karti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CardTrick {

    public static int count(int N) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(51);
        ArrayStack<Integer> stack = new ArrayStack<>(51);

        for (int i = 1; i < 52; i++) {
            queue.enqueue(i);
        }

        int shuffleCounter = 0;
        while (queue.peek() != N) {
            for (int i = 0; i < 7; i++) {
                stack.push(queue.dequeue());
            }

            for(int i = 0; i < 7; i++) {
                queue.enqueue(stack.pop());
                queue.enqueue(queue.dequeue());
            }

            shuffleCounter++;
        }

        return shuffleCounter;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(count(Integer.parseInt(br.readLine())));
    }
}

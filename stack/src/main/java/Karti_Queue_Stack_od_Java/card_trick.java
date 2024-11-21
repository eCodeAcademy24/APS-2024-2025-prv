package Karti_Queue_Stack_od_Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class card_trick {
    public static int count(int N){
        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i < 52; i++) {
            queue.offer(i);
        }

        int shuffleCounter = 0;
        while (queue.peek() != N) {
            for (int i = 0; i < 7; i++) {
                stack.push(queue.poll());
            }

            for(int i = 0; i < 7; i++) {
                queue.offer(stack.pop());
                queue.offer(queue.poll());
            }

            shuffleCounter++;
        }

        return shuffleCounter;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in) );
        System.out.println(count(Integer.parseInt(br.readLine())));
    }

}

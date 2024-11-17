package Bukvi;

import java.io.IOException;
import java.util.Scanner;



public class StackBukvi {

    static int proveri_t_posle_s(char[] St) {
        ArrayStack<Integer> stack = new ArrayStack<>(St.length);
        int count = 0;

        for (int i = 0; i < St.length; i++) {
            if (St[i] == 'S') {
                if (!stack.isEmpty() && stack.peek() != count) {
                    return 0;
                }
                stack.push(count);
                count = 0;
            } else if (St[i] == 'T') {
                count++;
            }
        }

        return 1;
    }

    public static void main(String[] args) throws IOException {
        char[] niza = new char[100];

        Scanner f = new Scanner(System.in);
        String st = f.next();
        niza = st.toCharArray();

        int rez = proveri_t_posle_s(niza);
        System.out.println(rez);
    }
}
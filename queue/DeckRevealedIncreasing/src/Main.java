import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;

interface Queue<E> {
    // Elementi na redicata se objekti od proizvolen tip.
    // Metodi za pristap:
    public boolean isEmpty();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size();
    // Ja vrakja dolzinata na redicata.

    public E peek();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear();
    // Ja prazni redicata.

    public void enqueue(E x);
    // Go dodava x na kraj od redicata.

    public E dequeue();
    // Go otstranuva i vrakja pochetniot element na redicata.
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}


class LinkedQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...

    public LinkedQueue() {
        clear();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size() {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek() {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear() {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue(E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue() {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null) rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}

//Ви е даден шпил низа со цел број. Има шпил карти каде секоја карта има единствен цел број. Целиот број на i-та карта е deck[i].
//
//Можете да го сортирате шпилот по кој било редослед што сакате. Првично, сите карти почнуваат со лицето надолу (неоткриени) во еден шпил.
//
//Следниве чекори ќе ги правите постојано додека не се откријат сите картички:
//
//1. Земете ја горната карта на палубата, откријте ја и извадете ја од палубата.
//2. Ако сè уште има карти на палубата, тогаш ставете ја следната горна карта на палубата на дното на палубата.
//3. Ако сè уште има неоткриени картички, вратете се на чекор 1. Во спротивно, застанете.
//  Вратете нарачка на шпилот што ќе ги открие картите во зголемен редослед.
//
//Забележете дека првиот запис во одговорот се смета за врвот на шпилот.

//Input
// 17,13,11,2,3,5,7 --> ова е шпилот од карти
//Output
// 2 13 3 11 5 17 7

public class Main {
    public static int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }

        Arrays.sort(deck);

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[queue.dequeue()] = deck[i];
            queue.enqueue(queue.dequeue());
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String[] parts = line.split(",");

        int[] deck = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            int number = Integer.parseInt(parts[i]);
            deck[i] = number;
        }

        System.out.println(Arrays.toString(deckRevealedIncreasing(deck)));

    }
}
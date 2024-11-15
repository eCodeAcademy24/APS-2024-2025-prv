import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

//Има n луѓе во редот што чекаат да купат билети, каде што 0-то лице е на предниот дел од линијата, а (n - 1)-то лице е на задниот дел од линијата.
//
//Ви се дадени билети со цела низа со 0 индексирана со должина n каде што бројот на билети што би сакал да ги купи i-то лице е tickets[i].
//
//На секој човек му треба точно 1 секунда за да купи билет. Едно лице може да купи само 1 билет во исто време и мора
// да се врати до крајот на линијата (што се случува моментално) за да купи повеќе билети. Ако на некое лице не му остануваат билети за купување,
// лицето ќе ја напушти линијата.
//
//Вратете го времето потребно за лицето првично на позицијата k (0-индексирано) да заврши со купување билети.

//Input
// 2,3,2 --> tickets
// 2 --> k
//Output
// 6


public class Main {

    public static int timeRequiredToBuy(int[] tickets, int k) {
        LinkedQueue<Integer> ticketsQueue = new LinkedQueue<>();

        for (int ticket : tickets) {
            ticketsQueue.enqueue(ticket);
        }

        int secondsToBuy = 0;
        int index = 0;

        while (!ticketsQueue.isEmpty()) {
            int currentTicketCount = ticketsQueue.peek();

            if (currentTicketCount - 1 > 0) {
                ticketsQueue.dequeue();
                ticketsQueue.enqueue(currentTicketCount - 1);
                secondsToBuy++;
            } else {
                ticketsQueue.dequeue();
                secondsToBuy++;

                if (index == k) {
                    break;
                }
            }

            index = (index + 1) % tickets.length;
        }

        return secondsToBuy;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String[] parts = line.split(" ");

        int[] tickets = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            tickets[i] = Integer.parseInt(parts[i]);
        }

        line = br.readLine();

        int k = Integer.parseInt(line);

        int time = timeRequiredToBuy(tickets, k);
        System.out.println(time);
    }
}
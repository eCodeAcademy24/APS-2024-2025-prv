import java.io.*;
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

class Person {
    private int delay;
    private int forget;

    public Person(int delay, int forget) {
        this.delay = delay;
        this.forget = forget;
    }

    public int getDelay() {
        return delay;
    }

    public int getForget() {
        return forget;
    }
}

//Првиот ден, едно лице открива тајна.
//
//Имате доцнење кој што е цел број, што значи дека секој ден ќе ја споделува тајната со ново лице,
// почнувајќи од деновите за доцнење по откривањето на тајната. Ви е даден и цел број за заборавање,
// што значи дека секој човек ќе ја заборави тајната неколку дена откако ќе ја открие.
// Човек не може да ја сподели тајната истиот ден кога ја заборавил, или кој било ден потоа.

//Input
// n = 6
// delay = 2
// forget = 4
//Output
// 5
//Ден 1: Да претпоставиме дека првото лице се вика А. (1 лице)
//Ден 2: А е единствената личност која ја знае тајната. (1 лице)
//Ден 3: А ја споделува тајната со нова личност, Б. (2 лица)
//Ден 4: А ја споделува тајната со нова личност, C. (3 лица)
//Ден 5: А ја заборава тајната, а Б ја споделува тајната со ново лице, Д. (3 лица)
//Ден 6: Б ја споделува тајната со Е, а Ц ја споделува тајната со Ф. (5 лица)

public class Main {

    private static int peopleAwareOfSecret(int n, int delay, int forget) {
        LinkedQueue<Person> queue = new LinkedQueue<>();

        queue.enqueue(new Person(delay + 1, forget + 1));
        int totalPeople = 1;

        for (int day = 2; day <= n; day++) {
            int currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                Person person = queue.dequeue();

                if (day == person.getForget()) {
                    totalPeople--;
                } else {
                    queue.enqueue(person);
                }
            }

            currentSize = queue.size();
            for (int i = 0; i < currentSize; i++) {
                Person person = queue.dequeue();

                if (person.getDelay() <= day) {
                    queue.enqueue(new Person(day + delay, day + forget));
                    totalPeople++;
                }

                queue.enqueue(person);
            }
        }

        return totalPeople;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        int n = Integer.parseInt(line);

        line = br.readLine();
        int delay = Integer.parseInt(line);

        line = br.readLine();
        int forget = Integer.parseInt(line);

        System.out.println(peopleAwareOfSecret(n, delay, forget));
    }

//    private static int peopleAwareOfSecret(int n, int delay, int forget) {
//        LinkedQueue<Person> queue = new LinkedQueue<>();
//
//        queue.enqueue(new Person(delay + 1, forget + 1));
//
//        for (int day = 2; day <= n; day++) {
//            int currentSize = queue.size();
//            for (int i = 0; i < currentSize; i++) {
//                Person person = queue.dequeue();
//
//                if (day != person.getForget()) {
//                    queue.enqueue(person);
//                }
//            }
//
//            currentSize = queue.size();
//            for (int i = 0; i < currentSize; i++) {
//                Person person = queue.dequeue();
//
//                if (person.getDelay() <= day) {
//                    queue.enqueue(new Person(day + delay, day + forget));
//                }
//
//                queue.enqueue(person);
//            }
//        }
//
//        return queue.size();
//    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

class ArrayQueue<E> {
    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Ako length > 0, togash elementite na redicata se zachuvani vo elems[front...rear-1]
    // Ako rear > front, togash vo  elems[front...maxlength-1] i elems[0...rear-1]
    E[] elems;
    int length, front, rear;

    // Konstruktor ...

    @SuppressWarnings("unchecked")
    public ArrayQueue(int maxlength) {
        elems = (E[]) new Object[maxlength];
        clear();
    }

    public boolean isEmpty() {
        // Vrakja true ako i samo ako redicata e prazna.
        return (length == 0);
    }

    public int size() {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek() {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (length > 0)
            return elems[front];
        else
            throw new NoSuchElementException();
    }

    public void clear() {
        // Ja prazni redicata.
        length = 0;
        front = rear = 0;  // arbitrary
    }

    public void enqueue(E x) {
        // Go dodava x na kraj od redicata.
        if (length == elems.length)
            throw new NoSuchElementException();
        elems[rear++] = x;
        if (rear == elems.length) rear = 0;
        length++;
    }

    public E dequeue() {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (length > 0) {
            E frontmost = elems[front];
            elems[front++] = null;
            if (front == elems.length) front = 0;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }
}

//Училишната кафетерија нуди кружни и квадратни сендвичи на паузата за ручек, наведени со броевите 0 и 1 соодветно.
// Сите студенти стојат во редица. Секој студент преферира квадратни или кружни сендвичи.
//
//Бројот на сендвичи во кафетеријата е еднаков на бројот на ученици. Сендвичите се ставаат во низа. На секој чекор:
//
//Ако ученикот на предниот дел од редот го преферира сендвичот на почетокот на низата, тој ќе го земе и ќе го напушти редот.
//Во спротивно, тие ќе го остават и ќе одат до крајот на редот.
//Ова продолжува се додека никој од студентите во редица не сака да го земе горниот сендвич и затоа не можат да јадат.
//
//Ви се дадени две целобројни низи студенти и сендвичи каде што sandwiches[i] е типот на i-тиот сендвич во низата(i = 0 е почетокот на низата)
// и students[j] е претпочитање на j-тиот ученик во почетната редица (j = 0 е предниот дел од редот). Вратете го бројот на ученици кои не можат да јадат.

//Input
// 1 1 0 0 --> students
// 0 1 0 1 --> sandwiches
//Output
// 0

//Input
// 1 1 1 0 0 1  --> students
// 1 0 0 0 1 1 --> sandwiches
//Output
// 3


public class Main {

    private static int countStudents(int[] students, int[] sandwiches) {
        ArrayQueue<Integer> studentQueue = new ArrayQueue<>(students.length);

        for (int student : students) {
            studentQueue.enqueue(student);
        }

        int count = 0;
        int index = 0;

        while (!studentQueue.isEmpty() && count < studentQueue.size()) {
            int student = studentQueue.dequeue();

            if (student == sandwiches[index]) {
                index++;
                count = 0;
            } else {
                count++;
                studentQueue.enqueue(student);
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String[] parts = line.split(" ");

        int[] students = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            int number = Integer.parseInt(parts[i]);
            students[i] = number;
        }

        line = br.readLine();
        parts = line.split(" ");

        int[] sandwiches = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            sandwiches[i] = Integer.parseInt(parts[i]);
        }

        int numberOfStudents = countStudents(students, sandwiches);
        System.out.println(numberOfStudents);

//        System.out.println(countStudents(students, sandwiches));
    }
}
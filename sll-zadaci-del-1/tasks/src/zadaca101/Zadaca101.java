package zadaca101;


import java.util.Scanner;

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

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp + "->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }
}

//101. Дадена е еднострано поврзана листа. Да се напише функција која во листата ќе го
//пронајде првиот јазол со информација x и ќе го префрли на чело на листата. Како
//резултат функцијата да ја враќа позицијата (броено од почетокот на листата) на која
//бил пронајден бараниот јазол или -1 ако таков јазол не бил пронајден во листата.

//Input:
// 8
// 3→4→7→6→4→7→3→2;
// x=7
//Output:
// 7→3→4→6→4→7→3→2; return позиција 3

//Input:
// 5
// 2→5→5→3→1;
// x=5
//Output:
// 5→2→5→3→1; return позиција 2

//Input:
// 10
// 2→4→7→6→4→7→3→2→9→3;
// x=3
//Output:
// 3→2→4→7→6→4→7→2→9→3; return позиција 7

public class Zadaca101 {

    public static int zadaca101(SLL<Integer> lista, int x) {
        SLLNode<Integer> findX = lista.find(x);
        int index = 1;

        if (findX == null) {
            return -1;
        }

        // 3→4→7→6→4→7→3→2; x=7 index = 1 -> 2 -> 3

        SLLNode<Integer> iterator = lista.getFirst();
        while(iterator != null && iterator.element != x) {
            index++;

            iterator = iterator.succ; // i++ ekvivalentno vo izminuvanje na niza
        }

        lista.insertFirst(x);
        lista.delete(iterator);
        System.out.println(lista);

        return index;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SLL<Integer> integerSLL = new SLL<>();
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            integerSLL.insertLast(scanner.nextInt());
        }

        int x = scanner.nextInt();

        System.out.println(zadaca101(integerSLL, x));
    }
}

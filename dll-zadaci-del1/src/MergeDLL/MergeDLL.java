package MergeDLL;

import java.util.Scanner;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
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

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            deleteFirst();
            return node.element;
        }
        if (node == last) {
            deleteLast();
            return node.element;
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp + "<->";
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void izvadiDupliIPrebroj() {

    }
}

//Дадени се две сортирани двојно поврзани листи, при што, првата листа е сортирана во растечки редослед,
// а втората е сортирана во опаѓачки редослед.
// Да се констурира ново двојно поврзана листа којашто ќе ги содржи сите елементи од двете листи и тоа во опаѓачки редослед.
// При решавањето на задачата не смее да се користи сортирање на листи.

//Input:
//5 5
//1 3 4 6 9
//9 8 5 2 1
//Output:
//9 9 8 6 5 4 3 2 1 1

//Input:
//4
//4
//1 3 7 9
//9 6 5 4
//Output:
//9 9 7 6 5 4 3 1

public class MergeDLL {

    private static void spoi(DLL<Integer> lista1, DLL<Integer> lista2, DLL<Integer> lista3) {
        DLLNode<Integer> lastIteratorL1 = lista1.getLast();
        DLLNode<Integer> firstIteratorL2 = lista2.getFirst();

        while ((firstIteratorL2 != null) && (lastIteratorL1 != null)) {
            if (firstIteratorL2.element.equals(lastIteratorL1.element)) {
                lista3.insertLast(lastIteratorL1.element);
                lista3.insertLast(firstIteratorL2.element);
                firstIteratorL2 = firstIteratorL2.succ;
                lastIteratorL1 = lastIteratorL1.pred;
                continue;
            }

            if (lastIteratorL1.element > firstIteratorL2.element) {
                lista3.insertLast(lastIteratorL1.element);
                lastIteratorL1 = lastIteratorL1.pred;
            } else {
                lista3.insertLast(firstIteratorL2.element);
                firstIteratorL2 = firstIteratorL2.succ;
            }
        }

        while (firstIteratorL2 != null) {
            lista3.insertLast(firstIteratorL2.element);
            firstIteratorL2 = firstIteratorL2.succ;
        }

        while(lastIteratorL1 != null) {
            lista3.insertLast(lastIteratorL1.element);
            lastIteratorL1 = lastIteratorL1.pred;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        DLL<Integer> lista = new DLL<Integer>();

        for (int i = 0; i < n; i++) {
            lista.insertLast(scanner.nextInt());
        }

        DLL<Integer> lista1 = new DLL<Integer>();

        for (int i = 0; i < m; i++) {
            lista1.insertLast(scanner.nextInt());
        }

        DLL<Integer> lista2 = new DLL<Integer>();

        spoi(lista, lista1, lista2);

        System.out.println(lista2);
    }


}

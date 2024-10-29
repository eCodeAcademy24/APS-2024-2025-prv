package MergeTwoDLLs;

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
    private DLLNode<E> current, last;

    public DLL() {
        // Construct an empty SLL
        this.current = null;
        this.last = null;
    }

    public void deleteList() {
        current = null;
        last = null;
    }

    public int length() {
        int ret;
        if (current != null) {
            DLLNode<E> tmp = current;
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
        if (current != null) {
            DLLNode<E> tmp = current;
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
        return current;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, current);
        if (current == null)
            last = ins;
        else
            current.pred = ins;
        current = ins;
    }

    public void insertLast(E o) {
        if (current == null)
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
        if (before == current) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (current != null) {
            DLLNode<E> tmp = current;
            current = current.succ;
            if (current != null) current.pred = null;
            if (current == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (current != null) {
            if (current.succ == null)
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
        if (node == current) {
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
        if (current != null) {
            DLLNode<E> tmp = current;
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
        return current;
    }

    public DLLNode<E> getLast() {
        return last;
    }
}

//Дадени се две двојно поврзани листи, едната сортирана по растечки редослед едната не.
//Да се напише алгоритам кој ќе ги вметне сите јазли од втората листа во првата листа без да ја наруши нејзината подреденост.
//Влез:
//10
//2 4 5 7 7 9 11 12 15 17

//7
//1 18 7 8 9 3 16
//Излез:
//1 2 3 4 5 7 7 7 8 9 9 11 12 15 16 17 18

public class MergeDLLs {

    public static void merging(DLL<Integer> lista, DLL<Integer> lista2) {
        DLLNode<Integer> mainIterator = lista2.getFirst();

        while(mainIterator != null) {
            DLLNode<Integer> current = lista.getFirst();
            boolean flag = true;

            while(current != null) {
                if(current.element > mainIterator.element) {
                    lista.insertBefore(mainIterator.element, current);
                    lista2.delete(mainIterator);
                    flag = false;
                    break;
                }

                current = current.succ;
            }

            if(flag) {
                lista.insertLast(mainIterator.element);
            }

            mainIterator = mainIterator.succ;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        DLL<Integer> lista = new DLL<>();
        DLL<Integer> lista2 = new DLL<>();

        for (int i = 0; i < n; i++) {
            lista.insertLast(scanner.nextInt());
        }

        int m = scanner.nextInt();

        for (int i = 0; i < m; i++) {
            lista2.insertLast(scanner.nextInt());
        }

        merging(lista, lista2);

        System.out.println(lista);
    }
}

package Clients;
import java.lang.ref.Cleaner;
import java.util.Scanner;

class Client {
    private int id;
    private int loyalty;
    private int accounts;

    public Client(int id, int loyalty, int accounts) {
        this.id = id;
        this.loyalty = loyalty;
        this.accounts = accounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(int loyalty) {
        this.loyalty = loyalty;
    }

    public int getAccounts() {
        return accounts;
    }

    public void setAccounts(int accounts) {
        this.accounts = accounts;
    }

    public int calculateImportance() {
        return loyalty * 10 + accounts * 20;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
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

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
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
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " " + tmp;
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

public class Main {

//        Во рамки на една банка се користи систем кој ги чува податоците за клиентите на банката. Во тој систем податоците за
//        еден клиент
//        се дадени во формат: id на корисник (int id), години на лојалност (int loyalty), број на активни трансакциски сметки
//        (int accounts)
//
//        Имајќи предвид дека банката постои 50 години, ниту еден корисник не може да има лојалност поголема од 50.
//
//        Во рамки на системот, корисниците се чуваат од две еднострани поврзани листи. Во првата листа се чуваат податоците
//        за обичните
//        корисници (Normal), додека пак во втората листа се чуваат податоците за корисниците со посебни привилегии (Golden).
//
//
//        За секој клиент може да се пресмета неговата важност за банката според формулата.
//        ------> importance = loyalty * 10 + accounts * 20
//
//        Банката решила дека сака да направи измена, односно да го отстрани најмалку важниот клиент од Golden листата и да го
//        стави на
//        крај на Normal листата. Потоа, да го отстрани најмногу важниот клиент од Normal листата и да го стави на крај на
//        Golden листата.
//
//        Внимавај: Ако има повеќе клиенти со иста важност, се отстранува првиот.
//
//        Влез:
//        Во првиот ред е даден броот на клиенти од Normal листата.
//        Во вториот ред е даден броот на дискусии од Golden листата.
//        Во секоj следен ред се дадени податоци за еден клиент, одделени со празно место, во формат id loyalty accounts.
//        Притоа, прво се дадони податоците за клиентите од Normal листата, по што следуваат податоците за клиентите од
//        Golden листата.
//
//        Излез:
//        Во првиот ред id на сите клиенти од Normal листата.
//        Во вториот ред id на сите клиенти од Golden листата.

    public static void bank(SLL<Client> normal, SLL<Client> golden) {
        SLLNode<Client> goldenIterator = golden.getFirst().succ;
        SLLNode<Client> leastImportantClientGolden = golden.getFirst();

        while(goldenIterator != null) {
            if(goldenIterator.element.calculateImportance() < leastImportantClientGolden.element.calculateImportance()) {
                leastImportantClientGolden = goldenIterator;
            }
            goldenIterator = goldenIterator.succ;
        }

        golden.delete(leastImportantClientGolden);
        normal.insertLast(leastImportantClientGolden.element);

        SLLNode<Client> normalIterator = normal.getFirst().succ;
        SLLNode<Client> mostImportantClientNormal = normal.getFirst();

        while (normalIterator != null) {
            if (normalIterator.element.calculateImportance() > mostImportantClientNormal.element.calculateImportance()) {
                mostImportantClientNormal = normalIterator;
            }

            normalIterator = normalIterator.succ;
        }

        normal.delete(mostImportantClientNormal);
        golden.insertLast(mostImportantClientNormal.element);
    }

    // 5636 3606 6698
    //  1178 1705

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numNormal = Integer.parseInt(scanner.nextLine());
        int numGolden = Integer.parseInt(scanner.nextLine());

        SLL<Client> normal = new SLL<Client>();
        SLL<Client> golden = new SLL<Client>();

        for (int i = 0; i < numNormal; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            normal.insertLast(new Client(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        for (int i = 0; i < numGolden; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            golden.insertLast(new Client(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        bank(normal, golden);
        System.out.println(normal.toString());
        System.out.println(golden.toString());
    }
}

package Finance;

import java.util.*;

class Discussion
{
    private int id;
    private int popularity;
    private int users;

    public Discussion(int id, int popularity, int users) {
        this.id = id;
        this.popularity = popularity;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public int calculateImportance() {
        return popularity * users;
    }

    public String toString()
    {
        return id + "";
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
            ret += tmp + " ";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + " ";
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
            if(first==before){
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
            if(first ==node){
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
public class Main
{

//        Во рамки на еден форум постојат повеке отворени дискусии, т.ш. за секоја дискусија се чува податок за: ИД
//        на дискусиата.(int id), популарност (int popularity), како и броJ на корисници кои се активни (int users)
//        Популарноста на дискусијата е вредност во ранг (1, 100], каде што популарност 100 означува највоисока популарност.
//        Во рамки на форумот има повеке отворени дискусии, кои можат да се поделат во две еднострано поврзани листи.

//        Во првата листа се чуваат подотоците за дискусиите на тема Health,
//        а додека во втората листа се чуваат податоците за дискусиите на тема Finance.

//        За секоа дискусија може да се пресмета нејзината важност како производ на нејзината популарност и бројот на
//        активни корисници.
//        importance = popularity * users

//        Ваша задача е од дискусиите на тема Finance да се отстрани дискусијата со најмала важност.
//        Потоа, во дискусиите на тема Finance да се додаде нова дискусија со идентични податоци како најважната дискусија од
//        темата Health. Додавањето на дискусијата се прави на крај на листата која ги чува податоците за дискусиите на таа тема.
//
//        Влез:
//        Во првиот ред е даден броот на дискусии од Health темата.
//        Во вториот ред е даден броот на дискусии од Finance темата
//        Во секо] следен ред се дадени податоци за една дискусија, одделени со празно место, во формат id popularity users.
//        Притоа, прво се дадени податоците за дискусиите од Health листата, по што следуваат податоците за дискусиите од
//        Finance листата.
//
//        Излез:
//        Во првиот ред id на сите дискусии од Health листата
//        Во вториот ред id на сите дискусии од Finance листата.

    public static void forum(SLL<Discussion> health, SLL<Discussion> finance){
        SLLNode<Discussion> leastImportantFinance = finance.getFirst();
        SLLNode<Discussion> financeIterator = finance.getFirst().succ;

        while(financeIterator != null){
            int importance = financeIterator.element.calculateImportance();
            if(importance < leastImportantFinance.element.calculateImportance()){
                leastImportantFinance = financeIterator;
            }

            financeIterator = financeIterator.succ;
        }
        finance.delete(leastImportantFinance);

        SLLNode<Discussion> mostImportantHealth = health.getFirst();
        SLLNode<Discussion> healthIterator = health.getFirst().succ;

        while(healthIterator != null){
            int importance = healthIterator.element.calculateImportance();
            if(importance > mostImportantHealth.element.calculateImportance()){
                mostImportantHealth = healthIterator;
            }
            healthIterator = healthIterator.succ;
        }

        finance.insertLast(mostImportantHealth.element);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SLL<Discussion> health = new SLL();
        SLL<Discussion> finance = new SLL<>();
        int N = input.nextInt();
        int M = input.nextInt();

        for(int i=0;i<N;i++)
        {
            int id = input.nextInt();
            int popularity = input.nextInt();
            int users = input.nextInt();

            health.insertLast(new Discussion(id,popularity,users));
        }
        for(int i=0;i<M;i++)
        {
            int id = input.nextInt();
            int popularity = input.nextInt();
            int users = input.nextInt();

            finance.insertLast(new Discussion(id,popularity,users));
        }

        forum(health,finance);

        System.out.println(health);
        System.out.println(finance);
    }
}
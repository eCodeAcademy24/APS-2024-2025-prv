package Football;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

class Player implements Comparable<Player> {
    int number;
    int rating;
    int years;

    public Player(int number, int rating, int years) {
        this.number = number;
        this.rating = rating;
        this.years = years;
    }

    @Override
    public int compareTo(Player o) {
        if (o.rating > this.rating)
            return -1;
        if (o.rating < this.rating)
            return 1;
        if (o.rating == this.rating) {
            if (o.years > this.years)
                return 1;
            if (o.years < this.years)
                return -1;
        }
        return 0;
    }
}


public class Football {

    //Еден фудбалски клуб се состои од репрезентативен тим и тим на млади сило (под 21 година), секој од тимовите се состои од 11 главни играчи
    // Тренерот на фудбалскиот тим одлучува да направи освежување во поставата на реп. тим. Неговата цел е да ги замени првите N играчи
    // кои имаат најголемо искуство во репрезентативниот тим со топ N играчи со најдобар рејтинг од тимот на млади сили (соодветно на позициите)

    //Двата тима се претставени со две еднострано поврзани листи. Ваша задача е да ја имплементирате само функцијата
    //changePlayers(SLL<Player> representative_team, SLL<Player> under_21_team, int N) со чија помош ќе се изврши промената на N-те играчи
    //во листата representative_team.

    //На влез е даден број на секој играч, рејтинг и години искуство одделени со празно место. Прво се дадени 11 реда за секој играч
    //од репрезентативниот тим, а потоа следните 11 реда за играчите од тимот на млади сили.

    // Во крајниот ред е даден бројот N-број на играчи кои треба да се заменат од реп. тим

    // На излез се печати листата на броеви на сите играчи од новиот репрезентативен тим

    public static void changePlayers(SLL<Player> representative_team, SLL<Player> under_21_team, int N) {

        for(int i = 0; i < N; i++) {
            SLLNode<Player> iteratorRepresentative = representative_team.getFirst().succ;
            SLLNode<Player> mostYears = representative_team.getFirst();

            while(iteratorRepresentative != null) {
                if(iteratorRepresentative.element.years >= mostYears.element.years) {
                    mostYears = iteratorRepresentative;
                }

                iteratorRepresentative = iteratorRepresentative.succ;
            }

            SLLNode<Player> iteratorUnder21 = under_21_team.getFirst().succ;
            SLLNode<Player> bestPlayer = under_21_team.getFirst();

            while (iteratorUnder21 != null) {
                if (iteratorUnder21.element.rating >= bestPlayer.element.rating) {
                    bestPlayer = iteratorUnder21;
                }

                iteratorUnder21 = iteratorUnder21.succ;
            }

            representative_team.insertBefore(bestPlayer.element, mostYears);
            representative_team.delete(mostYears);
            under_21_team.delete(bestPlayer);
        }
    }

    public static void sort(SLL<Player> list) {
        SLLNode<Player> tmp1 = list.getFirst();
        while (tmp1 != null) {
            SLLNode<Player> tmp2 = tmp1.succ;
            while (tmp2 != null) {
                if (tmp1.element.compareTo(tmp2.element) < 0) {
                    Player pom = tmp1.element;
                    tmp1.element = tmp2.element;
                    tmp2.element = pom;
                }
                tmp2 = tmp2.succ;
            }

            tmp1 = tmp1.succ;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        SLL<Player> representative_team = new SLL<Player>();
        SLL<Player> under_21_team = new SLL<Player>();
        for (int i = 0; i < 11; i++) {
            String[] pom = bf.readLine().split(" ");
            Player p = new Player(Integer.parseInt(pom[0]), Integer.parseInt(pom[1]), Integer.parseInt(pom[2]));
            representative_team.insertLast(p);
        }

        for (int i = 0; i < 11; i++) {
            String[] pom = bf.readLine().split(" ");
            Player p = new Player(Integer.parseInt(pom[0]), Integer.parseInt(pom[1]), Integer.parseInt(pom[2]));
            under_21_team.insertLast(p);
        }

        sort(under_21_team);
        int N = Integer.parseInt(bf.readLine());

        changePlayers(representative_team, under_21_team, N);
        SLLNode<Player> tmp = representative_team.getFirst();

        while (tmp != null) {
            System.out.print(tmp.element.number + " ");
            tmp = tmp.succ;
        }
    }
}




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

    //1 1 3
    //2 2 3
    //3 3 4
    //4 4 5
    //5 5 5
    //6 6 6
    //7 7 6
    //8 8 7
    //9 9 7
    //10 10 8
    //11 11 8
    //12 3 2
    //13 2 1
    //14 1 5
    //15 5 3
    //16 5 4
    //17 10 2
    //18 3 3
    //19 2 1
    //20 9 3
    //21 9 1
    //22 1 1
    //2

    public static void changePlayers(SLL<Player> representative_team, SLL<Player> under_21_team, int N) {
        //Вашиот код тука
        int counter = 0;
        while (counter != N) {
            ++counter;

            SLLNode<Player> tmp = representative_team.getFirst();
            SLLNode<Player> najiskusen = tmp;

            tmp = tmp.succ;
            while (tmp != null) {
                if (tmp.element.years >= najiskusen.element.years) {
                    najiskusen = tmp;
                }

                tmp = tmp.succ;
            }

            SLLNode<Player> tmp2 = under_21_team.getFirst();
            SLLNode<Player> najdobar = under_21_team.getFirst();

            tmp2 = tmp2.succ;

            while (tmp2 != null) {
                if (tmp2.element.rating >= najdobar.element.rating) {
                    najdobar = tmp2;
                }

                tmp2 = tmp2.succ;
            }

            representative_team.insertBefore(najdobar.element, najiskusen);
            representative_team.delete(najiskusen);
            under_21_team.delete(najdobar);

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




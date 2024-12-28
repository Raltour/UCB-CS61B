//Make a list to restore infinity numbers.
public class intList {
    public int first;
    public intList rest;

    public intList(int f, intList r) {
        first = f;
        rest = r;
    }

    //Return the size of the list using recursion.
    public int size() {
        if (rest == null)
            return 1;
        else
            return 1 + rest.size();
    }

    //Return the i'th item in the list.
    public int get(int i) {
        if (i == 0)
            return this.first;
        return this.rest.get(i - 1);
    }

    //Return the size of the list without using recursion.
    public int iterativesize() {
        int total = 1;
        intList p = this;
        while (p.rest != null) {
            total++;
            p = p.rest;
        }
        return total;
    }

    public static void main(String[] args) {
        intList L = new intList(15, null);
        L = new intList(10, L);
        L = new intList(5, L);

        System.out.println(L.size());
        System.out.println(L.iterativesize());
        System.out.println(L.get(2));
    }
}

import java.util.Comparator;

public class Maximizer {
    public static Comparable max(Comparable[] items) {
        int maxDex = 0;
        for (int i = 0; i < items.length; i++) {
            int cmp = items[i].compareTo(items[maxDex]);
            if (cmp > 0) {
                maxDex = i;
            }
        }
        return items[maxDex];
    }

    public static void main(String[] args) {
        Dog d1 = new Dog("Elyse", 3);
        Dog d2 = new Dog("Sture", 9);
        Dog d3 = new Dog("Benjamin", 15);
        Dog[] dogs = new Dog[]{d1, d2, d3};

        Dog maxDog = (Dog) Maximizer.max(dogs);
        System.out.println(maxDog.name);

        Dog d4 = new Dog("Oski", 200);
        Dog d5 = new Dog("Cerebus", 999999);

        Comparator<Dog> nc = Dog.getNameComparator();
        System.out.println(nc.compare(d4, d5));
    }
}
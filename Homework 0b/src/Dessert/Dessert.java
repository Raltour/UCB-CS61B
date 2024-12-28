package Dessert;

public class Dessert {

    public int flavor;
    public int price;

    public Dessert(int f, int p) {
        flavor = f;
        price = p;
        numDesserts++;
    }

    public static int numDesserts;

    public void printDesserts() {
        String text = "";
        text += this.flavor;
        text += " ";
        text += this.price;
        text += " ";
        text += numDesserts;
        System.out.println(text);
    }

    public static void main(String[] args) {
        Dessert Cake = new Dessert(12, 43);
        Cake.printDesserts();
    }
}
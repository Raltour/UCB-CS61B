public interface List<Item> {
    public void addFirst(Item x);
    public void addLast(Item x);
    public void insert(Item x, int position);
    public Item get(int i);
    public int size();

    default public void print() {
         for (int i = 1; i <= size(); i++) {
             System.out.println(get(i));
         }
         System.out.println();
    }
}
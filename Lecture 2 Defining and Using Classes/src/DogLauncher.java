public class DogLauncher {
    public static void main(String[] args) {
        Dog d = new Dog(100);
        d.makeNoise();

        System.out.println(Dog.binomen);

        Dog chester = new Dog(17);
        Dog yusuf = new Dog(150);

        Dog larger_1 = Dog.maxDog(chester, yusuf);
        larger_1.makeNoise();

        Dog larger_2 = chester.maxDog(yusuf);
        larger_2.makeNoise();
    }
}
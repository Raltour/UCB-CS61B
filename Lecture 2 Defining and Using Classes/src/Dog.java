public class Dog {
    //实例变量
    public int weightInPounds;
    //静态变量
    public static String binomen = "Canis familiaris";

    public Dog(int w) {
        weightInPounds = w;
    }

    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("yipyipyip!");
        } else if (weightInPounds < 30) {
            System.out.println("Bark!");
        } else {
            System.out.println("arooooooo!");
        }
    }

    public static Dog maxDog(Dog d1, Dog d2) {
        if (d1.weightInPounds > d2.weightInPounds) {
            return d1;
        } else {
            return d2;
        }
    }

    public Dog maxDog(Dog d2) {
        if (this.weightInPounds > d2.weightInPounds) {
            return this;
        } else {
            return d2;
        }
    }
}
/*static和non-static的区别：
静态函数对所有一般的对象都可以使用，函数的执行结果是一样的，
调用时必须使用类的名字来调用它；
而非静态函数需要引用一个特定的对象，执行结果视情况可能是不同的，
调用时需要使用对象的名字来调用它
 */
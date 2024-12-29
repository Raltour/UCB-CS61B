import static java.lang.Math.sqrt;


public class Variance {

    public static double average(double[] nums) {
        double sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        return sum / nums.length;
    }

    public static double variance(double[] nums) {
        if (nums.length == 0) return 0;
        double sum = 0;
        double a = average(nums);
        for (int i = 0; i < nums.length; i++) {
            double difference = nums[i] - a;
            sum += difference * difference;
        }

        return sum / nums.length;
    }
}

import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;

public class VarianceTest {
    @Test
    public void test() {
        double[] input = {10, 20, 30, 40};
        double expected = 125.0;
        double actual = Variance.variance(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testEmptyList() {
        double[] input = {};
        double expected = 0.0;
        double actual = Variance.variance(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testReapeatedList() {
        double[] input = {61, 61, 61, 61, 61, 61};
        double expected = 0.0;
        double actual = Variance.variance(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testNegative() {
        double[] input = {-1, -2, -3, -8};
        double expected = 7.25;
        double actual = Variance.variance(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testZero() {
        double[] input = {0};
        double expected = 0.0;
        double actual = Variance.variance(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testNotInteger() {
        double[] input = {5.5, 6.5};
        double expected = 0.25;
        double actual = Variance.variance(input);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testBig() {
        int INPUTSIZE = 1000;
        double[] input = new double[INPUTSIZE];
        for (int i = 0; i < INPUTSIZE; i++) {
            input[i] = Math.pow(i, 3);
        }
        double expected = 83333.25;
        double actual = Variance.variance(input);
        assertThat(actual).isNotWithin(1e+10).of(expected);
    }
}
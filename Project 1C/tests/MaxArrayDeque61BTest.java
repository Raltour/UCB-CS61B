import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }

    @Test
    public void testBasicMax() {
        MaxArrayDeque61B<Integer> m = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());

        m.addFirst(1);
        m.addFirst(2);
        m.addFirst(3);
        assertThat(m.max()).isEqualTo(3);
    }

    @Test
    public void testSpecialMax() {
        MaxArrayDeque61B<Integer> m = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());

        m.addFirst(1);
        m.addFirst(2);
        m.addFirst(3);
        assertThat(m.max(Comparator.naturalOrder())).isEqualTo(3);
    }

    @Test
    public void testString() {
        MaxArrayDeque61B m;
        m = new MaxArrayDeque61B<String>(MaxArrayDeque61B.getNaturalOrderString());

        m.addFirst("A");
        m.addFirst("B");
        m.addFirst("C");
        assertThat(m.max()).isEqualTo("C");
    }
}

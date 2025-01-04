import org.junit.jupiter.api.Test;
import deque.ArrayDeque61B;
import deque.Deque61B;
import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {

    @Test
    public void test() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        for (String s : lld1) {
            System.out.println(s);
        }
    }

    @Test
    public void addLastTestBasicWithoutToList() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1).containsExactly("front", "middle", "back");
    }


    @Test
    public void testEqual() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        Deque61B<String> lld2 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    public void testToString() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");
    }
}

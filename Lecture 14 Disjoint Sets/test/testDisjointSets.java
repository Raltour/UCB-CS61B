import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class testDisjointSets {

    @Test
    public void testIsConnected() {
        DisjointSets sets = new DisjointSets(8);
        sets.connect(0, 1);
        sets.connect(0, 2);
        sets.connect(0, 3);
        sets.connect(4, 5);
        sets.connect(5, 6);
        sets.connect(6, 7);
        assertThat(sets.isConnected(0, 1)).isTrue();
        assertThat(sets.isConnected(4, 7)).isTrue();
    }
}
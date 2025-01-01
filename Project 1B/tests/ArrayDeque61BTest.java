import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
     void testAddFirst() {
         ArrayDeque61B list = new ArrayDeque61B();
         list.addFirst(10);
         list.addFirst(20);
         list.addFirst(30);
         list.addFirst(40);
         list.addFirst(50);
         assertThat(list.toList()).containsExactly(50, 40, 30, 20, 10).inOrder();
     }

     @Test
    void testAddLast() {
        ArrayDeque61B list = new ArrayDeque61B();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);
        assertThat(list.toList()).containsExactly(10, 20, 30, 40, 50).inOrder();
    }

    @Test
    void testAddFirstAndLast() {
         ArrayDeque61B list = new ArrayDeque61B();
         list.addFirst(10);
         list.addFirst(20);
         list.addFirst(30);
         list.addLast(40);
         list.addLast(50);
        assertThat(list.toList()).containsExactly(30, 20, 10, 40, 50).inOrder();
    }

    @Test
    void testIsEmpty() {
         ArrayDeque61B list = new ArrayDeque61B();
         assertThat(list.isEmpty()).isTrue();
    }

    @Test
    void testSize() {
         ArrayDeque61B list = new ArrayDeque61B();
         list.addFirst(10);
         list.addFirst(20);
         list.addLast(30);
         list.addLast(40);
         list.addLast(50);
         list.removeFirst();
         list.removeLast();
         assertThat(list.size()).isEqualTo(3);
    }

    @Test
    void testRemoveFirst() {
         ArrayDeque61B list = new ArrayDeque61B();
         list.addFirst(10);
         list.addFirst(20);
         list.addFirst(30);
         list.removeFirst();
         list.removeFirst();
         assertThat(list.toList()).containsExactly(10).inOrder();
    }

    @Test
    void testRemoveLast() {
         ArrayDeque61B list = new ArrayDeque61B();
         list.addFirst(10);
         list.addFirst(20);
         list.addLast(30);
         list.addLast(40);
         list.removeLast();
         assertThat(list.toList()).containsExactly(20, 10, 30).inOrder();
    }
}

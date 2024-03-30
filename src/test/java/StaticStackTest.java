import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StaticStackTest {

    private StaticStack<Integer> stack;

    @BeforeEach
    public void setUp() {
        stack = new StaticStack<>(3);
    }

    @Test
    void shouldCreateAStackWith32DefaultCapacity() {
        stack = new StaticStack<>();

        assertEquals(stack.size(), 32);
    }

    @Test
    public void shouldRemoveElementFromTopWhenCallPop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenPushNewElementInFullStack() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertThrows(IllegalStateException.class, () -> stack.push(4));
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenCallPopInEmptyStack() {
        assertThrows(IllegalStateException.class, stack::pop);
    }

    @Test
    public void shouldNotChangeTheElementsWhenCallTop() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.top());
        assertEquals(3, stack.count());
    }

    @Test
    void shouldThrowIllegalStateExceptionWhenCallTopInEmptyStack() {
        assertThrows(IllegalStateException.class, stack::top);
    }

    @Test
    public void shouldReturnStackCapacityWhenCallSize() {
        assertEquals(3, stack.size());
    }

    @Test
    void shouldReturnNumberOfElementsInStackWhenCallCount() {
        assertEquals(0, stack.count());

        stack.push(1);
        assertEquals(1, stack.count());

        stack.push(2);
        assertEquals(2, stack.count());

        stack.pop();
        assertEquals(1, stack.count());
    }

    @Test
    void shouldStackIsEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void shouldStackIsFull() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertTrue(stack.isFull());
    }

}
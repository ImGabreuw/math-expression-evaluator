public class StaticStack<E> implements Stack<E> {

    private static final int DEFAULT_CAPACITY = 32;

    private final E[] elements;

    private int count;

    public StaticStack() {
        this(DEFAULT_CAPACITY);
    }

    public StaticStack(int size) {
        //noinspection unchecked
        this(
                (E[]) new Object[size],
                0
        );
    }

    public StaticStack(E[] elements, int count) {
        this.elements = elements;
        this.count = count;
    }

    @Override
    public void push(E element) {
        if (isFull()) {
            throw new IllegalStateException("A pilha está cheia.");
        }

        elements[count++] = element;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia.");
        }

        var element = elements[count - 1];
        elements[--count] = null;

        return element;
    }

    @Override
    public E top() {
        if (isEmpty()) {
            throw new IllegalStateException("A pilha está vazia.");
        }

        return elements[count - 1];
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean isFull() {
        return count == size();
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }
}

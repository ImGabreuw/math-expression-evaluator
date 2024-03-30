public interface Stack<E> {

    void push(E element);

    E pop();

    E top();

    int size();

    int count();

    boolean isFull();

    boolean isEmpty();

}

public class QueueImplementation<E> implements Queue<E> {

  private E[] elems;
  private int height;
  public static final int DEFAULT_CAPACITY = 10;

  @SuppressWarnings("unchecked")
  public QueueImplementation() {
    height = 0;
    elems = (E[]) new Object[DEFAULT_CAPACITY];
  }

  public boolean isEmpty() {
    return height == 0;
  }

  @SuppressWarnings("unchecked")
  public void enqueue(E s) {
    if (height == elems.length) {
      E[] elems2 = (E[]) new Object[elems.length * 2];

      for (int x = 0; x < elems.length; x++) {
        elems2[x] = elems[x];
      }

      elems = elems2;
    }

    elems[height] = s;
    height++;
  }

  @SuppressWarnings("unchecked")
  public E dequeue() {
    E dequeue = elems[0];

    E[] elems3 = (E[]) new Object[elems.length];

    for (int x = 0; x < elems.length - 1; x++) {
      elems3[x] = elems[x + 1];
    }

    elems = elems3;
    height--;

    return dequeue;
  }
}

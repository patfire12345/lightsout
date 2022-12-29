public interface Queue<E> {
  public abstract boolean isEmpty();

  public abstract void enqueue(E s);

  public abstract E dequeue();
}

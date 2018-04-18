public class Stack<E> {
  private E[] elements;
  private int capacity;
  private int size;

  public Stack() {
    capacity = 10;
    size = 0;
    elements = (E[]) new Object[capacity];
  }

  public Stack(int capacity) {
    this.capacity = capacity;
    size = 0;
    elements = (E[]) new Object[capacity];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return (size == 0);
  }

  public E peek() {
    if (isEmpty()) {
      return null;
    }
    return elements[size - 1];
  }

  public boolean push(E e) {
    if (size == capacity) {
      return false;
    } else {
      elements[size++] = e;
    }
    return true;
  }

  public E pop() {
    if (isEmpty()) {
      return null;
    } else {
      E item = elements[size - 1];
      size--;
      return item;
    }
  }
}

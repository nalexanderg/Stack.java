public class MyArrayList<T> {
  private T[] elements;
  private int capacity;
  private int size;

  public MyArrayList() {
    capacity = 10;
    size = 0;
    elements = (T[]) new Object[capacity];
  }

  public MyArrayList(int capacity) {
    this.capacity = capacity;
    size = 0;
    elements = (T[]) new Object[capacity];
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return (size == 0);
  }

  public T get(int index) {
    if (index >= 0 && index <= size - 1) {
      return elements[index];
    } else {
      return null;
    }
  }

  public void set(int index, T t) {
    if (index >= 0 && index <= size - 1) {
      elements[index] = t;
    } else {
      return;
    }
  }

  public boolean insert(int index, T t) {
    if (size < capacity && (index >= 0 || index <= size + 1)) {
      for (int k = size - 1; k >= index; k--) {
        elements[k + 1] = elements[index];
      }
      elements[index] = t;
      size++;
      return true;
    } else {
      return false;
    }
  }

  public T remove(int index) {
    if (size > 0 && (index >= 0 || index <= size)) {
      for (int k = index; k < size - 1; k++) {
        elements[k] = elements[k + 1];
      }
    size--;
    }

    return elements[index];
  }
}

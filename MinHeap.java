public class MinHeap<K extends Comparable<K>, V> {
  private class Pair<K extends Comparable<K>,V> {
    private K key;
    private V value;

    Pair(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }
    public V getValue() {
      return value;
    }
  }

  private MyArrayList<Pair<K,V>> list;

  public MinHeap() {
    list = new MyArrayList<Pair<K,V>>();
  }

  public MinHeap(int capacity) {
    list = new MyArrayList<Pair<K,V>>(capacity);
  }

  public int size() {
    return list.size();
  }

  public boolean isEmpty() {
    return (list.size() == 0);
  }

  public void swap(int a, int b) {
    final Pair<K,V> TEMP = list.get(a);
    list.set(a, list.get(b));
    list.set(b, TEMP);
  }

  public void heapUp(int index) {
    if (index == 0) {
      return;
    }
    int parent = (index - 1)/2;
    Pair<K,V> p = list.get(parent);
    Pair<K,V> c = list.get(index);

    if (c.key.compareTo(p.key) < 0) {
      swap(parent, index);
      heapUp(parent);
    }
  }

  public void heapDown(int index) {
    int leftChild = 2 * index + 1;
    int rightChild = 2 * index + 2;
    Pair<K,V> p = list.get(index);
    Pair<K,V> left = list.get(leftChild);
    Pair<K,V> right = list.get(rightChild);

    if (left == null) {
      return;
    }

    if (right == null) {
      if (left.key.compareTo(p.key) < 0) {
        swap(leftChild, index);
      }
    } else if (left.key.compareTo(right.key) < 0) {
      if (left.key.compareTo(p.key) < 0) {
        swap(leftChild, index);
        heapDown(leftChild);
      } else {
        return;
      }
    } else {
      if (right.key.compareTo(p.key) < 0) {
        swap(rightChild, index);
        heapDown(rightChild);
      } else {
        return;
      }
    }
  }

  public boolean insert(K key, V val) {
    Pair<K,V> p = new Pair(key, val);
    list.insert(list.size(), p);
    heapUp(list.size() - 1);

    return true;
  }

  public V removeMin(){
    if (isEmpty()) {
      return null;
    }
    Pair<K,V> root = list.get(0);
    list.set(0, list.get(list.size() - 1));
    list.remove(list.size() - 1);
    heapDown(0);

    return root.getValue();
  }

  //return the minimum key's value, cannot return smallest value of heap because V does not extend Comparable
  public V min() {
    if (isEmpty()) {
      return null;
    }
    Pair<K,V> smallest = list.get(0);
    for (int i = 1; i < list.size(); i++) {
      Pair<K,V> temp = list.get(i);
      if (temp.key.compareTo(smallest.key) < 0) {
        smallest = list.get(i);
      }
    }

    return smallest.getValue();
  }

  public String toString() {
    String mh = "";
    for (int p = 0; p < list.size(); p++) {
      mh += ("Key: " +list.get(p).getKey()+ ", Value: " +list.get(p).getValue()+ "\n");
    }

    mh += ("\nMinimum key's value: " +min()+ "\n");

    return mh;
  }
}

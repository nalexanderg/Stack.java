public class PQueue<K extends Comparable<K>,V> {
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

  MyArrayList<Pair<K,V>> list;

  public PQueue() {
    list = new MyArrayList<Pair<K,V>>();
  }

  public PQueue(int capacity) {
    list = new MyArrayList<Pair<K,V>>(capacity);
  }

  public int size() {
    return list.size();
  }

  public boolean isEmpty() {
    return (list.size() == 0);
  }

  public boolean insert(K key, V val) {
    Pair<K,V> p = new Pair(key, val);
    int s = list.size();
    list.insert(s, p);
    return true;
  }

  //Iterator methods cannot be used on objects that are not considered a part of Collections
  public V removeMin() {
    if (isEmpty()) {
      return null;
    }
    int minIndex = 0;
    Pair<K,V> smallest = list.get(0);
    for (int i = 1; i < list.size(); i++) {
      Pair<K,V> temp = list.get(i);
      if (temp.key.compareTo(smallest.key) < 0) {
        smallest = list.get(i);
        minIndex = i;
      }
    }

    return list.remove(minIndex).getValue();
  }

  //return the minimum key's value, cannot return smallest value of priority queue because V does not extend Comparable
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
    String pq = "";
    for (int p = 0; p < list.size(); p++) {
      pq += ("Key: " +list.get(p).getKey()+ ", Value: " +list.get(p).getValue()+ "\n");
    }

    pq += ("\n Minimum key's value: " +min()+ "\n");

    return pq;
  }

}

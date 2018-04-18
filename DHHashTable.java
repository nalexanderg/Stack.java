public class DHHashTable<K extends Comparable<K>,V> {
  private class Pair<K extends Comparable<K>, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }
    public V getValue() {
      return value;
    }
    public void setKey(K key) {
      this.key = key;
    }
    public void setValue(V value) {
      this.value = value;
    }
  }

  private Pair<K,V>[] table;
  private int capacity; // N
  private int size; // the number elements in the table

  public DHHashTable(int prime) {
    capacity = prime;
    size = 0;
    table = new Pair[capacity];  // construct an empty table with prime slots. In practice, prime should a prime number.
  }

  private int hashValue(K key) {
    int hc = key.hashCode();
    return hc % capacity; //2. Compression step
  }
  // d(k) = 2699 - key mod 2699
  public int size() {
    return size;
  }
  public boolean isEmpty() {
    return (size == 0);
  }
  public V get(K key) {
    int step = 0;
    int dOfK = 2699 - (key.hashCode() % 2699);
    int i = hashValue(key);

    while (table[i] != null) {
      if (table[i].getKey().compareTo(key) == 0) {
        break;
      } else {
        step++;
        i = (i + (step * dOfK)) % capacity;
      }
    }
    if (table[i] == null) {
      return null;
    }

    return table[i].getValue();
  }
  // return the number of collisions
  public int  put(K key, V val) {
    int collisions = 0;
    int dOfK = 2699 - (key.hashCode() % 2699);
    int i = hashValue(key);
    while (table[i] != null) {
      collisions++;
      i = (i + (collisions * dOfK)) % capacity;
    }
    table[i] = new Pair<>(key,val);
    size++;

    return collisions;
  }
  public void remove(K key) {
    int step = 0;
    int dOfK = 2699 - (key.hashCode() % 2699);
    int i = hashValue(key);

    while (table[i] != null) {
      if (table[i].getKey().compareTo(key) == 0) {
        break;
      } else {
        step++;
        i = (i + (step * dOfK)) % capacity;
      }
    }
    if (table[i] == null) {
      return;
    }
    table[i] = null;
    size--;
  }

}

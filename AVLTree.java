public class AVLTree<K extends Comparable<K> ,V> {
  private class AVLNode<K,V> {
    private K key;
    private V value;
    private int height;
    private int size;
    private AVLNode<K,V> parent;
    private AVLNode<K,V> left;
    private AVLNode<K,V> right;

    AVLNode(K key, V val) {
      this(key, val, 1, 1);
    }
    AVLNode(K key, V val, int h, int size) {
      this.key = key;
      this.value = val;
      this.height = h;
      this.size = size;
      this.parent = null;
      this.left = null;
      this.right = null;
    }

    K getKey() {return key;}
    V getValue() {return value;}
    int getH() {return height;}
    int getSize() {return size;}
    AVLNode<K,V> getLeft() {return left;}
    AVLNode<K,V> getRight() {return right;}
    AVLNode<K,V> getParent() {return parent;}
    void setKey(K key) {this.key = key;}
    void setValue(V val) {this.value = val;}
    void setH(int h) {this.height = h;}
    void setSize(int s) {this.size = s;}
    void setLeft(AVLNode<K,V> l) {this.left = l;}
    void setRight(AVLNode<K,V> r) {this.right = r;}
    void setParent(AVLNode<K,V> p) {this.parent = p;}
  }

  private AVLNode<K,V> root;    // the root of the tree
  private AVLNode<K,V> NIL;     // the common leaf node

  AVLTree() {
    NIL = new AVLNode<>(null, null, 0, 0);
    root = NIL;
  }

  public int size() {
    return root.getSize();
  }

  public int height() {
    return root.getH();
  }

  public boolean isEmpty() {
    return (size() == 0);
  }

  private int max(int u, int v) {
    if (u >= v) {
      return u;
    } else {
      return v;
    }
  }

  public void insert(K key, V val) {
    // your implementation
    AVLNode<K,V> child = new AVLNode<>(key, val);
    root = insertAVL(root, child);
  }

  private AVLNode<K,V> insertAVL(AVLNode<K,V> p, AVLNode<K,V> item) {
    // recursively insert an AVLNode item into a subtree rooted at p
    // your implementation
    if (p.getKey() == null) {
      p = item;
      p.setLeft(NIL);
      p.setRight(NIL);
      p.setH(1);
      p.setSize(p.getSize() + 1);
    } else if (item.getKey().compareTo(p.getKey()) < 0) {
      p.setLeft(insertAVL(p.getLeft(), item));
      if (p.getLeft().getH() - p.getRight().getH() == 2) {
        if (item.getKey().compareTo(p.getLeft().getKey()) < 0) {
          rotateLL(p); // do rotateLL
        } else {
          rotateLR(p); // do rotateLR
        }
      }
    } else if (item.getKey().compareTo(p.getKey()) > 0) {
      p.setRight(insertAVL(p.getRight(), item));
      if (p.getRight().getH() - p.getLeft().getH() == 2) {
        if (item.getKey().compareTo(p.getLeft().getKey()) > 0) {
          rotateRR(p); // do rotateRR
        } else {
          rotateRL(p); // do rotateRL
        }
      }
    } else {
      // do nothing, duplicate
    }
    return p;
  }

  private void rotateLL(AVLNode<K,V> n) { //single rotation
    // your implementation
    AVLNode<K,V> newRoot = n.getRight();
    int isRightChild = n.getKey().compareTo(n.getParent().getRight().getKey());
    if (isRightChild == 0) {
      n.getParent().setRight(newRoot);
    } else {
      n.getParent().setLeft(newRoot);
    }
    AVLNode<K,V> temp = newRoot.getLeft();
    newRoot.setLeft(n);
    n.setRight(temp);
    // fix heights
    n.setH(1 + max(n.getLeft().getH(), n.getRight().getH()));
    newRoot.setH(1 + max(newRoot.getLeft().getH(), newRoot.getRight().getH()));
  }

  private void rotateRR(AVLNode<K,V> n) { //single rotation
    // your implementation
    AVLNode<K,V> newRoot = n.getLeft();
    int isLeftChild = n.getKey().compareTo(n.getParent().getLeft().getKey());
    if (isLeftChild == 0) {
      n.getParent().setLeft(newRoot);
    } else {
      n.getParent().setRight(newRoot);
    }
    AVLNode<K,V> temp = newRoot.getRight();
    newRoot.setRight(n);
    n.setLeft(temp);
    // fix heights
    n.setH(1 + max(n.getLeft().getH(), n.getRight().getH()));
    newRoot.setH(1 + max(newRoot.getLeft().getH(), newRoot.getRight().getH()));
  }

  private void rotateLR(AVLNode<K,V> n) { //double rotation
    // your implementation
    rotateLL(n.getLeft());
    rotateRR(n);
  }

  private void rotateRL(AVLNode<K,V> n) { //double rotation
    // your implementation
    rotateRR(n.getRight());
    rotateLL(n);
  }

  public String toString() {
    // return a String: the in-order sequence of the data items
    inOrder(root);
    return "";
  }

  private void inOrder(AVLNode<K,V> n) {
    if (n != NIL) {
      inOrder(n.left);
      System.out.println("(K: " + n.getKey() + ",V: " + n.getValue() + ") ");
      inOrder(n.right);
    }
  }
}

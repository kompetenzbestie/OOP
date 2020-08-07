package u10;

public class BinLinkedTree <T extends Comparable<T>, D> implements Iterable<T> {

  private DTreeNode root;
  private int size;

  public BinLinkedTree() {
    root = null;
    size = 0;
  }

  public InorderIterator iterator() {
    return new InorderIterator();
  }

  public int size() {
    return size;
  }

  public boolean contains(T key) {
    return getData(key) != null;
  }

  public D getData(T key) {
    DTreeNode node = root;

    while(node != null) {
      int compare = key.compareTo(node.key);
      if (compare < 0) {
        node = node.left;
      } else if (compare > 0) {
        node = node.right;
      } else {
        return node.data;
      }
    }

    return null;
  }

  public void store(T key, D data) {
    root = insert(root, key, data);
  }

  private DTreeNode insert(DTreeNode node, T key, D data) {
    if (node == null) {
      size++;
      return new DTreeNode(key, data);
    }

    int compare = key.compareTo(node.key);
    if (compare < 0) {
      node.left = insert(node.left, key, data);
    } else if (compare > 0) {
      node.right = insert(node.right, key, data);
    } else {
      node.data = data;
    }

    return node;
  }

  public boolean empty() {
    return root == null;
  }

  public DTreeNode minNode(DTreeNode node) {
    while(node.left != null) {
      node = node.left;
    }

    return node;
  }

  public DTreeNode maxNode(DTreeNode node) {
    while(node.right != null) {
      node = node.right;
    }

    return node;
  }

  public DTreeNode succ(DTreeNode node) {
    return node.right;
  }

  public DTreeNode pred(DTreeNode node) {
    DTreeNode ret = root;

    while(ret != null) {
      int compare = ret.key.compareTo(node.key);
      if (compare < 0) {
        ret = ret.right;
      } else if (compare > 0) {
        ret = ret.left;
      } else {
        return ret;
      }
    }

    return null;
  }

  //public DTreeNode delete(DTreeNode node) {}

  //public int deep() {}

  //public D[] toArray() {}

  //public String toString() {}

  public boolean perfectlyBalanced(DTreeNode node) {
    if (node.left != null && node.right != null) {
      return (true && perfectlyBalanced(node.left) && perfectlyBalanced(node.right));
    } else {
      return false;
    }
  }

  private class InorderIterator implements Iterator<T> {

    private Stack<DTreeNode> stack = new Stack<DTreeNode>();

    InorderIterator() {
      pushLeftTree(root);
    }

    public boolean hasNext() {
      return !stack.isEmpty();
    }

    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      DTreeNode node = stack.pop();
      pushLeftTree(node.right);
      return node.key;
    }

    public void pushLeftTree(DTreeNode node) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  private class DTreeNode {
    private T key;
    private D data;
    private DTreeNode left;
    private DTreeNode right;
    private DTreeNode parent;
  }
}

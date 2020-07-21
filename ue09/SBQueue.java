package u9;

public class SBQueue<E> implements Queue<E>, Iterable<E> {

  private E[] queue;
  private int head;
  private int tail;

  public SBQueue(E[] _queue) {
    head = 0;
    tail = 0;
    this.queue = _queue;
  }

  public QueueIterator iterator() {
    return new QueueIterator(head);
  }

  public void enqueue(E elem) {
    queue[tail] = elem;
    if (tail == (queue.length-1)) {
      tail = 0;
    } else {
      tail++;
    }
  }

  public E dequeue() throws EmptyQueueException {
    if (!empty()) {
      E elem = queue[head];
      if(head == (queue.length-1)) {
        head = 0;
      } else {
        head++;
      }

      return elem;
    } else {
      throw new EmptyQueueException();
    }
  }

  public E first() throws EmptyQueueException {
    if (!empty()) {
      return queue[head];
    } else {
      throw new EmptyQueueException();
    }
  }

  public boolean empty() {
    return head == tail;
  }

  public String toString() {
    QueueIterator qi = iterator();

    String ret = "";
    for (;qi.hasNext();) {
      ret += (char) qi.next();
    }
    
    return ret;
  }

  class QueueIterator implements Iterator<E> {
    int current;

    QueueIterator(int head) {
      current = head;
    }

    public boolean hasNext() {
      return ((queue[current] != null) || (current != tail));
    }

    public E next() {

      if (queue[current] == null) {
        return null;
      }

      E ret = queue[current];

      if ((current+1) == queue.length) {
        current = 0;
      } else {
        current++;
      }

      return ret;
    }
  }
}

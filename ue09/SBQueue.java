package u9;

// Der Name steht für "Schiller-Brandenburg-Queue", da mir nichts besseres eingefallen ist.
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

  // Siehe Folien VL 20.
  public void enqueue(E elem) {
    queue[tail] = elem;
    if (tail == (queue.length-1)) {
      tail = 0;
    } else {
      tail++;
    }
  }

  // Siehe Folien VL 20.
  public E dequeue() throws EmptyQueueException {
    if (!empty()) {
      E elem = queue[head];
      queue[head] = null;
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

  // Das Element an der Position des aktuellen heads wird zurückgegeben.
  public E first() throws EmptyQueueException {
    if (!empty()) {
      return queue[head];
    } else {
      throw new EmptyQueueException();
    }
  }

  // Siehe Folien VL 20.
  public boolean empty() {
    return (head == tail)&&(queue[head] != null);
  }

  // Mithilfe des Iterators gehen wir hier durch alle Elemente durch und konkatenieren diese zu einem String.
  public String toString() {
    QueueIterator qi = iterator();

    String ret = "";
    for (;qi.hasNext();) {
      ret += (char) qi.next();
    }

    return ret;
  }

  class QueueIterator implements Iterator<E> {

    // Der Iterator besitzt einen inneren Zähler, der angibt, welches Element momentan betrachtet wird.
    private int current;

    QueueIterator(int head) {
      // Der Iterator wird am Kopf angesetzt.
      current = head;
    }

    // Das aktuelle Element hat ein nächstes Element, wenn es nicht leer ist oder der tail der Queue ist.
    public boolean hasNext() {
      return ((queue[current] != null) || (current != tail));
    }

    public E next() {
      // Ist das aktuelle Element leer, kann nicht weiter gegangen werden.
      if (queue[current] == null) {
        return null;
      }

      E ret = queue[current];

      // Es wird überprüft, ob der Iterator an den Anfang der Array verschoben werden muss.
      if ((current+1) == queue.length) {
        current = 0;
      } else {
        current++;
      }

      return ret;
    }
  }
}

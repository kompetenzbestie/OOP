package u9;

public class PriorityQueue<P extends Comparable<P>, Data> {

  private Message<P, Data>[] heap;
  private int size;

  // Ich verwende hier einige SuppressWarnings, da es sich hier hauptsächlich um unchecked conversions handelt, die in der Ausführung immer funktionieren
  // und bei denen ich nach einer halben Stunde Recherche nicht herausgefunden habe, wie ich sie weg bekomme.
  @SuppressWarnings("unchecked")
  public PriorityQueue() {
    // size entspricht immer length-1, da hier die tatsächlich besetzten Stellen der Array gezählt werden (0 fällt hier weg, da dies ein Heap ist).
    this.size = 0;
    this.heap = new Message[1];
  }

  public int parent(int i) {
    assert(i>0);
    return i/2;
  }

  public int left(int i) {
    assert(i>0);
    return i*2;
  }

  public int right(int i) {
    assert(i>0);
    return i*2+1;
  }

  // Da Message eine interne Klasse ist, kann von außen nicht einfach auf einzelne Objekte zugegriffen werden.
  // Daher gibt es diese beiden Funktionen, die die Eigenschaften einer Message anhand des Arrayindexes zurückgibt.
  public P getPriorityAt(int index) {
    assert(index>0 && index <= size);
    return heap[index].priority;
  }

  public Data getDataAt(int index) {
    assert(index>0 && index <= size);
    return heap[index].data;
  }

  public int getSize() { return size; }

  // Die folgenden beiden Funktionen funktionieren nach dem selben Prinzip: die aktuelle Array wird abgespeichert, eine neue Array wird mit entweder einer Stelle mehr oder weniger erzeugt und in die Variable der alten Array geschrieben.
  // Danach wird der Inhalt der alten in die neue Array kopiert, wobei bei decreaseSize() die letzte Position weggelassen wird.
  // Die Länge der neuen Array wird nur zurückgegeben, da wir immer irgendetwas zurückgeben sollen, und nicht weil es irgendeinen praktischen Nutzen erfüllt.
  @SuppressWarnings("unchecked")
  private int increaseSize() {
    assert(size >= 0);
    Message<P, Data>[] old = heap;
    heap = new Message[size+2];
    System.arraycopy(old, 0, heap, 0, size+1);
    assert(heap.length == (old.length+1));

    return heap.length;
  }

  @SuppressWarnings("unchecked")
  private int decreaseSize() {
    assert(size > 0);
    Message<P, Data>[] old = heap;
    heap = new Message[size];
    System.arraycopy(old, 0, heap, 0, size);
    assert(heap.length == (old.length-1));

    return heap.length;
  }

  // Die Funktion funktioniert ungefähr nach dem in der Vorlesung vorgestellten max_heapify-Algorithmus:
  // Wir starten an der Spitze und tauschen so lange die aktuelle Nachricht mit dem jeweils größeren Kind, bis wir entweder nicht weiter gehen können oder keine Kinder mehr größer sind.
  // Da wir davon ausgehen können, dass nur ein Element die Heap-Eigenschaft verletzt, müssen wir dies nicht rekursiv für die gesamte Array aufrufen, sondern können uns auf einen "Pfad" konzentrieren.
  // So oder so wird am Ende die Heap-Eigenschaft intakt sein.
  private Data sortHeap() {

    // Wenn der Heap nur ein oder kein Element enthält, muss nicht sortiert werden.
    if(size > 1) {
      int i=1;
      int child;

      Message<P, Data> top = heap[i];

      for (;left(i) <= size;i = child) {

        child = left(i);
        // Es wird überprüft, ob das rechte Kind größer ist als das linke Kind.
        if (child != size && heap[child].priority.compareTo(heap[child+1].priority) < 0) {
          // child wird um 1 erhöht, damit wir beim Vergleich das rechte Element betrachten und uns im Falle eines Tausches nicht nach links, sondern nach rechts bewegen.
          child++;
        }
        assert(child == left(i) || child == right(i));
        // Hier wird überprüft, ob das aktuelle Element kleiner als das zu vegleichende Element ist.
        // Wenn ja, findet ein Tausch statt, wenn nicht, wird die AUsführung beendet.
        if (top.priority.compareTo(heap[child].priority) < 0) {
          heap[i] = heap[child];
        } else {
          break;
        }
      }

      heap[i] = top;
    }

    // Es wird versucht, das aktuell höchste Element zurückzugeben.
    try {
      return highest();
    } catch (EmptyQueueException e) {
      return null;
    }
  }

  public void enqueue(P priority, Data data) {

    // Die Array wird um einen Platz erweitert.
    increaseSize();
    // Dies wird in size wiedergespiegelt.
    size++;
    // Im folgenden wird der Heap von der letzten Stelle aus nach oben hin betrachtet und an jedem Punkt überprüft, ob das einzufügende Objekt hier untergebracht werden kann.
    int pos = size;

    for (;pos>1 && priority.compareTo(heap[parent(pos)].priority)>0; pos = parent(pos)) {
      heap[pos] = heap[parent(pos)];
    }

    Message<P, Data> mess = new Message<P, Data>(priority, data);
    heap[pos] = mess;

  }

  public Data dequeue() throws EmptyQueueException {
    if (empty()) {
      throw new EmptyQueueException();
    }

    // Die zurückzugebende Nachricht wird abgespeichert und die Nachricht an der letzten Stelle des Heaps wird an ihre Position kopiert.
    // Dieser Tausch ermöglicht es später, dass sortHeap() in O(log(n)) ausgeführt werden kann.
    Data ret = heap[1].data;
    heap[1] = heap[size];

    // Die Array wird um einen Platz verkürzt.
    decreaseSize();
    // Dies wird in size wiedergespiegelt.
    size--;
    // Der Heap wird sortiert.
    sortHeap();

    return ret;
  }

  public Data highest() throws EmptyQueueException {
    if (empty()) {
      throw new EmptyQueueException();
    }

    return heap[1].data;
  }

  public boolean empty() {
    return(size == 0);
  }

  //--------------------------------------------------//

  private class Message<P extends Comparable<P>, Data> {
    public P priority;
    public Data data;

    Message(P _priority, Data _data) {
      this.priority = _priority;
      this.data = _data;
    }
  }
}

package u9;

// Dieser Datentyp bildet einen Wrapper um einen Int, um diesen Comparable<>-konform zu machen.
public class Prio implements Comparable<Prio> {

  private int value;

  public Prio(int _value) {
    this.value = _value;
  }

  public int getValue() { return value; }

  // Die IMplementierung von getValue(). Es wird überprüft, ob der andere Int größer, kleiner oder gleich Diesem ist. 
  public int compareTo(Prio o) {
    if (this.value < o.getValue()) {
      return -1;
    } else if (this.value > o.getValue()) {
      return 1;
    } else {
      return 0;
    }
  }

}

package u9;

// Dieser Datentyp bildet einen Wrapper um einen String, damit dieser in PriorityQueue verwendet werden kann.
public class StringData {

  private final String content;

  public StringData(String _content) {
    this.content = _content;
  }

  public String getValue() {
    return this.content;
  }

}

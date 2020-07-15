package u8;

public class NotEnoughCapacityException extends Exception {

  // NotEnoughCapacityException soll immer die Menge von Flüssigkeit nehmen, die nicht mehr gepasst hat.
  public int tooMuch;

  public NotEnoughCapacityException(int _tooMuch) {
    super("The mug didn't have enough capacity to hold all the liquid!");
    this.tooMuch = _tooMuch;
  }
}

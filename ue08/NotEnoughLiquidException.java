package u8;

public class NotEnoughLiquidException extends Exception {

  // NotEnoughCapacityException soll immer die Menge von Flüssigkeit nehmen, die nicht mehr genommen werden konnte.
  int tooMuch;

  public NotEnoughLiquidException(int _tooMuch) {
    super("The mug didn't have enough liquid to take out!");
    this.tooMuch = _tooMuch;
  }
}

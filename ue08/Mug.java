package u8;

public class Mug<L extends Liquid> {
  public L liquid;

  public int capacity;
  public int filledCapacity;

  public Mug(L _liquid, int _capacity) {
    // Wenn die Tasse initialisiert wird, enthält sie noch keine Flüssigkeit.
    this.capacity = _capacity;
    this.filledCapacity = 0;
    this.liquid = _liquid;
  }

  public void pour(int ml) throws NotEnoughCapacityException {
    // Es wird überprüft, ob die gewünschte Menge von Flüssigkeit noch in die Tasse passt.
    // Wenn nicht, wird so viel Flüssigkeit eingegossen, wie nich reinpasst und eine NotEnoughCapacityException wird ausgelöst.
    if ((ml+this.filledCapacity)>this.capacity) {
      this.filledCapacity = this.capacity;
      throw new NotEnoughCapacityException((ml+this.filledCapacity)-this.capacity);
    }

    this.filledCapacity += ml;
  }

  public void takeOut(int ml) throws NotEnoughLiquidException {
    // Es wird überprüft, ob die gewünschte Menge von Flüssigkeit noch aus der Tasse entfernt werden kann.
    // Wenn nicht, wird die Tasse geleert und eine NotEnoughLiquidException wird ausgelöst.
    if ((this.filledCapacity-ml)<0) {
      empty();
      throw new NotEnoughLiquidException(ml-filledCapacity);
    }

    this.filledCapacity -= ml;
  }

  public void drink(int ml) throws UndrinkableException, NotEnoughLiquidException {
    // Es wird überprüft, ob die Flüssigkeit in der Tasse überhaupt trinkbar ist.
    // Wenn nicht, wird eine UndrinkableException ausgelöst.
    // Ansonsten wird versucht, die gewünschte Menge aus der Tasse zu entfernen (takeOut() überprüft dann, ob eine NotEnoughLiquidException ausgelöst werden muss).
    if (!this.liquid.isDrinkable()) {
      throw new UndrinkableException();
    }

    takeOut(ml);
  }

  public int empty() {
    // Ich bin mir nicht ganz sicher, was hier zurückgegeben werden soll, daher wird hier die Tasse geleert und ihr neuer Füllstand (0) zurückgegeben.
    this.filledCapacity = 0;
    return this.filledCapacity;
  }

  public boolean isEmpty() {
    return (this.filledCapacity == 0);
  }

  public boolean isHot() {
    return (this.liquid.temperature >= 100);
  }
}

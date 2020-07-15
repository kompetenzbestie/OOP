package u8;

public class Lava extends Liquid {

  public Lava() {
    // Lava besitzt in der Regel Temperaturen zwischen 800°C und 1200°C und ssollte daher nicht getrunken werden.
    super("Lava", new java.awt.Color(255, 104, 51), false);
    this.temperature = 1100;
  }

  public String getName() {
    return this.name;
  }

  public java.awt.Color getColor() {
    return this.color;
  }

  public boolean isDrinkable() {
    return this.drinkable;
  }

  public void heatUp(int temperature) {
    this.temperature += temperature;
  }

  public int getTemperature() {
    return this.temperature;
  }

}

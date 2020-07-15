package u8;

public class EarlGrey extends Liquid {

  public EarlGrey() {
    // Earl Grey ist eine Art von Tee und daher dunkelbraun und trinkbar.
    super("Earl Grey", new java.awt.Color(43, 31, 6), true);
    this.temperature = 40;
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

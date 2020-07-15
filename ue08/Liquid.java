package u8;

public abstract class Liquid {

  final protected String name;
  final protected java.awt.Color color;
  final protected boolean drinkable;
  protected int temperature = 18;

  protected Liquid(String name, java.awt.Color color, boolean drinkable) {
    this.name = name;
    this.color = color;
    this.drinkable = drinkable;
  }

  public abstract String getName();
  public abstract java.awt.Color getColor();
  public abstract boolean isDrinkable();
  public abstract void heatUp(int temperature);
  public abstract int getTemperature();
}

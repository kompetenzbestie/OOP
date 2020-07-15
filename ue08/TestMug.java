package u8;

import java.util.Random;

public class TestMug {

  static Random rand = new Random();

  static Mug<EarlGrey> smallTea = new Mug<EarlGrey>(new EarlGrey(), 1000);
  static Mug<EarlGrey> bigTea = new Mug<EarlGrey>(new EarlGrey(), 5000);

  static Mug<Lava> smallLavaBucket = new Mug<Lava>(new Lava(), 1000);
  static Mug<Lava> bigLavaBucket = new Mug<Lava>(new Lava(), 5000);

  public static void main(String[] args) {

    // Jede Tasse wird mit 2l der Flüssigkeit gefüllt.

    pourTest(2000, 2000, 2000, 2000);

    // Aus den kleinen Tassen werden 500 ml getrunken (es bleiben noch 1500 ml übrig) und aus den großen 2000 ml (wonach sie leer sind).
    // Bei den Lavaeimern wird dies natürlich so oder so fehlschlagen, da es Lava ist.

    drinkTest(500, 2000, 500, 2000);

    // In alle Tassen werden erst 0 ml gegossen und dann 0 ml getrunken.

    pourTest(0,0,0,0);
    drinkTest(0,0,0,0);

    // Diese Prozedur wird mit zufälligen Zahlen erneut durchgeführt.

    pourTest(rand.nextInt(2500), rand.nextInt(2500), rand.nextInt(2500), rand.nextInt(2500));
    drinkTest(rand.nextInt(2500), rand.nextInt(2500), rand.nextInt(2500), rand.nextInt(2500));

    getAllTemperatures();

    smallTea.liquid.heatUp(50);
    bigTea.liquid.heatUp(100);
    smallLavaBucket.liquid.heatUp(50);
    bigLavaBucket.liquid.heatUp(100);

    getAllTemperatures();

  }

  // pourTest() befüllt alle Tassen mit der jeweils angegebenen Menge und kümmert sich darum, dass eventuelle Exeption abgefangen und in der Ausgabe bemerkbar gemacht werden.
  public static void pourTest(int ml1, int ml2, int ml3, int ml4) {

    System.out.println("\npour():\n");

    try {
      smallTea.pour(ml1);
      System.out.println("Successfully poured " + ml1 + " ml!");
    } catch (NotEnoughCapacityException e) {
      System.out.println("Couldn't fit another " + e.tooMuch +" ml!");
    }

    try {
      bigTea.pour(ml2);
      System.out.println("Successfully poured " + ml2 + " ml!");
    } catch (NotEnoughCapacityException e) {
      System.out.println("Couldn't fit another " + e.tooMuch +" ml!");
    }

    try {
      smallLavaBucket.pour(ml3);
      System.out.println("Successfully poured " + ml3 + " ml!");
    } catch (NotEnoughCapacityException e) {
      System.out.println("Couldn't fit another " + e.tooMuch +" ml!");
    }

    try {
      bigLavaBucket.pour(ml4);
      System.out.println("Successfully poured " + ml4 + " ml!");
    } catch (NotEnoughCapacityException e) {
      System.out.println("Couldn't fit another " + e.tooMuch +" ml!");
    }

    checkFilled();

  }

  // drinkTest() trinkt aus allen Tassen die jeweils angegebenene Menge und kümmert sich darum, dass eventuelle Exeption abgefangen und in der Ausgabe bemerkbar gemacht werden.
  public static void drinkTest(int ml1, int ml2, int ml3, int ml4) {

    System.out.println("\ndrink():\n");

    try {
      smallTea.drink(ml1);
      System.out.println("Successfully drank 500 ml!");
    } catch (UndrinkableException e) {
      System.out.println("You can't drink this liquid! Or at least you shouldn't.");
    } catch (NotEnoughLiquidException e) {
      System.out.println("Couldn't take another " + e.tooMuch +" ml!");
    }

    try {
      bigTea.drink(ml2);
      System.out.println("Successfully drank 2000 ml!");
    } catch (UndrinkableException e) {
      System.out.println("You can't drink this liquid! Or at least you shouldn't.");
    } catch (NotEnoughLiquidException e) {
      System.out.println("Couldn't take another " + e.tooMuch +" ml!");
    }

    try {
      smallLavaBucket.drink(ml3);
      System.out.println("Successfully drank 500 ml!");
    } catch (UndrinkableException e) {
      System.out.println("You can't drink this liquid! Or at least you shouldn't.");
    } catch (NotEnoughLiquidException e) {
      System.out.println("Couldn't take another " + e.tooMuch +" ml!");
    }

    try {
      bigLavaBucket.drink(ml4);
      System.out.println("Successfully drank 2000 ml!");
    } catch (UndrinkableException e) {
      System.out.println("You can't drink this liquid! Or at least you shouldn't.");
    } catch (NotEnoughLiquidException e) {
      System.out.println("Couldn't take another " + e.tooMuch +" ml!");
    }

    checkFilled();

  }

  // checkFilled() gibt die aktuellen Füllstände aller Tassen aus, wobei eine spezielle Ausgabe erfolgt, wenn die Tasse leer ist.
  public static void checkFilled() {

    System.out.println();

    if (smallTea.isEmpty()) {
      System.out.println("The small cup of tea is empty.");
    } else {
      System.out.println("The small cup of tea contains " + smallTea.filledCapacity + " ml.");
    }

    if (bigTea.isEmpty()) {
      System.out.println("The big cup of tea is empty.");
    } else {
      System.out.println("The big cup of tea contains " + bigTea.filledCapacity + " ml.");
    }

    if (smallLavaBucket.isEmpty()) {
      System.out.println("The small bucket of lava is empty.");
    } else {
      System.out.println("The small bucket of lava contains " + smallLavaBucket.filledCapacity + " ml.");
    }

    if (bigLavaBucket.isEmpty()) {
      System.out.println("The big bucket of lava is empty.");
    } else {
      System.out.println("The big bucket of lava contains " + bigLavaBucket.filledCapacity + " ml.");
    }
  }

  // getAllTemperatures() gibt die aktuellen Temperaturen der Flüssigkeiten aller Tassen aus, wobei speziell bemerkt wird, ob sie zu heiß (>100°C) ist.
  public static void getAllTemperatures() {

    System.out.println();

    System.out.print("The Earl Grey in the small cup is " + smallTea.liquid.getTemperature() + "°C.\t");

    if (smallTea.isHot()) {
      System.out.println("That's too hot!");
    } else {
      System.out.println("That's just right!");
    }

    System.out.print("The Earl Grey in the big cup is " + bigTea.liquid.getTemperature() + "°C.\t");

    if (bigTea.isHot()) {
      System.out.println("That's too hot!");
    } else {
      System.out.println("That's just right!");
    }

    System.out.print("The Lava in the small bucket is " + smallLavaBucket.liquid.getTemperature() + "°C.\t");

    if (smallLavaBucket.isHot()) {
      System.out.println("That's too hot!");
    } else {
      System.out.println("That's just right!");
    }

    System.out.print("The Lava in the big bucket is " + bigLavaBucket.liquid.getTemperature() + "°C.\t");

    if (bigLavaBucket.isHot()) {
      System.out.println("That's too hot!");
    } else {
      System.out.println("That's just right!");
    }
  }
}

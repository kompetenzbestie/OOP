package u7;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Scared implements Shape, Animation {

  double radius;
  Point center;

  Color color;
  ShapesWorld welt;
  Random rand;

  // ANMERKUNG: Laut Aufgabenstellung soll das Objekt springen, sobald es berührt wird. Da es aber auch "sterben" soll, sobald es im Sprung berührt wird,
  // stirbt es in 99% der Fälle direkt, sobald es einmal berührt wird, da die Berührung einen Tick nach der Auslösung des Sprungs immer noch registriert wird.
  // Daher habe ich die Distanz, die nötig ist, um einen Sprung auszulösen etwas erhöht.
  double scareDistance = 190.0;
  double jumpDistance = 80.0;

  boolean jumping = false;
  int targetX;
  int targetY;

  int shakeDirection = 0;
  double shakeDistance = 3.0;

  public Scared() {
    this.rand = new Random();
    this.radius = 25;
    this.center = new Point();
    this.color = Color.ORANGE;
  }

  public Color getColor()
  { return color; }

  public void moveTo(double x, double y){
    center.x = (int) x;
    center.y = (int) y;
  }

  public void setShapesWorld( ShapesWorld theWorld )
  {
    this.welt = theWorld;
  }

  public void draw(Graphics g) {
    g.setColor(color);
    g.fillRoundRect((int) (center.x-radius), (int) (center.y-radius), (int) radius*2,  (int)radius*2, 10, 10);
    g.setColor(Color.BLACK);
    g.drawString(":<", (int) center.x, (int) center.y);
  }

  public Point getCenter() {
   return center;
  }

  public void userClicked(double atX, double atY){

  }

  public void userTyped(char key){

  }

  // implement the Animation-Interface
  public void play() {

    // Das nächste Objekt wird gesucht.
    Shape closest = welt.getClosestShape(this);

    // Es wird überprüft, ob es noch ein anderes Objekt in der Welt gibt.
    // Scared handelt nur dann, wenn das der Fall ist; wenn es nicht anderes gibt, braucht Scared auch keine Angst haben.
    if (closest != null) {

      // Die Distanz zu dem nächsten Objekt wird berechnet. Die Aufteilung in x_dist und y_dist geschieht nur, weil ich schlecht in Mathe bin.
      double x_dist = Math.abs(center.x-closest.getCenter().x);
      double y_dist = Math.abs(center.y-closest.getCenter().y);
      double distance = Math.sqrt((x_dist*x_dist)+(y_dist*y_dist));

      // Scared befindet sich gerade im Sprung.
      if (jumping) {

        // Wenn sich die beiden Objekte berühren, löst sich Scared in Steine auf.
        if (distance <= radius+closest.getRadius()) {
          dropStones();
        }

        // Wurden die Zielkoordinaten erreicht, hört Scared auf, zu springen.
        if (targetX == center.x && targetY == center.y) {
          jumping = false;
          return;
        }

        // Ansonsten bewegt es sich auf seine Zielkoordinate hin.
        if (targetX > center.x) {
          center.x++;
        } else if (targetX < center.x) {
          center.x--;
        }

        if (targetY > center.y) {
          center.y++;
        } else if (targetY < center.y) {
          center.y--;
        }

      } else if (distance <= jumpDistance) {

        // Wenn das nächste Objekt zu nahe kommt, werden zufällige Zielkoordinaten generiert und der Sprung beginnt.
        targetX = randomInRange((int) (welt.getMin_X()+radius),(int) (welt.getMax_X()-radius));
        targetY = randomInRange((int) (welt.getMin_Y()+radius),(int) (welt.getMax_Y()-radius));
        jumping = true;

      } else if (distance <= scareDistance) {

        // Wenn das nächste Objekt nah genug kommt, um Scared Angst zu machen, fängt es an, zu zittern.
        // Die Richtung, in die Scared zittert, ist in shakeDirection gespeichert.
        switch(shakeDirection) {
          case 0:
            center.x -= shakeDistance;
            shakeDirection++;
            break;
          case 1:
            center.x += shakeDistance;
            shakeDirection--;
            break;
        }
      }
    } else {
      // Es befindet sich niemand außer Scared in der Welt, es braucht also keine Angst mehr haben.
      jumping = false;
    }
  }

  public boolean contains(double x, double y) {
    if (x<(center.x-radius) || x>center.x+radius || y<(center.y-radius) || y>(center.y+radius))
      return false;
    else
      return true;
  }

  public double getRadius() {
    return radius;
  }

  // Eine Funktion, um das Berechnen einer Zufallszahl zwischen zwei Zahlen ein wenig übersichtlicher zu machen.
  public int randomInRange(int min, int max) {
    return rand.nextInt(max - min) + min;
  }

  public void dropStones() {
    // Eine zufällige Anzahl an MiniSteinen wird an einer zufälligen Position innerhalb von Scared generiert.
    for (int i=0 ; i<(rand.nextInt(10)+1) ; i++) {
      MiniStein mini = new MiniStein(randomInRange((int) (center.x-radius),(int) (center.x+radius)),randomInRange((int) (center.y-radius),(int) (center.y+radius)));
      welt.addShape(mini);
    }
    // Scared wird entfernt.
    welt.removeShape(this);
  }
}

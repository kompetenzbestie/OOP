package u7;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

// Growing wird an einer zufälligen Stelle gespawnt und wächst, bis es ein anderes Objekt berührt.
public class Growing implements Shape, Animation {

  double radius;
  Point center;

  Random rand;
  Color color;
  ShapesWorld welt;

  boolean grow = true;

  public Growing() {
    this.radius = 25;
    this.rand = new Random();
    this.center = new Point(randomInRange(-200,200),randomInRange(-180,180));
    this.color = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
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
    g.fillOval((int) (center.x-radius), (int) (center.y-radius), (int) radius*2,  (int)radius*2);
  }

  public Point getCenter() {
   return center;
  }

  public void userClicked(double atX, double atY){

  }

  public void userTyped(char key){

  }

  // implement the Animation-Interface
  public void play()
  {
    if (grow) {
      // Das nächste Objekt wird gesucht.
      Shape closest = welt.getClosestShape(this);

      // Wenn es ein anderes Objekt in der Welt gibt, wird die Distanz berechnet.
      if (closest != null) {
        double x_dist = Math.abs(center.x-closest.getCenter().x);
        double y_dist = Math.abs(center.y-closest.getCenter().y);
        double distance = Math.sqrt((x_dist*x_dist)+(y_dist*y_dist));

        // Wenn sich die beiden Objekte berühren, ist Schlus mit wachsen.
        if (distance <= radius+closest.getRadius()) {
          grow = false;
          return;
        }
      }

      // Das Objekt wächst.
      radius++;
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
}

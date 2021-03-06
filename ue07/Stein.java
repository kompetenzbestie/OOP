package u7;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Stein implements Shape, Animation {

  double radius;
  Point center;

  Color color;
  ShapesWorld welt;
  Random rand;

  double g = 0.981;
  int time = 1;

  public Stein() {
    this.rand = new Random();
    this.radius = 25;
    this.color = Color.GRAY;
    this.center = new Point(rand.nextInt(500)-250,-200);
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
    g.fillOval((int) (center.x-radius), (int) (center.y-radius), (int) radius*2, (int) radius*2);
  }

  public Point getCenter() {
   return center;
  }

  public void userClicked(double atX, double atY){

  }

  public void userTyped(char key){
    System.out.println("key");
  }

  // implement the Animation-Interface
  public void play()
  {
    if (center.y+(radius*2)>=welt.getMax_Y()) {
      dropStones();
    } else {
      center.y = center.y + (g*time);
      time++;
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
    for (int i=0 ; i<(rand.nextInt(10)+1) ; i++) {
      // Eine zufällige Anzahl an MiniSteinen wird an einer zufälligen Position innerhalb von Stein generiert.
      MiniStein mini = new MiniStein(randomInRange((int) (center.x-radius),(int) (center.x+radius)),randomInRange((int) (center.y-radius),(int) (center.y+radius)));
      welt.addShape(mini);
    }
    // Stein wird entfernt.
    welt.removeShape(this);
  }
}

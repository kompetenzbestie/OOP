package u7;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

// Lawine spawnt so lange MiniSteine an einer zufälligen Position am oberen Bildschrimrand, bis sie durch das Drücken von q unterbrochen wird.
public class Lawine implements Shape, Animation {

  double radius;
  Point center;

  Color color;
  ShapesWorld welt;
  Random rand;

  public Lawine() {
    this.radius = 250;
    this.center = new Point();
    this.color = Color.CYAN;
    this.rand = new Random();
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
  }

  public Point getCenter() {
   return center;
  }

  public void userClicked(double atX, double atY){

  }

  public void userTyped(char key){
    if (key == 'q') {
      welt.removeShape(this);
    }
  }

  // implement the Animation-Interface
  public void play()
  {
    // Ein MiniStein wird an einer zufälligen x-Koordiante an welt.getMin_Y() gespawnt.
    MiniStein mini = new MiniStein(randomInRange(welt.getMin_X(), welt.getMax_X()), welt.getMin_Y());
    welt.addShape(mini);
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

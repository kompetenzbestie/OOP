package u7;

import java.awt.Color;
import java.awt.Graphics;

public class GoAndBack implements Shape, Animation {

  double radius;
  Point center;

  Color color;
  ShapesWorld welt;
  double velocity = 5;

  public GoAndBack() {
   this.radius = 25;
   this.color = Color.CYAN;
   this.center = new Point();
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
    g.fillRect((int) (center.x-radius), (int) (center.y-radius), (int) radius*2,  (int)radius*2);
  }

  public Point getCenter() {
   return center;
  }

  public void userClicked(double atX, double atY){
   // this.radius += 2;
   // this.welt.addShape(new GoAndBack());
  }

  public void userTyped(char key){
    System.out.println("key");
  }

  // implement the Animation-Interface
  public void play()
  {
    if ((center.x+radius)<=welt.getMax_X() && (center.x-radius)>=welt.getMin_X())
      center.x = center.x + velocity;
    else {
      velocity = (-velocity);
      center.x = center.x + velocity;
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

}

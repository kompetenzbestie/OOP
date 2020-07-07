package u7;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Captive implements Shape, Animation {

  double radius;
  Point center;

  Random rand;
  Color color;
  ShapesWorld welt;
  double velocity = 5;

  public Captive() {
   this.radius = 25;
   this.color = Color.MAGENTA;
   this.center = new Point();
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
    g.setColor(color);
    g.fillRect((int) center.x, (int) center.y, (int) radius*2,  (int)radius*2);
  }

  public Point getCenter() {
   return center;
  }

  public void userClicked(double atX, double atY){
   // this.radius += 2;
   // this.welt.addShape(new Captive());
  }

  public void userTyped(char key){
    System.out.println("key");
  }

  // implement the Animation-Interface
  public void play()
  {
    int xd=0, yd=0;

    if (center.x+(radius*2)>=((welt.getMax_X())-5))  {
      xd = -10; yd = 0;
    } else if ((center.x<=((welt.getMin_X())+5))) {
      xd = 10; yd = 0;
    } else if ((center.y+(radius*2)>=((welt.getMax_Y())-5))) {
      xd = 0; yd = -10;
    } else if (center.y<=((welt.getMin_Y())+5)) {
      xd = 0; yd = 10;
    } else {
      int d = rand.nextInt(4);
      switch (d) {
        case 0:
              xd = 10; yd = 10; break;
        case 1:
              xd = 10; yd = -10; break;
        case 2:
              xd = -10; yd = 10; break;
        case 3:
              xd = -10; yd = -10; break;
      }
    }

    this.moveTo(this.center.x+xd, this.center.y+yd);
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

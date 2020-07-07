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

    Shape closest = welt.getClosestShape(this);

    if (closest != null) {
      double x_dist = Math.abs(center.x-closest.getCenter().x);
      double y_dist = Math.abs(center.y-closest.getCenter().y);
      double distance = Math.sqrt(Math.pow(x_dist,2)+Math.pow(y_dist,2));

      if (jumping) {
        if (distance <= 50.0) {
          dropStones();
        }

        if (targetX == center.x && targetY == center.y) {
          jumping = false;
          return;
        }

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
        targetX = randomInRange((int) (welt.getMin_X()+radius),(int) (welt.getMax_X()-radius));
        targetY = randomInRange((int) (welt.getMin_Y()+radius),(int) (welt.getMax_Y()-radius));
        jumping = true;
      } else if (distance <= scareDistance) {
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

  public int randomInRange(int min, int max) {
    return rand.nextInt(max - min) + min;
  }

  public void dropStones() {
    for (int i=0 ; i<(rand.nextInt(10)+1) ; i++) {
      MiniStein mini = new MiniStein(randomInRange((int) (center.x-radius),(int) (center.x+radius)),randomInRange((int) (center.y-radius),(int) (center.y+radius)));
      welt.addShape(mini);
    }
    welt.removeShape(this);
  }
}

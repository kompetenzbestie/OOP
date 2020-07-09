package u7;

import java.awt.Color;
import java.awt.Graphics;

//mit WASD bewegt sich der Roboter, mit j wird ein Sprung ausgeführt (währenddessen kann keine andere Bewegung vorgenommen werden).
public class Roboter implements Shape, Animation {

  double radius;
  double velocity = 2;
  int jump = 10;
  Point center;

  Color color;
  Color color2;
  Color color3;
  Color color4;
  ShapesWorld welt;

  public Roboter() {
    this.radius = 25;
    this.center = new Point();
    this.color = Color.GRAY;
    this.color2 = Color.DARK_GRAY;
    this.color3 = Color.YELLOW;
    this.color4 = Color.BLACK;
  }

  public Color getColor()
  { return color; }

  public Color getColor2()
  { return color2; }

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
    g.fillRect((int) (center.x), (int) (center.y), (int) radius,  (int)radius);
    g.setColor(color2);
    g.fillRect((int) (center.x-(radius/5)), (int) (center.y-(radius*1.5)), (int) (radius*1.5),  (int)(radius*1.5));
    g.setColor(color3);
    g.fillOval((int) (center.x+(0)), (int) (center.y -(radius)), 4, 4);
    g.fillOval((int) (center.x+(radius)), (int) (center.y -(radius)), 4, 4);
    g.drawOval((int) (center.x+(radius/3)), (int) (center.y-(radius/2.5)), 10, 10);
  }

  public Point getCenter() {
   return center;
  }

  public void userClicked(double atX, double atY){

  }
 //Je nach Tastendruck wird die Center Variable in die jeweilige Richtung verschoben, falls j gedrückt wird, wird ein Sprung initialisiert.
 //Falls gerade ein Sprung ausgeführt wird, wird die Tastatureingabe ignoriert.
  public void userTyped(char key){
     if(jump == 10){
      switch(key){
	       case 'w': center.y = center.y - 5; break;
	       case 'a': center.x = center.x - 5; break;
	       case 's': center.y = center.y + 5; break;
	       case 'd': center.x = center.x + 5; break;
         case 'j': jump = 0;
	       default: break;
	    }
    }
  }

  //Falls das Objekt sich innerhalbt des Bildschirms befindet, wird normal animiert, falls nicht, so wird der Roboter wieder hinein teleportiert.
  public void play()
  {
    if ((center.x+(radius*1.5))<=welt.getMax_X() && center.x>=welt.getMin_X() && (center.y-(radius/5)) >= welt.getMin_Y() && (center.y+radius) <= welt.getMax_Y()){
    //Weiter
    }
    else {
	    center.x = (welt.getMax_X()/2);
	    center.y = (welt.getMax_Y()/2);
    }
    //Falls ein Sprung stattfindet, so zeigt die jump variable, wie weit der Sprung fortgeschritten ist. Falls jump < 5 so bewegt sich der
    //Roboter nach oben, falls 4 < jump < 10, so bewegt er sich nach unten. Bei jump = 10 ist der Sprung fertig durchgeführt.
    if(jump < 5){
      center.y = center.y -5;
      jump++;
    }
    else if(jump < 10){
      center.y = center.y +5;
      jump++;
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

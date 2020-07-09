package u7;

import java.awt.Color;
import java.awt.Graphics;

//Vibe spawnt ein Objekt, dessen Kopf sich auf und ab bewegt. Wenn es angeklickt wird, bewegt es sich wie GoAndBack, wenn es nochmal angeklickt wird,
//dann bewegt es sich auf und ab. Wenn man s drückt stopt das ausgewählte Objekt.
public class Vibe implements Shape, Animation {

  double radius;
  double bpm = 5;
  double velocity = 2;
  int status = 0;
  Point center;
  Point center2;

  Color color;
  Color color2;
  Color color3;
  Color color4;
  ShapesWorld welt;

  public Vibe() {
    this.radius = 25;
    this.center = new Point();
    this.center2 = new Point();
    this.color = Color.BLUE;
    this.color2 = Color.GREEN;
    this.color3 = Color.BLACK;
    this.color4 = Color.ORANGE;
    color2 = color2.darker();
    color4 = color4.darker();
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
    g.fillRect((int) (center.x+(radius/5)), (int) (center.y), (int) radius,  (int)radius);
    g.setColor(color2);
    g.fillOval((int) center2.x, (int) (center2.y -radius/1.5), (int) (radius*1.5), (int) (radius) );
    g.setColor(color3);
    g.fillOval((int) (center2.x+radius/4), (int) (center2.y -radius/3), 4, 4);
    g.fillOval((int) (center2.x+radius), (int) (center2.y -radius/3), 4, 4);
    g.setColor(color4);
    g.fillRect((int) (center2.x+radius/1.75), (int) (center2.y), (int) (radius/2), 5);
  }

  public Point getCenter() {
   return center;
  }
  //Durch clicken auf das Objekt wird der Status geändert. Dieser wechselt immer von 1 zu 2. Falls Status 0 wird nach dem Klick Status auf 1 gesetzt.
  public void userClicked(double atX, double atY){
    if(status == 1){
      status = 2;
    }
    else{
      status = 1;
    }
  }
 //falls s oder S eingegeben wird, so wird der status auf 0 gesetzt, d.h. das Objekt bewegt sich nicht mehr.
  public void userTyped(char key){
     if (key == 'S' || key == 's')
	status = 0;
  }

  //Abhängig vom Status wird das Objekt entweder von horizontal oder vertikal bewegt. Funktionsweise wie bei GoAndBack
  //Zum auf und ab bewegen des Kopfes wurde das Interval 20 festgelegt und es wird eine zweite center Variable genutzt um die Position
  //des Kopfes zu überprüfen und zu verändern.
  public void play(){
    if(status == 1){
       if ((center.x+radius)<=welt.getMax_X() && (center.x-radius)>=welt.getMin_X()){
         center.x = center.x + velocity;
         center2.x = center2.x + velocity;
        }
         else {
            velocity = (-velocity);
            center.x = center.x + velocity;
             center2.x = center2.x + velocity;
         }
    }
    else if(status == 2) {
       if ((center.y+radius)<=welt.getMax_Y() && (center.y-radius)>=welt.getMin_Y()){
          center.y = center.y + velocity;
          center2.y = center2.y + velocity;
        }
       else {
          velocity = (-velocity);
          center.y = center.y + velocity;
          center2.y = center2.y + velocity;
      }
    }
    if (center2.y <= (center.y) || center2.y >= (center.y+20)){
       bpm = -bpm;
    }
     center2.y = center2.y + bpm;
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

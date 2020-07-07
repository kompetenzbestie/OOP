package u7;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class MiniStein extends Stein implements Shape, Animation {

  public MiniStein(double x, double y) {
    super();
    this.radius = 10;
    this.center = new Point(x, y);
  }

  public void play()
  {
    if (center.y<welt.getMax_Y()) {
      center.y = center.y + (g*time);
      time++;
    }
  }

}

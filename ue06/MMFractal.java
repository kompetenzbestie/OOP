package u6;

public class MMFractal {

  public static void recDraw(double x, double y, double size, int depth) {

    if (depth > 0){
      StdDraw.filledCircle(x,y,size);

      recDraw(x+size, y+size, size/2, depth-1);
      recDraw(x+size, y-size, size/2, depth-1);
      recDraw(x-size, y-size, size/2, depth-1);
      recDraw(x-size, y+size, size/2, depth-1);
    }
  }

  public static void main(String[] args) {
    StdDraw.setPenColor(StdDraw.BLACK);
    recDraw(0.5,0.5,0.3,6);
  }
}

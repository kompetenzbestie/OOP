package u6;

public class Rectangle {

  public int x;
  public int y;
  public int width;
  public int height;

  public Rectangle(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public Rectangle() {
    this(0,0,100,100);
  }

  public int area() {
    return width*height;
  }

  public Rectangle clone() {
    return new Rectangle(this.x,this.y,this.width,this.height);
  }

  public boolean equal(Rectangle r) {
    return (r.x==this.x & r.y==this.y & r.width==this.width & r.height==this.height);
  }

  public int compareAreaTo(Rectangle r) {
    if (this.area()==r.area()) {
      return 0;
    } else if (this.area()<r.area()) {
      return -1;
    } else {
      return 1;
    }
  }

  public boolean contains(Rectangle r) {
    if (r.x<this.x | r.y<this.y | r.x>(this.x+this.width) | r.y>(this.y+this.height)) {
      return false;
    } else if ((this.width-(r.x-this.x)-r.width)>0 & (this.height-(r.y-this.y)-r.height)>0) {
      return true;
    } else {
      return false;
    }
  }

  public boolean overlaps(Rectangle r) {
    if (r.x>(this.x+this.width) | r.y>(this.y+this.height)) {
      return false;
    } else if (this.x>(r.x+r.width) | this.y>(r.y+r.height)) {
      return false;
    }
    return true;
  }

  public Rectangle section(Rectangle r) {
    // REST KOMMT NOCH
    if (!overlaps(r)) {
      return null;
    }

    int secX;
    int secY;
    int secW = 10;
    int secH = 10;

    if (this.contains(r)) {
      secX = r.x;
      secY = r.y;
      secW = r.width;
      secH = r.height;
    } else if (r.contains(this)) {
      secX = this.x;
      secY = this.y;
      secW = this.width;
      secH = this.height;
    } else if (r.x>this.x & r.y>this.y) {
      secX = r.x;
      secY = r.y;
    } else if (this.x>r.x & this.y>r.y) {
      secX = this.x;
      secY = this.y;
    } else if (r.x>this.x) {
      secX = r.x;
      secY = this.y;
    } else {
      secX = this.x;
      secY = r.y;
    }

    return new Rectangle(secX, secY, secW, secH);
  }

  public static Rectangle smallestBoundingRectangle(Rectangle[] recs) {

    int ltx = recs[0].x;
    int lty = recs[0].y;
    int rbx = recs[0].x + recs[0].width;
    int rby = recs[0].y + recs[0].height;

    for (Rectangle r : recs) {
      if (r.x < ltx) {
        ltx = r.x;
      }
      if (r.y < lty) {
        lty = r.y;
      }
      if (r.x+r.width > rbx) {
        rbx = r.x+r.width;
      }
      if (r.y+r.height > rby) {
        rby = r.y+r.height;
      }
    }

    return new Rectangle(ltx,lty,rbx-ltx,rby-lty);
  }
}

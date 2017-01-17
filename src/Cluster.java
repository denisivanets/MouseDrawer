import java.awt.*;

public class Cluster {
    private Color color = new Color(
            (int) (Math.random() * 255),
            (int) (Math.random() * 255),
            ( int) (Math.random() * 255)
    );
    private int X;
    private int Y;
    private int width;
    private int height;
    private Point center;

    public Cluster(int X, int Y, int width, int height){
        this.X = X;
        this.Y = Y;
        this.width = width;
        this.height = height;
//        center = new Point(((this.X + this.width)/2), (( this.Y + this.height)/2));
        center = new Point(this.width / 2, this.height /2 + this.Y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

}

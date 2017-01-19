import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cluster {
    private Color color = new Color(
            (int) (Math.random() * 255),
            (int) (Math.random() * 255),
            (int) (Math.random() * 255)
    );
    private int X;
    private int Y;
    private Set<Point> pointList;

    public Cluster(int X, int Y){
        this.X = X;
        this.Y = Y;
        pointList = new HashSet<>();
    }

    public void addPoint(Point point){
        pointList.add(point);
        point.setColor(color);
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

    public Set<Point> getPointList() {
        return pointList;
    }

    public void setPointList(Set<Point> pointList) {
        this.pointList = pointList;
    }

    @Override
    public boolean equals(Object clusterForCheck){
        Cluster cluster = (Cluster) clusterForCheck;
        if( Math.abs(cluster.getX() - X) > 2 || Math.abs(cluster.getY() - Y) > 2 ) return false;
        return true;
    }

    public void recalcCenter(){
        int sumX = 0;
        int sumY = 0;
        for(Point point : pointList){
            sumX += point.getX();
            sumY += point.getY();
        }
        int avgX = sumX / pointList.size();
        int avgY = sumY / pointList.size();
        X = avgX;
        Y = avgY;
    }
}

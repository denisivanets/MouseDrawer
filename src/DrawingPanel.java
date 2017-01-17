import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class DrawingPanel extends JPanel {
    private int K;
    private java.util.List<Cluster> clusters;
    private Point currentPoint;
    public DrawingPanel(){
        super();
        super.setSize(500,500);
        currentPoint = new Point(-5,-5);
        clusters = new ArrayList<>();
        K = (int) (Math.random() * 9) + 5;
        makeClusters();
    }

    @Override
    public void paint(Graphics g) {
            clusters.forEach(
                    (cluster) -> {
                        g.setColor(cluster.getColor());
                        g.drawRect(cluster.getCenter().getX(), cluster.getCenter().getY(), 7, 7);
                    }
        );
        if(currentPoint != null && clusters.size() > 0){
            g.setColor(analizeClusterColor());
            g.drawOval(currentPoint.getX(),currentPoint.getY(),5,5);
        }
    }


    public java.util.List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    private Color analizeClusterColor(){
        double min = this.getWidth() + this.getHeight();
        Color resultColor = clusters.get(0).getColor();
        for(Cluster cluster : clusters){
            double distance = countDistance(cluster.getCenter().getX(),cluster.getCenter().getY(),currentPoint.getX(),currentPoint.getY());
            if( distance < min){
                min = distance;
                resultColor = cluster.getColor();
            }
        }
        return resultColor;
    }

    private double countDistance(double x1, double y1, double x2, double y2){
        return Math.sqrt( Math.pow((x2 - x1),2) + Math.pow( (y2 - y1),2) );
    }

    private void makeClusters(){
            for( int i = 0; i < this.getHeight(); i += this.getHeight() / K ){
                Cluster cluster = new Cluster(0, i, this.getWidth(), this.getHeight() / K);
                clusters.add(cluster);
            }
        }

    public void clearPanel(Graphics g) {
        g.clearRect(-1, -1, this.getWidth(), this.getHeight());
    }
}


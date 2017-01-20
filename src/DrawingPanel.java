import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class DrawingPanel extends JPanel {

    private KMeans kMeans = new KMeans();

    public DrawingPanel(){
        super();
        super.setSize(500,500);
    }

    @Override
    public void paint(Graphics g) {
        kMeans.getCurrentClusterState().forEach(
                (cluster) -> {
                    g.setColor(cluster.getColor());
                    g.drawRect(cluster.getX(), cluster.getY(), 7, 7);
                }
        );

        PointStorage.getInstance().getPointList().forEach(
                (point) -> {
                    g.setColor(point.getColor());
                    g.drawOval(point.getX(), point.getY(), 5, 5);
                }
        );

    }

    public KMeans getkMeans() {
        return kMeans;
    }

    public void setkMeans(KMeans kMeans) {
        this.kMeans = kMeans;
    }

    public void clear(Graphics g){
        g.clearRect(0,0,this.getWidth(),this.getHeight());
    }
}


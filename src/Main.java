import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {
    private JFrame frame;
    private DrawingPanel pointsPanel = new DrawingPanel();

    public static void main(String[] args) {
        Point point1 = new Point(50,50);
        Point point2 = new Point(150,150);
        Point point3 = new Point(250,250);
        Point point4 = new Point(70,250);
        Point point5 = new Point(10,300);
        PointStorage.getInstance().getPointList().add(point1);
        PointStorage.getInstance().getPointList().add(point2);
        PointStorage.getInstance().getPointList().add(point3);
        PointStorage.getInstance().getPointList().add(point4);
        PointStorage.getInstance().getPointList().add(point5);
        new Main().start();
    }

    private void start(){
        frame = new JFrame("Points and mouse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.getContentPane().add(pointsPanel);
        Box box = new Box(BoxLayout.Y_AXIS);
        JButton clusterButton = new JButton("Cluster" );
        box.add(clusterButton);
        clusterButton.addActionListener(
                (event) -> {
                    pointsPanel.getkMeans().runAlgorithm(500, 500);
                    pointsPanel.update(pointsPanel.getGraphics());
                }
        );
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PointStorage.getInstance().getPointList().add(new Point(e.getX(), e.getY()));
                pointsPanel.update(pointsPanel.getGraphics());
            }
        };
        pointsPanel.addMouseListener(listener);
        frame.getContentPane().add(BorderLayout.EAST,box);
        frame.setVisible(true);
    }
}

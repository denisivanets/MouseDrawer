import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Main {
    private JFrame frame;
    private JPanel emptyPanel;
    private DrawingPanel pointsPanel = new DrawingPanel();

    public static void main(String[] args) {
        new Main().start();
    }

    private void start(){
        frame = new JFrame("Points and mouse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.getContentPane().add(pointsPanel);
        Box box = new Box(BoxLayout.Y_AXIS);
        JButton clearButton = new JButton("Clear" );
        box.add(clearButton);
        clearButton.addActionListener(
                (event) -> {
                    PointStorage.getInstance().getPointList().clear();
                    pointsPanel.clearPanel(pointsPanel.getGraphics());
                    pointsPanel.setCurrentPoint(new Point(-5,-5));
                    pointsPanel.setVisible(false);
                    pointsPanel.setVisible(true);
                }
        );
        frame.getContentPane().add(BorderLayout.EAST,box);
        frame.setVisible(true);
        MouseListener listener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pointsPanel.setCurrentPoint(new Point(e.getX(),e.getY()));
                pointsPanel.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
        pointsPanel.addMouseListener(listener);
    }
}

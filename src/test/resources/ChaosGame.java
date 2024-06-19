import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.Random;

public class ChaosGame {

    private ChaosGameGUI gui;
    private static final Random r = new Random();
    public Image offScreenImage;
    private Timer timer;
    private Point[] startPoints;
    private Point prevPoint;
    public Graphics g;
    private static final int RADIUS = 5;

    public ChaosGame() {    
        gui = new ChaosGameGUI(this);
        ActionListener guiRefresher = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getNextAnimationFrame();
            }
        };
        timer = new Timer(100, guiRefresher);
        g = gui.getGraphics();
        g.setColor(Color.WHITE);
    }

    public void beginSimulation(int numPoints) {
        startPoints = new Point[numPoints];
        for (int i = 0; i < numPoints; i++) {
            startPoints[i] = new Point(r.nextInt(ChaosGameGUI.WIDTH), r.nextInt(ChaosGameGUI.HEIGHT));
            g.fillOval(startPoints[i].x, startPoints[i].y, RADIUS, RADIUS);
        }
        prevPoint = new Point(r.nextInt(ChaosGameGUI.WIDTH), r.nextInt(ChaosGameGUI.HEIGHT));
        timer.start();
    }

    public void getNextAnimationFrame() {
        for(int i = 0 ; i < 100 ; i++) {
            int index = getClosestToStartPoints();
            prevPoint = getMidpoint(prevPoint, startPoints[index]);
            drawPoint(prevPoint); 
        }
    }

    private int getClosestToStartPoints() {
        int index = 0; // change to prevPoint
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < startPoints.length; i++) {
            double distance = getDistance(prevPoint, startPoints[i]);
            if (distance < minDistance) {
                minDistance = distance;
                index = i;
            }
        }
        return index;
    }

    private double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    }

    private Point getMidpoint(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    private void drawPoint(Point p) {
        g.fillOval(p.y, p.x, RADIUS, RADIUS);
    }



}

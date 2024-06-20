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
    private Color[] startPointColors;
    private Point prevPoint;
    public Graphics g;
    private static final int RADIUS = 3;

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

    public void beginSimulation(int numPoints, boolean random) {
        startPoints = new Point[numPoints];
        startPointColors = new Color[numPoints];
        startPoints[0] = new Point(400, 50);
        startPoints[1] = new Point(100, 600);
        startPoints[2] = new Point(700, 600);
        for (int i = 0; i < numPoints; i++) {
            if(random) {
                startPoints[i] = new Point(r.nextInt(ChaosGameGUI.WIDTH), r.nextInt(ChaosGameGUI.HEIGHT)-50);
            }
            startPointColors[i] = simulateRandomColor();
            g.setColor(startPointColors[i]);
            g.fillOval(startPoints[i].x, startPoints[i].y, RADIUS, RADIUS);
        }
        prevPoint = new Point(startPoints[0].x, startPoints[0].y);
        timer.start();
    }

    public void endSimulation() {
        timer.stop();
        clearScreen();
    }

    public void clearScreen() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ChaosGameGUI.WIDTH, ChaosGameGUI.HEIGHT);
    }

    public void getNextAnimationFrame() {
        for(int i = 0 ; i < 100 ; i++) {
            simulateSinglePoint();
        }
    }

    private void simulateSinglePoint() {
        int index = r.nextInt(startPoints.length);
        prevPoint = getMidpoint(prevPoint, startPoints[index]);
        drawPoint(prevPoint, startPointColors[index]);
    }

    private Color simulateRandomColor() {
        return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }


    private double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow((p1.y - p2.y), 2));
    }

    private Point getMidpoint(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    private void drawPoint(Point p, Color c) {
        g.setColor(c);
        g.fillOval(p.x, p.y, RADIUS, RADIUS);
    }



}

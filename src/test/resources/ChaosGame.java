import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.Random;

public class ChaosGame {

    private ChaosGameGUI gui;
    private static final Random r = new Random();
    private Timer timer;
    public Graphics g;
    private static final int RADIUS = 2;

    private Fractal currSimulation;

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
        if(numPoints == 3) {
            currSimulation = new SerpinskiTriangle(this, random);
        } else if(numPoints == 4) {
            //currSimulation = new SerpinskiSquare(random);
        } else if(numPoints == 5) {
            //currSimulation = new SerpinskiPentagon(random);
        
        }

        currSimulation.initialiseStartPoints(random);
        currSimulation.initialiseStartPointColours();
        for (int i = 0; i < numPoints; i++) {
            g.setColor(currSimulation.getStartPointColors()[i]);
            g.fillOval(currSimulation.getStartPoints()[i].x, currSimulation.getStartPoints()[i].y, RADIUS, RADIUS);
        }
        timer.start();
    }

    public void getNextAnimationFrame() {
        for(int i = 0 ; i < 100 ; i++) {
            currSimulation.simulateSinglePoint();
        }
    }

    public void endSimulation() {
        timer.stop();
        clearScreen();
    }

    public void clearScreen() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ChaosGameGUI.WIDTH, ChaosGameGUI.HEIGHT);
    }

    

    public void drawPoint(Point p, Color c) {
        g.setColor(c);
        g.fillOval(p.x, p.y, RADIUS, RADIUS);
    }

    // private void squareSimulation() {
    //     startPoints = new Point[4];
    //     startPoints[0] = new Point(100, 100);
    //     startPoints[1] = new Point(700, 100);
    //     startPoints[2] = new Point(700, 600);
    //     startPoints[3] = new Point(100, 600);
    // }

    // private void pentagonSimulation() {
    //     startPoints = new Point[5];
    //     startPoints[0] = new Point(400, 50);
    //     startPoints[1] = new Point(100, 250);
    //     startPoints[2] = new Point(250, 600);
    //     startPoints[3] = new Point(550, 600);
    //     startPoints[4] = new Point(700, 250);
    // }



}

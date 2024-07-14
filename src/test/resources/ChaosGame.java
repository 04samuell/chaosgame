import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class ChaosGame {

    private ChaosGameGUI gui;
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

    public void beginSimulation(String fractal, boolean random) {
        if(fractal.equals("Serpinskis Triangle")) {
            currSimulation = new SerpinskiTriangle(this, random);
        } else if(fractal.equals("Serpinskis Pentagon")) {
            currSimulation = new SerpinskisPentagon(this, random);
        } else if(fractal.equals("Barnsleys Fern")) {
            currSimulation = new BarnsleysFern(this, random);
        }

        currSimulation.initialiseStartPoints();
        currSimulation.initialiseStartPointColours();
        timer.start();
    }

    public void getNextAnimationFrame() {
        int iterations = 1000;
        if(currSimulation.getClass() == BarnsleysFern.class) {
            iterations = 1000;
        }
        for(int i = 0 ; i < iterations ; i++) {
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

}

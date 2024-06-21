import java.awt.*;
import java.util.Random;


public class SerpinskiTriangle extends Fractal {

    private Point[] startPoints;
    private Color[] startPointColors;
    private boolean random;
    private Point prevPoint;
    private ChaosGame game;
    private static final Random r = new Random();

    public SerpinskiTriangle(ChaosGame game, boolean random) {
        this.game = game;
        this.random = random;
    }

    @Override
    public Point[] initialiseStartPoints(boolean random) {
        startPoints = new Point[3];
        if(!random) {
            startPoints[0] = new Point(400, 50);
            startPoints[1] = new Point(100, 600);
            startPoints[2] = new Point(700, 600);
        } else {
            for(int i = 0 ; i < 3 ; i++) {
                startPoints[i] = new Point(r.nextInt(ChaosGameGUI.WIDTH), r.nextInt(ChaosGameGUI.HEIGHT)-50);
            }
        }
        prevPoint = startPoints[0];
        return startPoints; //the first prevPoint
    }

    public void initialiseStartPointColours() {
        startPointColors = new Color[startPoints.length];
        for(int i = 0 ; i < startPointColors.length ; i++) {
            startPointColors[i] = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        }
    }
        

    @Override
    public void simulateSinglePoint() {
        int index = r.nextInt(startPoints.length);
        prevPoint = getMidpoint(prevPoint, startPoints[index]);
        game.drawPoint(prevPoint, startPointColors[index]);
        
    }
    
}

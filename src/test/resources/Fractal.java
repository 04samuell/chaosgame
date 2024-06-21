import java.awt.*;
import java.util.Random;

public abstract class Fractal {

    private Point[] startPoints;
    private Color[] startPointColors;
    private Point prevPoint;

    private boolean random;
    private ChaosGame game;

    public static final Random r = new Random();

    public Fractal(ChaosGame game, boolean random) {
        this.game = game;
        this.random = random;
    }
    
    public abstract void initialiseStartPoints();

    public abstract void simulateSinglePoint();

    public void initialiseStartPointColours() {
        Color[] startPointColors = new Color[startPoints.length];
        for(int i = 0 ; i < startPointColors.length ; i++) {
            startPointColors[i] = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        }
        this.startPointColors = startPointColors;
    }

    public Point getMidpoint(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    public Point[] getStartPoints() {
        return startPoints;
    }

    public Color[] getStartPointColors() {
        return startPointColors;
    }

    public void setStartPoints(Point[] startPoints) {
        this.startPoints = startPoints;
    }

    public void setStartPointColors(Color[] startPointColors) {
        this.startPointColors = startPointColors;
    }

    public Point getPrevPoint() {
        return prevPoint;
    }

    public void setPrevPoint(Point prevPoint) {
        this.prevPoint = prevPoint;
    }

    public ChaosGame getGame() {
        return game;
    }

    public boolean getRandom() {
        return random;
    }

}

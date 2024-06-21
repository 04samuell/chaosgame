import java.awt.*;
import java.util.Random;

public abstract class Fractal {

    private Point[] startPoints = new Point[1];
    private Color[] startPointColors = new Color[1];
    private static Random r = new Random();
    
    public abstract Point[] initialiseStartPoints(boolean random);

    public abstract void simulateSinglePoint();

    public abstract void initialiseStartPointColours();

    public Point getMidpoint(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    public Point[] getStartPoints() {
        return startPoints;
    }

    public Color[] getStartPointColors() {
        return startPointColors;
    }

}

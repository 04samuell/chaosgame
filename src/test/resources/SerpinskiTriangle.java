import java.awt.*;

public class SerpinskiTriangle implements Fractal {

    private Point[] startPoints;
    private Color[] startPointColors;
    private boolean random;

    public SerpinskiTriangle(boolean random) {
        this.random = random;
    }

    @Override
    public void initialiseStartPoints(boolean random) {
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

        //return start point for prevPoint in ChaosGame?
        
    }

    @Override
    public void simulateSinglePoint() {
        // TODO Auto-generated method stub
        
    }

    public Point[] getStartPoints() {
        return startPoints;
    }

    public Color[] getStartPointColors() {
        return startPointColors;
    }

    
    
}

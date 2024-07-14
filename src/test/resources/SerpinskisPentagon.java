import java.awt.*;

public class SerpinskisPentagon extends Fractal {

    private int prevVertice = 0;
    private Point prevPoint;

    public SerpinskisPentagon(ChaosGame game, boolean random) {
        super(game, random);
    }

    @Override
    public void initialiseStartPoints() {
        Point[] startPoints = new Point[5];
        if(!super.getRandom()) {
            startPoints[0] = new Point(400, 50);
            startPoints[1] = new Point(100, 250);
            startPoints[2] = new Point(250, 600);
            startPoints[3] = new Point(550, 600);
            startPoints[4] = new Point(700, 250);
        } else {
            for(int i = 0 ; i < 5 ; i++) {
                startPoints[i] = new Point(r.nextInt(ChaosGameGUI.WIDTH), r.nextInt(ChaosGameGUI.HEIGHT));
            }
        }
        prevPoint = startPoints[0];
        super.setStartPoints(startPoints);
        
    }

    @Override
    public void simulateSinglePoint() {
        Point nextPoint = new Point();
        int nextVertice = r.nextInt(5);
        if(nextVertice != prevVertice) {
            nextPoint = super.getMidpoint(super.getStartPoints()[nextVertice], prevPoint);
            super.getGame().drawPoint(nextPoint, super.getStartPointColors()[nextVertice]);
        }
        prevVertice = nextVertice;
        prevPoint = nextPoint;
    }

    
    
}

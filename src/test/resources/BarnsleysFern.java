import java.awt.*;

public class BarnsleysFern extends Fractal {

    private static final double F1 = 0.01;
    private static final double F2 = 0.07;
    private static final double F3 = 0.07;


    public BarnsleysFern(ChaosGame game, boolean random) {
        super(game, random);
    }

    @Override
    public void initialiseStartPoints() {
        Point[] startPoints = new Point[1];
        if(super.getRandom()) {
            startPoints[0] = new Point(r.nextInt(ChaosGameGUI.WIDTH), r.nextInt(ChaosGameGUI.HEIGHT)-50);
        } else {
            startPoints[0] = new Point(400, 400);
        }
        super.setStartPoints(startPoints);
        super.setPrevPoint(startPoints[0]);
    }

    @Override
    public void simulateSinglePoint() {
        double prob = r.nextDouble();
        Point nextPoint = new Point();
        int x = super.getPrevPoint().x;
        int y = super.getPrevPoint().y;
        if(prob < F1) {
            nextPoint = simulateF1(x, y);
        } else if(prob < F1 + F2) {
            nextPoint = simulateF2(x, y);
        } else if(prob < F1 + F2 + F3) {
            nextPoint = simulateF3(x, y);
        } else { //occurs with probability 0.85
            nextPoint = simulateF4(x, y);
        }

        super.setPrevPoint(nextPoint);
        super.getGame().drawPoint(nextPoint, super.getStartPointColors()[0]);
        
    }

    public Point simulateF1(int prevX, int prevY) {
        int x = prevX;
        int y = (int) 0.16 * prevY;
        return new Point(x, y);
    }

    public Point simulateF2(int prevX, int prevY) {
        int x = (int) (0.2 * prevX - 0.26 * prevY);
        int y = (int) (0.23 * prevX + 0.22 * prevY + 1.6);
        return new Point(x, y);
    }

    public Point simulateF3(int prevX, int prevY) {
        int x = (int) (-0.15 * prevX + 0.28 * prevY);
        int y = (int) (0.26 * prevX + 0.24 * prevY + 0.44);
        return new Point(x, y);
    }

    public Point simulateF4(int prevX, int prevY) {
        int x = (int) (0.85 * prevX + 0.04 * prevY);
        int y = (int) (-0.04 * prevX + 0.85 * prevY + 1.6);
        return new Point(x, y);
    }

    
    
}

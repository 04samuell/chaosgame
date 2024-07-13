import java.awt.*;

public class BarnsleysFern extends Fractal {

    static final double F1 = 0.01;
    static final double F2 = 0.07;
    static final double F3 = 0.07;

    static final double WIDTH = ChaosGameGUI.WIDTH;
    static final double HEIGHT = ChaosGameGUI.HEIGHT;

    static final double minX = -2.1820;
    static final double maxX = 2.6558;
    static final double minY = 0;
    static final double maxY = 9.9983;

    private double prevX = 0;
    private double prevY = 0;

    public BarnsleysFern(ChaosGame game, boolean random) {
        super(game, random);
    }

    @Override
    public void initialiseStartPoints() {
        Point[] startPoints = new Point[1];
        if(super.getRandom()) {
            startPoints[0] = new Point(r.nextInt(ChaosGameGUI.WIDTH), r.nextInt(ChaosGameGUI.HEIGHT)-50);
        } else {
            startPoints[0] = new Point(0, 0);
        }
        super.setStartPoints(startPoints);
        super.setPrevPoint(startPoints[0]);
    }

    @Override
    public void simulateSinglePoint() {
        double prob = r.nextDouble();
        Point nextPoint = new Point();
        // int x = super.getPrevPoint().x;
        // int y = super.getPrevPoint().y;
        if(prob < F1) {
            nextPoint = simulateF1();
        } else if(prob < F1 + F2) {
            nextPoint = simulateF2();
        } else if(prob < F1 + F2 + F3) {
            nextPoint = simulateF3();
        } else { //occurs with probability 0.85
            nextPoint = simulateF4();
        }

        System.out.println(nextPoint.x + " " + nextPoint.y);
        //nextPoint.x = mapToScreenX(nextPoint.x);
        //nextPoint.y = mapToScreenY(nextPoint.y);
        //super.setPrevPoint(nextPoint);
        super.getGame().drawPoint(nextPoint, super.getStartPointColors()[0]);
    }

    private Point simulateF1() {
        double x = 0;
        double y = (int) 0.16 * this.prevY;

        prevX = x;
        prevY = y;
        return transform(x, y);
    }

    private Point simulateF2() {
        double x = 0.2 * prevX - 0.26 * prevY;
        double y = 0.23 * prevX + 0.22 * prevY + 1.6;

        prevX = x;
        prevY = y;
        return transform(x, y);
    }

    private Point simulateF3() {
        double x = -0.15 * prevX + 0.28 * prevY;
        double y = 0.26 * prevX + 0.24 * prevY + 0.44;
        
        prevX = x;
        prevY = y;
        return transform(x, y);
    }

    private Point simulateF4() {
        double x = 0.85 * prevX + 0.04 * prevY;
        double y = -0.04 * prevX + 0.85 * prevY + 1.6;

        prevX = x;
        prevY = y;
        return transform(x, y);
    }

    private Point transform(double x, double y) {
        double normX = (x - minX) / (maxX - minX);
        double normY = (y - minY) / (maxY - minY);

        int screenX = (int) (normX * (WIDTH - 1));
        int screenY = (int) ((1 - normY) * (HEIGHT - 1)); 

        return new Point(screenX, screenY);
    }
    
}

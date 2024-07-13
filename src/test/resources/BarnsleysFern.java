import java.awt.*;

public class BarnsleysFern extends Fractal {

    // Funciton Probabilities
    static final double F1 = 0.01;
    static final double F2 = 0.07;
    static final double F3 = 0.07;

    // Screen Dimensions
    static final double WIDTH = ChaosGameGUI.WIDTH;
    static final double HEIGHT = ChaosGameGUI.HEIGHT;

    // Fractal Dimensions
    static final double minX = -2.1820;
    static final double maxX = 2.6558;
    static final double minY = 0;
    static final double maxY = 9.9983;

    // Raw values of the previous point
    private double prevX = 0;
    private double prevY = 0;

    // Scale factor & angle (only applicable if random points are selected)
    private double scale = 1.0;
    private double angle = 0; // radians

    public BarnsleysFern(ChaosGame game, boolean random) {
        super(game, random);
    }

    @Override
    public void initialiseStartPoints() {
        Point[] startPoints = new Point[1];
        if(super.getRandom()) {
            startPoints[0] = new Point(r.nextInt(ChaosGameGUI.WIDTH), r.nextInt(ChaosGameGUI.HEIGHT)-50);
            angle = Math.toRadians(r.nextInt(360));
            scale = r.nextDouble();
            if(scale < 0.2) scale = 0.2;
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
        if(prob < F1) {
            nextPoint = simulateF1();
        } else if(prob < F1 + F2) {
            nextPoint = simulateF2();
        } else if(prob < F1 + F2 + F3) {
            nextPoint = simulateF3();
        } else { //occurs with probability 0.85
            nextPoint = simulateF4();
        }

        super.getGame().drawPoint(nextPoint, super.getStartPointColors()[0]);
    }

    /**
     * Simulate this point with probability 0.01
     * @return the new point
     */
    private Point simulateF1() {
        double x = 0;
        double y = (int) 0.16 * this.prevY;

        prevX = x;
        prevY = y;
        return transform(x, y);
    }

    /**
     * Simulate this point with probability 0.07
     * @return the new point
     */
    private Point simulateF2() {
        double x = 0.2 * prevX - 0.26 * prevY;
        double y = 0.23 * prevX + 0.22 * prevY + 1.6;

        prevX = x;
        prevY = y;
        return transform(x, y);
    }

    /**
     * Simulate this point with probability 0.07
     * @return the new point
     */
    private Point simulateF3() {
        double x = -0.15 * prevX + 0.28 * prevY;
        double y = 0.26 * prevX + 0.24 * prevY + 0.44;
        
        prevX = x;
        prevY = y;
        return transform(x, y);
    }

    /**
     * Simulate this point with probability 0.85
     * @return the new point
     */
    private Point simulateF4() {
        double x = 0.85 * prevX + 0.04 * prevY;
        double y = -0.04 * prevX + 0.85 * prevY + 1.6;

        prevX = x;
        prevY = y;
        return transform(x, y);
    }

    /**
     * Method to transform the  raw x and y values to screen coordinates
     * @param x the raw x value
     * @param y the raw y value
     * @return the transformed point
     */
    private Point transform(double x, double y) {
        double normX = (x - minX) / (maxX - minX);
        double normY = (y - minY) / (maxY - minY);

        normX = (normX - 0.5) * scale + 0.5;
        normY = (normY - 0.5) * scale + 0.5;

        double rotatedX = normX * Math.cos(angle) - normY * Math.sin(angle);
        double rotatedY = normX * Math.sin(angle) + normY * Math.cos(angle);
            
        if(angle != 0) {
            rotatedX = (rotatedX + 1) / 2;
            rotatedY = (rotatedY + 1) / 2;
        }


        int screenX = (int) (rotatedX * (WIDTH - 1));
        int screenY = (int) ((1 - rotatedY) * (HEIGHT - 1)); 

        return new Point(screenX, screenY);
    }
    
}

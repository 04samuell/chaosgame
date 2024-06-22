import java.awt.*;
import java.util.Arrays;

public class SerpinskiCarpet extends Fractal {

    private Point[] corners;
    private Color[] cornerColors;

    private Point prevPoint;

    public SerpinskiCarpet(ChaosGame game, boolean random) {
        super(game, random);
        super.setStartPoints(new Point[4]);
        super.setStartPointColors(new Color[4]);
    }

    @Override
    public void initialiseStartPoints() {
        Point[] startPoints = new Point[4];
        if(!super.getRandom()) {
            startPoints[0] = new Point(100, 100);
            startPoints[1] = new Point(700, 100);
            startPoints[2] = new Point(700, 600);
            startPoints[3] = new Point(100, 600);
        } else {
            for(int i = 0 ; i < startPoints.length ; i++) {
                startPoints[i] = new Point(r.nextInt(ChaosGameGUI.WIDTH), r.nextInt(ChaosGameGUI.HEIGHT)-50);
            }
        }
        super.setStartPoints(startPoints);
        createVertices();
        createVerticesColour();
        super.setPrevPoint(super.getStartPoints()[0]);
    }

    @Override
    public void simulateSinglePoint() {
        int vertex = r.nextInt(corners.length);
        Point nextPoint = calculateNextPoint(vertex); 
        super.setPrevPoint(nextPoint); // set the new prevPoint to be the midpoint
        super.getGame().drawPoint(nextPoint, cornerColors[vertex]); //draw the point
    }

    /**
     * Calculates the next point to draw
     * 
     * New Point is determined by P_{i+1} = (2/3)P_i + (1/3)P_t
     * Where P_i is the current point and P_t is the target point
     * @param vertex
     * @return
     */
    private Point calculateNextPoint(int vertex) {
        int xt = corners[vertex].x;
        int yt = corners[vertex].y;
        int xi = super.getPrevPoint().x;
        int yi = super.getPrevPoint().x;

        int nextX = (int) ((2.0/3)*xi + (1.0/3)*xt);
        int nextY = (int) ((2.0/3)*yi + (1.0/3)*yt);

        return new Point(nextX, nextY);
    }

    public void createCorners() {
        corners = new Point[32];
        for(int i = 0 ; i < super.getStartPoints().length ; i++) {
            corners[i] = super.getStartPoints()[i];
        }
        corners[4] = getThirdOfWay(corners[0], corners[1]);
        corners[5] = getThirdOfWay(corners[1], corners[2]);
        corners[6] = getThirdOfWay(corners[2], corners[3]);
        corners[7] = getThirdOfWay(corners[3], corners[0]);
        addMissingCorners();
    }

    private Point getThirdOfWay(Point p1, Point p2) {
        int x1 = p1.x; int y1 = p1.y;
        int x2 = p2.x; int y2 = p2.y;
        int x = (int) (x1 + (1.0/3)*(x2 - x1));
        int y = (int) (y1 + (1.0/3)*(y2 - y1));
        return new Point(x, y);
    }

    private void addMissingCorners() {
        for(int i = 0 ; i < 8 ; i++) {

        }
    }

    public void createVerticesColour() {
        cornerColors = new Color[8];
        for(int i = 0 ; i < cornerColors.length ; i++) {
            cornerColors[i] = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        }
    }

    public void setCorners(Point[] corners) {
        this.corners = corners;
    }

    public void setCornerColors(Color[] corneColors) {
        this.cornerColors = corneColors;
    } 

    
    
}

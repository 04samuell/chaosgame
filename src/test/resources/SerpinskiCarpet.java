import java.awt.*;
import java.util.Arrays;

public class SerpinskiCarpet extends Fractal {

    private Point[] corners;
    private Color[] cornerColors;

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
            startPoints[2] = new Point(100, 700);
            startPoints[3] = new Point(700, 700);
        } else {
            for(int i = 0 ; i < startPoints.length ; i++) {
                startPoints[i] = new Point(r.nextInt(ChaosGameGUI.WIDTH), r.nextInt(ChaosGameGUI.HEIGHT)-50);
            }
        }
        super.setStartPoints(startPoints);
        createCorners();
        createCornersColor();
        super.setPrevPoint(super.getStartPoints()[0]);
        //System.out.println(super.getPrevPoint());
    }

    @Override
    public void simulateSinglePoint() {
        int vertex = r.nextInt(corners.length);
        Point nextPoint = calculateNextPoint(vertex); 
        super.setPrevPoint(nextPoint); // set the new prevPoint to be the midpoint
        super.getGame().drawPoint(nextPoint, cornerColors[vertex]); //draw the point
    }

    private Point calculateNextPoint(int vertex) {
        Point prevPoint = super.getPrevPoint();
        Point targetPoint = corners[vertex];
        int nextX = getThirdOfWayX(prevPoint, targetPoint);
        int nextY = getThirdOfWayY(prevPoint, targetPoint);

        return new Point(nextX, nextY);
    }

    public void createCorners() {
        Point[] startPoints = super.getStartPoints(); 
        corners = new Point[16];
        int xInitial = startPoints[0].x;
        int yInitial = startPoints[0].y;
        int xIncrement = getThirdOfWayX(startPoints[0], startPoints[1]) - startPoints[0].x;
        int yIncrement = getThirdOfWayY(startPoints[0], startPoints[2]) - startPoints[0].y;
        //System.out.println(xIncrement + " " + yIncrement);
        int index = 0;
        for(int i = 0 ; i < 4 ; i++) {
            for(int j = 0 ; j < 4 ; j++) {
                corners[index] = new Point(xInitial + i*xIncrement, yInitial + j*yIncrement);
                index++;
            }
        }
    }

    private int getThirdOfWayX(Point p1, Point p2) {
        int x1 = p1.x; 
        int x2 = p2.x; 
        int x = (int) (x1 + (1.0/3)*(x2 - x1));
        return x;
    }
    private int getThirdOfWayY(Point p1, Point p2) {
        int y1 = p1.y;
        int y2 = p2.y;
        int y = (int) (y1 + (1.0/3)*(y2 - y1));
        return y;
    }

    public void createCornersColor() {
        cornerColors = new Color[corners.length];
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

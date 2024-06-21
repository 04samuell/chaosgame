import java.awt.*;

public class SerpinskiCarpet extends Fractal {

    private Point[] vertices;

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
        super.setPrevPoint(startPoints[0]);
        createVertices();
    }

    @Override
    public void simulateSinglePoint() {
        int vertex = r.nextInt(vertices.length);

        Point prevPoint = super.getPrevPoint();
        Point[] startPoints = super.getStartPoints();
        Color[] startPointColors = super.getStartPointColors();

        Point nextPoint = calculateNextPoint(vertex); 

        super.setPrevPoint(nextPoint); // set the new prevPoint to be the midpoint
        super.getGame().drawPoint(prevPoint, startPointColors[vertex]); //draw the point
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
        int xt = vertices[vertex].x;
        int yt = vertices[vertex].y;
        int xi = super.getPrevPoint().x;
        int yi = super.getPrevPoint().y;    

        int nextX = (2/3)*xi + (1/3)*xt;
        int nextY = (2/3)*yi + (1/3)*yt;

        return new Point(nextX, nextY);
    }

    public void createVertices() {
        Point[] vertices = new Point[8];
        for(int i = 0 ; i < super.getStartPoints().length ; i++) {
            vertices[i] = super.getStartPoints()[i];
        }
        vertices[4] = super.getMidpoint(vertices[0], vertices[1]);
        vertices[5] = super.getMidpoint(vertices[1], vertices[2]);
        vertices[6] = super.getMidpoint(vertices[2], vertices[3]);
        vertices[7] = super.getMidpoint(vertices[3], vertices[0]);
        setVertices(vertices);
    }

    public void setVertices(Point[] vertices) {
        this.vertices = vertices;
    }

    
    
}

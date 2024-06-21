import java.awt.*;

public class SerpinskiTriangle extends Fractal {

    public SerpinskiTriangle(ChaosGame game, boolean random) {
        super(game, random);
        super.setStartPoints(new Point[3]);
        super.setStartPointColors(new Color[3]);
    }

    @Override
    public void initialiseStartPoints(boolean random) {
        Point[] startPoints = new Point[3];
        if(!random) {
            startPoints[0] = new Point(400, 50);
            startPoints[1] = new Point(100, 600);
            startPoints[2] = new Point(700, 600);
        } else {
            for(int i = 0 ; i < 3 ; i++) {
                startPoints[i] = new Point(r.nextInt(ChaosGameGUI.WIDTH), r.nextInt(ChaosGameGUI.HEIGHT)-50);
            }
        }
        super.setStartPoints(startPoints);
        super.setPrevPoint(startPoints[0]);
    }

    public void initialiseStartPointColours() {
        Color[] startPointColors = new Color[3];
        for(int i = 0 ; i < startPointColors.length ; i++) {
            startPointColors[i] = new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
        }
        super.setStartPointColors(startPointColors);
    }
        

    @Override
    public void simulateSinglePoint() {
        int index = r.nextInt(super.getStartPoints().length);

        Point prevPoint = super.getPrevPoint();
        Point[] startPoints = super.getStartPoints();
        Color[] startPointColors = super.getStartPointColors();

        super.setPrevPoint(getMidpoint(prevPoint, startPoints[index])); // set the new prevPoint to be the midpoint
        super.getGame().drawPoint(prevPoint, startPointColors[index]); //draw the point
        
    }
    
}

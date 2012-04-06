package cmcculloch1.csc212;

import android.graphics.Canvas;

public class ParabolaEq extends Equation {

	//TODO: Need to add color schemes later. Schemes will be stored in XML.
	public ParabolaEq(double sizeRatio, double offsetX,	double offsetY,
			double startX, double endX, double stepX, String name) {
		super(sizeRatio, offsetX, offsetY, startX, endX, stepX, name);
		Point2D p = new Point2D();
		for(double x = startX; x <= endX; x += stepX) {
			p.setX(x);
			p.setY(x * x * x);
			this.data.add(p);
			System.out.println(Double.toString(p.getX()) + ", " +
								Double.toString(p.getY())); 
		}
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}
}

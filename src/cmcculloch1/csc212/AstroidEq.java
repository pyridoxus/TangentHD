package cmcculloch1.csc212;

import android.graphics.Canvas;

public class AstroidEq extends Equation {

	//TODO: Need to add color schemes later. Schemes will be stored in XML.
	public AstroidEq(double sizeRatio, double offsetX, double offsetY,
			double startX, double endX, double stepX, String name) {
		super(sizeRatio, offsetX, offsetY, startX, endX, stepX, name);
		Point2D p = new Point2D();
		for(double x = startX; x <= endX; x += stepX) {
			p.setX(x);
			p.setY(Math.pow(1.0 - Math.pow(x, (2.0 / 3.0)), 1.5));
			this.data.add(p);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}
}

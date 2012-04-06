package cmcculloch1.csc212;

import android.graphics.Color;
import android.graphics.Paint.Style;

public class ParabolaEq extends Equation {

	//TODO: Need to add color schemes later. Schemes will be stored in XML.
	public ParabolaEq(double sizeRatio, double offsetX,	double offsetY,
			double startX, double endX, double stepX, String name) {
		super(sizeRatio, offsetX, offsetY, startX, endX, stepX, name);
		double x, y;
		for(x = startX; x <= endX; x += stepX) {
			y = Math.abs(x * x - 1.0);
			this.data.add(new Point2D(x, y));
		}
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(2);
		paint.setStyle(Style.STROKE);
	}
}

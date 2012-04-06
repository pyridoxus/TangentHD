package cmcculloch1.csc212;

import android.graphics.Color;
import android.graphics.Paint.Style;

public class PowerEq extends Equation {

	//TODO: Need to add color schemes later. Schemes will be stored in XML.
	public PowerEq(float sizeRatio, float offsetX, float offsetY,
					float startX, float endX, float stepX, String name) {
		super(sizeRatio, offsetX, offsetY, startX, endX, stepX, name);
		double x, y;
		for(x = startX; x <= endX; x += stepX) {
			y = x * x * x;
			this.data.add(new Point2D(x, y));
		}
		paint.setColor(Color.BLUE);
		paint.setStrokeWidth(2);
		paint.setStyle(Style.STROKE);
	}
}

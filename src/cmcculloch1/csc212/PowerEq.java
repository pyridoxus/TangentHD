package cmcculloch1.csc212;

import android.graphics.Canvas;

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
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}
}

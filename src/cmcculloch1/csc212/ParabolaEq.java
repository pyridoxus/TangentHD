package cmcculloch1.csc212;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class ParabolaEq extends Equation {

	//TODO: Need to add color schemes later. Schemes will be stored in XML.
	public ParabolaEq(Bitmap bmp, float sizeRatio, float offsetX,
			float offsetY, float startX, float endX, float stepX, String name) {
		super(bmp, sizeRatio, offsetX, offsetY, startX, endX, stepX, name);
		Point2D p = new Point2D();
		for(float x = startX; x <= endX; x += stepX) {
			p.setX(x);
			p.setY(x * x * x);
			this.data.add(p);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}
}

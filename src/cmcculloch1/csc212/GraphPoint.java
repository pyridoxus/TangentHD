package cmcculloch1.csc212;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;

public class GraphPoint extends GraphInterpolate {
	Point2D p;
	public GraphPoint(double sizeRatio, double offsetX, double offsetY) {
		super(sizeRatio, offsetX, offsetY);
		init();
	}
	
	private void init() {
		this.p = new Point2D(0, 0);
		paint.setStrokeWidth(2);
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL_AND_STROKE);
	}

	public void setColor(int color) {
		paint.setColor(color);
	}

	public int getColor() {
		return paint.getColor();
	}

	public Point2D getP() {
		return new Point2D(p);
	}

	public void setP(Point2D p) {
		this.p = new Point2D(p);
	}

	public void draw(Canvas canvas) {
		Point2D s = new Point2D(0, 0);
		graphToBmp(p.getX(), p.getY());
		s.setX(getInterpX());
		s.setY(getInterpY());
		//TODO: Will circle dimensions into resource later...
		canvas.drawCircle((float)s.getX(), (float)s.getY(), 4, paint);
	}
}

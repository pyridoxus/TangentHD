package cmcculloch1.csc212;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;

public class Grid extends GraphInterpolate {
	private float borderE, borderS, borderN, borderW;
	
	public Grid(double sizeRatio, double offsetX, double offsetY) {
		super(sizeRatio, offsetX, offsetY);
		paint.setStyle(Style.STROKE);
	}

	public void draw(Canvas canvas) {
		paint.setColor(Color.WHITE);
		canvas.drawPaint(paint);
		System.out.println("Drawing grid...");
		
		borderE = (float)(bmp.getWidth() / 2.0 / sizeRatio);
		float temp = borderE % 1;
		borderE = borderE - temp + 1;
		borderW = -borderE;
		borderN = (float)(bmp.getHeight() / 2.0 / sizeRatio);
		temp = borderN % 1;
		borderN = borderN - temp + 1;
		borderS = -borderN;
		paint.setColor(Color.BLACK);
		drawX(canvas);
		drawY(canvas);
	}

	public void drawX(Canvas canvas) {
		Point2D s = new Point2D();
		Point2D t = new Point2D();
		for(float x = borderW; x <= borderE; x += 1.0 ) {
			graphToBmp(x, borderN);
			s.setX(getInterpX());
			s.setY(getInterpY());
			graphToBmp(x, borderS);
			t.setX(getInterpX());
			t.setY(getInterpY());
			if(x == 0) paint.setStrokeWidth(2);
			else paint.setStrokeWidth(1);
			canvas.drawLine((float)s.getX(), (float)s.getY(),
					(float)t.getX(), (float)t.getY(), paint);
		}
	}
	
	public void drawY(Canvas canvas) {
		Point2D s = new Point2D();
		Point2D t = new Point2D();
		for(float y = borderS; y <= borderN; y += 1.0 ) {
			graphToBmp(borderW, y);
			s.setX(getInterpX());
			s.setY(getInterpY());
			graphToBmp(borderE, y);
			t.setX(getInterpX());
			t.setY(getInterpY());
			if(y == 0) paint.setStrokeWidth(2);
			else paint.setStrokeWidth(1);
			canvas.drawLine((float)s.getX(), (float)s.getY(),
					(float)t.getX(), (float)t.getY(), paint);
		}
	}
	
	@Override
	public String toString() {
		String s;
		s = "--- Grid Info ---\n";
		s += "grid@" + this.hashCode() + "\n";
		s += "sizeRatio = " + Double.toString(sizeRatio) + "\n";
		s += "offsetX = " + Double.toString(offsetX) + "\n";
		s += "offsetY = " + Double.toString(offsetY) + "\n";
		s += "--- End Grid Info ---\n";
		
		return s;
	}
}

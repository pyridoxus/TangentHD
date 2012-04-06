package cmcculloch1.csc212;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;

public class Grid extends GraphInterpolate {

	public Grid(double sizeRatio, double offsetX, double offsetY) {
		super(sizeRatio, offsetX, offsetY);
	}

	public void draw(Canvas canvas) {
		Point2D s = new Point2D();
		Point2D t = new Point2D();
		paint.setColor(Color.WHITE);
		canvas.drawPaint(paint);
		System.out.println("Drawing grid...");
		
		float borderE = (float)(bmp.getWidth() / 2.0 / sizeRatio);
		float temp = borderE % 1;
		borderE = borderE - temp + 1;
		float borderW = -borderE;
		float borderN = (float)(bmp.getHeight() / 2.0 / sizeRatio);
		temp = borderN % 1;
		borderN = borderN - temp + 1;
		float borderS = -borderN;
		System.out.println(Float.toString(borderE) + ", " + Float.toString(borderW) + ": " + Float.toString(borderN) + ", " + Float.toString(borderS));
		
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(1);
//		canvas.drawCircle(bmp.getWidth() / 2, bmp.getHeight() / 2, 100, paint);
		System.out.println("X grid...");
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
			System.out.println("------------------------------------------");
			System.out.println(Double.toString(x) + ", " + Double.toString(borderN));
			System.out.println(Double.toString(x) + ", " + Double.toString(borderS));
			System.out.println(Double.toString(s.getX()) + ", " + Double.toString(s.getY()));
			System.out.println(Double.toString(t.getX()) + ", " + Double.toString(t.getY()));
		}
		System.out.println("Y grid...");
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
			System.out.println(Double.toString(borderW) + ", " + Double.toString(y));
			System.out.println(Double.toString(borderE) + ", " + Double.toString(y));
			System.out.println(Double.toString(s.getX()) + ", " + Double.toString(s.getY()));
			System.out.println(Double.toString(t.getX()) + ", " + Double.toString(t.getY()));
		}
		System.out.println("End of drawing grid.");
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

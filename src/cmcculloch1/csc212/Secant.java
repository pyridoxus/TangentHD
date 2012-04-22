package cmcculloch1.csc212;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;

public class Secant extends GraphInterpolate{

	private Point2D p, q;	// Points to define the line
	private double m;		// Slope for display
	private double b;		// Y-int offset for display
	private double startX;	// Start (left side) where point can move
	private double endX;	// End (right side) where point can move
	private double stepX;	// Step size on x axis
	
	public Secant(double sizeRatio, double offsetX, double offsetY,
					double startX, double endX, double stepX) {
		super(sizeRatio, offsetX, offsetY);
		init(startX, endX, stepX);
	}
	
	private void init(double startX, double endX, double stepX) {
		this.startX = startX;
		this.endX = endX;
		this.stepX = stepX;
		p = new Point2D(0, 0);
		q = new Point2D(0, 0);
		m = 0;
		b = 0;
		paint.setStrokeWidth(2);
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.STROKE);
	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public double getStepX() {
		return stepX;
	}

	public void setStepX(double stepX) {
		this.stepX = stepX;
	}

	public Point2D getP() {
		return new Point2D(p);
	}

	public void setP(Point2D p) {
		this.p = new Point2D(p);
	}

	public Point2D getQ() {
		return new Point2D(q);
	}

	public void setQ(Point2D q) {
		this.q = new Point2D(q);
	}

	public double getM() {
		return m;
	}

	public double getB() {
		return b;
	}

	public void draw(Canvas canvas, boolean left) {
		Point2D s = new Point2D(0, 0);
		Point2D t = new Point2D(0, 0);
		double x1, x2, y1, y2;
		x1 = p.getX();
		y1 = p.getY();
		x2 = q.getX();
		y2 = q.getY();
		if(x1 == x2){
			this.m = 1e+50;	// Make slope large enough that it won't matter
		} else this.m = (y1 - y2) / (x1 - x2);
		this.b = y1 - this.m * x1;

		// Redo the coordinates so that the secant is a larger line segment...
		if(left == true) {
			x1 = -10.0; //TODO: Large enough for now, will add to resource later
			y1 = this.m * x1 + this.b;
		}
		else {
//	The following lines will make a line connecting through point of interest
//	if this if-else block is removed and applies to both end points.
			x1 = 10.0;
			y1 = this.m * x1 + this.b;
		}
//	The following lines will make a line segment ending at point of interest
		x2 = q.getX();
		y2 = q.getY();

		graphToBmp(x1, y1);
		s.setX(getInterpX());
		s.setY(getInterpY());
		graphToBmp(x2, y2);
		t.setX(getInterpX());
		t.setY(getInterpY());
		
		canvas.drawLine((float)s.getX(), (float)s.getY(),
				(float)t.getX(), (float)t.getY(), paint);
	}

	@Override
	public String toString() {
		String s = new String();
		s = "--- Secant ---\n";
		s += "Hash Code: " + this.hashCode() +"\n";
		s += "sizeRatio: " + this.sizeRatio + "\n";
		s += "offsetX: " + this.offsetX + "\n";
		s += "offsetY: " + this.offsetY + "\n";
		s += "startX: " + this.startX + "\n";
		s += "endX: " + this.endX + "\n";
		s += "stepX: " + this.stepX + "\n";
		s += "Color: " + this.paint.getColor() + "\n";
		s += "--- End of Secant ---\n";
		return s;
	}

}

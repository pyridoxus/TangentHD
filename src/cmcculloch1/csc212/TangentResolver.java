package cmcculloch1.csc212;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;

public class TangentResolver extends GraphInterpolate {
	private double leftSlope, leftOffset, rightSlope, rightOffset;
	private int state, equation;
	private Point2D p, q, r;	// Points to define the lines 
	
	public TangentResolver(double sizeRatio, double offsetX, double offsetY) {
		super(sizeRatio, offsetX, offsetY);
		this.paint.setStrokeWidth(3);
		this.paint.setStyle(Style.STROKE);
		this.paint.setColor(Color.WHITE);
		this.leftOffset = 0.0;
		this.leftSlope = 0.0;
		this.rightOffset = 0.0;
		this.rightSlope = 0.0;
		this.p = new Point2D(-10, 0);	// Set for left line
		// q is not allocated yet... This will be the point of interest
		this.r = new Point2D(10, 0);	// Set for right line
		this.state = 0;	// state = 0 if neither secant has become tangent
						// state = 1 if left secant has become tangent
						// state = 2 if right secant has become tangent
						// state = 3 if both secants have become tangent
		this.equation = 0;	// Some equations get different colors.
	}

	public void draw(Canvas canvas) {
		double s, t, u, v;
		switch(state) {
			case 1:
				graphToBmp(p.getX(), p.getY());
				s = getInterpX();
				t = getInterpY();
				graphToBmp(q.getX(), q.getY());
				u = getInterpX();
				v = getInterpY();
				paint.setColor(Color.YELLOW);
				canvas.drawLine((float)s, (float)t, (float)u, (float)v, paint);
			break;
			case 2:
				graphToBmp(r.getX(), r.getY());
				s = getInterpX();
				t = getInterpY();
				graphToBmp(q.getX(), q.getY());
				u = getInterpX();
				v = getInterpY();
				paint.setColor(Color.YELLOW);
				canvas.drawLine((float)s, (float)t, (float)u, (float)v, paint);
			break;
			case 3:
				graphToBmp(p.getX(), p.getY());
				s = getInterpX();
				t = getInterpY();
				graphToBmp(q.getX(), q.getY());
				u = getInterpX();
				v = getInterpY();
				if(this.equation == 1) paint.setColor(Color.RED);
				else paint.setColor(Color.GREEN);
				canvas.drawLine((float)s, (float)t, (float)u, (float)v, paint);
				graphToBmp(r.getX(), r.getY());
				s = getInterpX();
				t = getInterpY();
				canvas.drawLine((float)s, (float)t, (float)u, (float)v, paint);
			break;
		}
	}
	
	public void resolve() {
		p.setY(leftSlope * p.getX() + leftOffset);
		r.setY(rightSlope * r.getX() + rightOffset);
	}
	
	public void setMidPoint(Point2D q) {
		this.q = q;
	}
	
	public void setColor(int color) {
		this.paint.setColor(color);
	}
	
	public int getColor() {
		return this.paint.getColor();
	}

	public double getLeftSlope() {
		return leftSlope;
	}

	public void setLeftSlope(double leftSlope) {
		this.leftSlope = leftSlope;
	}

	public double getLeftOffset() {
		return leftOffset;
	}

	public void setLeftOffset(double leftOffset) {
		this.leftOffset = leftOffset;
	}

	public double getRightSlope() {
		return rightSlope;
	}

	public void setRightSlope(double rightSlope) {
		this.rightSlope = rightSlope;
	}

	public double getRightOffset() {
		return rightOffset;
	}

	public void setRightOffset(double rightOffset) {
		this.rightOffset = rightOffset;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state |= state;
//		System.out.println("State: " + Integer.toString(this.state));
	}

	public void clearState(int state) {
		this.state &= ~state;
//		System.out.println("State: " + Integer.toString(this.state));
	}

	public int getEquation() {
		return equation;
	}

	public void setEquation(int equation) {
		this.equation = equation;
	}
}

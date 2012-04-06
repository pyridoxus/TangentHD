package cmcculloch1.csc212;

import java.util.ArrayList;
import java.util.Iterator;

import android.graphics.Canvas;

public class Equation extends GraphInterpolate {
	protected ArrayList<Point2D> data;
	protected double startX;
	protected double endX;
	protected double stepX;
	protected String name;
	
	public Equation(double sizeRatio, double offsetX, double offsetY,
			double startX, double endX, double stepX, String name) {
		super(sizeRatio, offsetX, offsetY);
		this.startX = startX;
		this.endX = endX;
		this.stepX = stepX;
		this.name = name;
		this.data = new ArrayList<Point2D>();
	}

	@Override
	public String toString() {
		String s = new String();
		s = "Name: = " + name + "\n";
		s += "sizeRatio = " + sizeRatio + "\n";
		s += "offsetX = " + offsetX + "\n";
		s += "offsetY = " + offsetY + "\n";
		s += "startX = " + startX + "\n";
		s += "endX = " + endX + "\n";
		s += "stepX = " + stepX + "\n";
		s += "Bitmap = " + bmp + "\n";
		s += "Bitmap Size = " + bmp.getWidth() +", " + bmp.getHeight() + "\n";
		s += "Paint = " + paint + "\n";
		s += "Paint.color = " + paint.getColor() + "\n";
		s += "Paint.strokeWidth = " + paint.getStrokeWidth() + "\n";
		s += "Equation data length = " + data.size() + "\n";
		s += "Equation data:\n";
		for(Iterator<Point2D> i = data.iterator(); i.hasNext();) {
			Point2D p = i.next();
			s += "(" + p.getX() + ", " + p.getY() + ")\n";
		}
		return s;
	}

	public void draw(Canvas canvas) {
		// Override me.
	}
	
	public double getStartX() {
		return startX;
	}

	public void setStartX(float startX) {
		this.startX = startX;
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(float endX) {
		this.endX = endX;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point2D getData(int idx) {
		return data.get(idx);
	}

	public double getStepX() {
		return stepX;
	}

	public void setStepX(double stepX) {
		this.stepX = stepX;
	}
}

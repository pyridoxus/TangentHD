package cmcculloch1.csc212;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;

public class TangentResolver extends GraphInterpolate {
	private double leftSlope, leftOffset, rightSlope, rightOffset;
	
	public TangentResolver(double sizeRatio, double offsetX, double offsetY) {
		super(sizeRatio, offsetX, offsetY);
		this.paint.setStrokeWidth(2);
		this.paint.setStyle(Style.STROKE);
		this.paint.setColor(Color.BLACK);
	}

	public void draw(Canvas canvas) {
		canvas.drawLine(100, 100, 200, 200, paint);
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
	
}
